package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;
    char[] name;
    char[] username;
    char[] password;

    @BeforeEach
    void setUp() {
        name = "Test Website".toCharArray();
        username = "test1".toCharArray();
        password = "password123".toCharArray();
        account = new Account(name, username, password);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(name, account.getName());
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
    }

    @Test
    void testSetName() {
        char[] newName = "New Name".toCharArray();
        account.setName(newName);
        assertEquals(newName, account.getName());
    }

    @Test
    void testSetUsername() {
        char[] newUsername = "newUsername".toCharArray();
        account.setUsername(newUsername);
        assertEquals(newUsername, account.getUsername());
    }

    @Test
    void testSetPassword() {
        char[] newPassword = "newPassword".toCharArray();
        account.setPassword(newPassword);
        assertEquals(newPassword, account.getPassword());
    }
}