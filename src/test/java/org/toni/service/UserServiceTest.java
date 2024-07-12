package org.toni.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.toni.entity.User;
import org.toni.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = new User("testUser", LocalDate.now());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findUserByUsername() {
        Optional<User> user = Optional.of(new User("testUser", LocalDate.now()));
        when(userRepository.findById("testUser")).thenReturn(user);

        Optional<User> foundUser = userService.findUserByUsername("testUser");

        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findById("testUser");
    }

    @Test
    void getAllUsers() {
        User user1 = new User("user1", LocalDate.now());
        User user2 = new User("user2", LocalDate.now());
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        var users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById("testUser");

        userService.deleteUser("testUser");

        verify(userRepository, times(1)).deleteById("testUser");
    }
}