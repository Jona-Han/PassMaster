package persistence;


import model.Account;
import model.AllUsers;
import model.User;

import java.io.IOException;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
    User user;
    User userTwo;
    AllUsers allUsers = AllUsers.getInstance();

    @BeforeEach
    void setUp() {
        allUsers.clear();
        user = new User("username", "password");
        userTwo = new User("second", "pass");
    }


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //success
        }
    }

    @Test
    void testWriterNoAccountInUser() {
        try {
            allUsers.addUser(user);
            JsonWriter writer = new JsonWriter("./data/testWriterNoAccounts.json");
            writer.open();
            writer.write();
            writer.close();
            allUsers.clear();

            JsonReader reader = new JsonReader("./data/testWriterNoAccounts.json");
            reader.read();
            assertEquals(user, allUsers.get(0));
        } catch (IOException e) {
            fail("IO Exception thrown");
        }
    }

    @Test
    void testWriterWithTwoAccountsInUser() {
        try {
            user.add(new Account("first", "a", "b"));
            user.add(new Account("second", "c", "d"));
            allUsers.addUser(user);

            JsonWriter writer = new JsonWriter("./data/testWriterWithTwoAccountsInUser.json");
            writer.open();
            writer.write();
            writer.close();
            allUsers.clear();

            JsonReader reader = new JsonReader("./data/testWriterWithTwoAccountsInUser.json");
            reader.read();
            assertEquals(user, allUsers.get(0));
        } catch (IOException e) {
            fail("IO Exception");
        }
    }

    @Test
    void testWriterWithTwoUsersNoAccounts() {
        try {
            allUsers.addUser(user);
            allUsers.addUser(userTwo);

            JsonWriter writer = new JsonWriter("./data/testWriterWithTwoUsersNoAccounts.json");
            writer.open();
            writer.write();
            writer.close();
            allUsers.clear();

            JsonReader reader = new JsonReader("./data/testWriterWithTwoUsersNoAccounts.json");
            reader.read();
            assertEquals(user, allUsers.get(0));
            assertEquals(userTwo, allUsers.get(1));
        } catch (IOException e) {
            fail("IO Exception");
        }
    }

    @Test
    void testWriterWithTwoUsersWithAccounts() {
        try {
            user.add(new Account("first", "a", "b"));
            user.add(new Account("second", "c", "d"));
            userTwo.add(new Account("first", "a", "b"));
            userTwo.add(new Account("second", "c", "d"));
            allUsers.addUser(user);
            allUsers.addUser(userTwo);

            JsonWriter writer = new JsonWriter("./data/testWriterWithTwoUsersWithAccounts.json");
            writer.open();
            writer.write();
            writer.close();
            allUsers.clear();

            JsonReader reader = new JsonReader("./data/testWriterWithTwoUsersWithAccounts.json");
            reader.read();
            assertEquals(user, allUsers.get(0));
            assertEquals(userTwo, allUsers.get(1));
        } catch (IOException e) {
            fail("IO Exception");
        }
    }
}