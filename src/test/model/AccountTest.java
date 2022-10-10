package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account("Test Website", "test1", "password123");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Test Website", account.getName());
        assertEquals("test1", account.getUsername());
        assertEquals("password123", account.getPassword());
    }

    @Test
    void testSetName() {
        account.setName("New Name");
        assertEquals("New Name", account.getName());
    }

    @Test
    void testSetUsername() {
        account.setUsername("newUsername");
        assertEquals("newUsername", account.getUsername());
    }

    @Test
    void testSetPassword() {
        account.setPassword("newPassword");
        assertEquals("newPassword", account.getPassword());
    }
}