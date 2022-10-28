package persistence;

import model.Account;

import static org.junit.jupiter.api.Assertions.*;

//All the code in this class is heavily inspired by...
//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkAccountIsSame(String name, String username, String password, Account account) {
        assertEquals(name, account.getName());
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
    }
}
