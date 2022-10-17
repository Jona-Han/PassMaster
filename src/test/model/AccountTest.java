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
        char[] expectedName = "Test Website".toCharArray();
        char[] expectedUsername = "test1".toCharArray();
        char[] expectedPassword = "password123".toCharArray();

        assertArrayEquals(expectedName, account.getName());
        assertArrayEquals(expectedUsername, account.getUsername());
        assertArrayEquals(expectedPassword, account.getPassword());
    }

    @Test
    void testSetName() {
        char[] newName = "New Name".toCharArray();
        char[] expected = "New Name".toCharArray();
        account.setName(newName);
        assertArrayEquals(expected, account.getName());
    }

    @Test
    void testSetUsername() {
        char[] newUsername = "newUsername".toCharArray();
        char[] expected = "newUsername".toCharArray();
        account.setUsername(newUsername);
        assertArrayEquals(expected, account.getUsername());
    }

    @Test
    void testSetUsernameEmptyArray() {
        char[] newUsername = new char[0];
        char[] expected = "_NO_VALUE_".toCharArray();
        account.setUsername(newUsername);
        assertArrayEquals(expected, account.getUsername());
    }

    @Test
    void testSetPassword() {
        char[] newPassword = "newPassword".toCharArray();
        char[] expected  = "newPassword".toCharArray();
        account.setPassword(newPassword);
        assertArrayEquals(expected, account.getPassword());
    }

    @Test
    void testSetPasswordEmptyArray() {
        char[] newPassword = new char[0];
        char[] expected = "_NO_VALUE_".toCharArray();
        account.setPassword(newPassword);
        assertArrayEquals(expected, account.getPassword());
    }
}