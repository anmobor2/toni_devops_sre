package org.toni.controller;

import org.toni.entity.User;
import org.toni.service.DateService;
import org.toni.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    private final UserService userService;
    private final DateService dateService;

    public HelloWorldController(UserService userService, DateService dateService) {
        this.userService = userService;
        this.dateService = dateService;
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> saveUser(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> request) {

        if (!Pattern.matches("^[a-zA-Z]+$", username)) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate dateOfBirth = LocalDate.parse(request.get("dateOfBirth"));
        if (!dateOfBirth.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User(username, dateOfBirth);
        User userReturned = userService.saveUser(user);
        if (userReturned == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, String>> getHelloMessage(
            @PathVariable("username") String username) {
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();

        long daysUntilBirthday = dateService.getDaysUntilNextBirthday(user.getDateOfBirth());

        String message;
        if (daysUntilBirthday == 0) {
            message = String.format("Hello, %s! Happy birthday!", username);
        } else {
            message = String.format("Hello, %s! Your birthday is in %d day(s).", username, daysUntilBirthday);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}