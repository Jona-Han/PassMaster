package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    Account account;
    String name;
    String username;
    String password;

    @BeforeEach
    void setUp() {
        name = "Test Website";
        username = "test1";
        password = "password123";
        account = new Account(name, username, password);
    }

    @Test
    void testConstructorNoneEmptyArray() {
        String expectedName = "Test Website";
        String expectedUsername = "test1";
        String expectedPassword = "password123";

        assertEquals(expectedName, account.getName());
        assertEquals(expectedUsername, account.getUsername());
        assertEquals(expectedPassword, account.getPassword());
    }

    @Test
    void testConstructorEmptyUsernameArray() {
        String expectedName = "Test Website";
        String expectedUsername = "_NO_VALUE_";
        String expectedPassword = "password123";

        account = new Account("Test Website", "", "password123");

        assertEquals(expectedName, account.getName());
        assertEquals(expectedUsername, account.getUsername());
        assertEquals(expectedPassword, account.getPassword());
    }

    @Test
    void testConstructorEmptyPasswordArray() {
        String expectedName = "Test Website";
        String expectedUsername = "test1";
        String expectedPassword = "_NO_VALUE_";

        account = new Account("Test Website", "test1", "");

        assertEquals(expectedName, account.getName());
        assertEquals(expectedUsername, account.getUsername());
        assertEquals(expectedPassword, account.getPassword());
    }


    @Test
    void testSetName() {
        String newName = "New Name";
        String expected = "New Name";
        account.setName(newName);
        assertEquals(expected, account.getName());
    }

    @Test
    void testSetUsername() {
        String newUsername = "newUsername";
        String expected = "newUsername";
        account.setUsername(newUsername);
        assertEquals(expected, account.getUsername());
    }

    @Test
    void testSetUsernameEmptyArray() {
        String newUsername = "";
        String expected = "_NO_VALUE_";
        account.setUsername(newUsername);
        assertEquals(expected, account.getUsername());
    }

    @Test
    void testSetPassword() {
        String newPassword = "newPassword";
        String expected  = "newPassword";
        account.setPassword(newPassword);
        assertEquals(expected, account.getPassword());
    }

    @Test
    void testSetPasswordEmptyArray() {
        String newPassword = "";
        String expected = "_NO_VALUE_";
        account.setPassword(newPassword);
        assertEquals(expected, account.getPassword());
    }
}