package org.toni.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUsername() {
        User user = new User("testUser", LocalDate.of(1990, 1, 1));
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void getDateOfBirth() {
        User user = new User("testUser", LocalDate.of(1990, 1, 1));
        assertEquals(LocalDate.of(1990, 1, 1), user.getDateOfBirth());
    }

    @Test
    void setUsername() {
        User user = new User("testUser", LocalDate.of(1990, 1, 1));
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    void setDateOfBirth() {
        User user = new User("testUser", LocalDate.of(1990, 1, 1));
        user.setDateOfBirth(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), user.getDateOfBirth());
    }

    @Test
    void testEquals() {
        User user1 = new User("testUser", LocalDate.of(1990, 1, 1));
        User user2 = new User("testUser", LocalDate.of(1990, 1, 1));
        assertEquals(user1, user2);
    }

    @Test
    void canEqual() {
        User user1 = new User("testUser", LocalDate.of(1990, 1, 1));
        User user2 = new User("testUser", LocalDate.of(1990, 1, 1));
        assertTrue(user1.equals(user2) && user2.equals(user1));
    }

    @Test
    void testHashCode() {
        User user1 = new User("testUser", LocalDate.of(1990, 1, 1));
        User user2 = new User("testUser", LocalDate.of(1990, 1, 1));
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToString() {
        User user = new User("testUser", LocalDate.of(1990, 1, 1));
        String expected = "User(username=testUser, dateOfBirth=1990-01-01)";
        assertEquals(expected, user.toString());
    }
}