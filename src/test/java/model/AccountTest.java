package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    Account account;
    String name;
    String username;
    String password;
    SecretKey key;
    byte[] iv;

    @BeforeEach
    void setUp() {
        name = "Test Website";
        username = "test1";
        password = "password123";
        iv = new byte[]{20, 30, 80, 65};
        key = new SecretKeySpec(iv, "AES");
        account = new Account(name, username, password, key, iv);
    }

    @Test
    void testConstructor() {
        assertEquals(account.getName(), name);
        assertEquals(account.getPassword(), password);
        assertEquals(account.getUsername(), username);
        assertEquals(account.getSecretKey(), key);
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