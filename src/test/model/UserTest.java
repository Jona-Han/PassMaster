package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testConstructor() {
        char[] expectedUsername = "admin".toCharArray();
        char[] expectedPassword = "pass".toCharArray();
        CollectionOfAccounts accounts = new CollectionOfAccounts();
        User testUser = new User(expectedUsername, expectedPassword, accounts);

        assertEquals(expectedUsername, testUser.getMasterUsername());
        assertEquals(expectedPassword, testUser.getMasterPassword());
        assertEquals(accounts, testUser.getAccounts());
    }
}
