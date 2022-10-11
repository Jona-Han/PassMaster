package ui;

import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordManagerAppTest {
    PasswordManagerApp manager;

    @BeforeEach
    void setUp() {
        manager = new PasswordManagerApp();
        accounts.add(new Account("test1", "user1", "pass1"));
        accounts.add(new Account("test2", "user2", "pass2"));
        accounts.add(new Account("test3", "user2", "pass3"));
    }


    //TODO
    @Test
    void testRunLoginProcess() {}

    //TODO
    @Test
    void testRunManagerProcess() {}

    //TODO
    @Test
    void testDisplayCommandMenu() {}

    //TODO
    @Test
    void testDisplayAllAccounts() {}


    //TODO
    @Test
    void testGetUserInput() {
    }

    @Test
    void testProcessCommand() {}

    @Test
    void testViewSpecificAccountInformation() {
    }

    @Test
    void testAddAccount() {}

    @Test
    void testRemoveAccount() {}

    @Test
    void testEditAccount() {}

    @Test
    void testChangeUsername() {}

    @Test
    void testChangePassword() {}

}
