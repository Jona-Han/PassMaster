package persistence;


import model.Account;
import model.User;

import java.io.IOException;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

//All the code in this class is heavily inspired by...
//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User("password");
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
    void testWriterNoAccounts() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterNoAccounts.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNoAccounts.json");
            user = reader.read();
            assertEquals("password", user.getPassword());
            assertEquals(0, user.size());
        } catch (IOException e) {
            fail("IO Exception thrown");
        }
    }

    @Test
    void testWriterUnderNormalFunction() {
        try {
            user.add(new Account("first", "a", "b"));
            user.add(new Account("second", "c", "d"));
            JsonWriter writer = new JsonWriter("./data/testWriterUnderNormalFunction.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUnderNormalFunction.json");
            user = reader.read();
            assertEquals("password", user.getPassword());
            assertEquals(2, user.size());

            checkAccountIsSame("first", "a", "b", user.get(0));
            checkAccountIsSame("second", "c", "d", user.get(1));
        } catch (IOException e) {
            fail("IO Exception");
        }
    }
}