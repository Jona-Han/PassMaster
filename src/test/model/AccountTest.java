package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    Account account;
    String name;
    String username;
    String password;
    String salt;
    byte[] iv;

    @BeforeEach
    void setUp() {
        name = "Test Website";
        username = "test1";
        password = "password123";
        salt = "random";
        iv = new byte[]{20, 30, 80, 65};
        account = new Account(name, username, password, salt, iv);
    }

    @Test
    void testConstructor() {
        assertEquals(account.getName(), name);
        assertEquals(account.getPassword(), password);
        assertEquals(account.getUsername(), username);
        assertEquals(account.getSalt(), salt);
        assertEquals(account.getIV(), iv);
    }

    @Test
    void testToString() {
        account = new Account(name, username, password);
        String expected = "<html><div style=\"font-size:12px;font-weight:bold;\">" + name
                + "</div><div style=\"font-size:8px;\">" + username + "</div></html>";
        assertEquals(expected, account.toString());
    }
}