package org.toni.controller;

import org.toni.entity.User;
import org.toni.service.DateService;
import org.toni.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HelloWorldControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private DateService dateService;

    @InjectMocks
    private HelloWorldController helloWorldController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("dateOfBirth", "2000-01-01");

        User user = new User("testUser", LocalDate.parse("2000-01-01"));

        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<Void> responseEntity = helloWorldController.saveUser("testUser", requestBody);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetHelloMessage() {
        User user = new User("testUser", LocalDate.now().minusYears(20));

        when(userService.findUserByUsername("testUser")).thenReturn(Optional.of(user));
        when(dateService.getDaysUntilNextBirthday(user.getDateOfBirth())).thenReturn(365L);

        ResponseEntity<Map<String, String>> responseEntity = helloWorldController.getHelloMessage("testUser");

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Hello, testUser! Your birthday is in 365 day(s).", responseEntity.getBody().get("message"));
    }
}