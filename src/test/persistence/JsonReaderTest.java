package persistence;


import model.User;

import java.io.IOException;
import exceptions.CollectionIndexOutOfBoundsException;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

//All the code in this class is heavily inspired by...
//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //success
        }
    }

    @Test
    void testReaderNoAccounts() {
        JsonReader reader = new JsonReader("./data/testReaderNoAccounts.json");
        try {
            User user = reader.read();
            assertEquals("password", user.getMasterPassword());
            assertEquals(0, user.size());
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

    @Test
    void testReaderUnderNormalFunction() {
        JsonReader reader = new JsonReader("./data/testReaderUnderNormalFunction.json");
        try {
            User user = reader.read();
            assertEquals("password", user.getMasterPassword());
            assertEquals(2, user.size());
            checkAccountIsSame("first", "a", "b", user.get(0));
            checkAccountIsSame("second", "c", "d", user.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (CollectionIndexOutOfBoundsException e) {
            fail("Couldn't get Account from user");
        }
    }
}