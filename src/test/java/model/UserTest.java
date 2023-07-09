package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User testCollection;
    List<Account> innerList;
    String username;
    String pass;
    Account a;
    Account b;
    Account c;

    @BeforeEach
    void setUp() {
        a = new Account("a", "userA", "passA");
        b = new Account("b", "userB", "passB");
        c = new Account("c", "userC", "passC");
        username = "testUser";
        pass = "testPass";

        testCollection = new User(username, pass, a);
        innerList = testCollection.getInnerList();
    }

    @Test
    void testEmptyConstructor() {
        testCollection = new User(username, pass);
        List<Account> actualList = testCollection.getInnerList();
        assertEquals(0, actualList.size());
        assertEquals(pass, testCollection.getPasswordHash());
    }

    @Test
    void testConstructor() {
        testCollection = new User(username, pass, a);
        List<Account> actualInnerList = testCollection.getInnerList();
        assertEquals(1, actualInnerList.size());
    }

    @Test
    void testAddOneTime() {
        assertTrue(testCollection.add(b));
        assertEquals(2, innerList.size());
        assertTrue(innerList.contains(a));
        assertTrue(innerList.contains(b));
    }

    @Test
    void testAddSameObject() {
        assertTrue(testCollection.add(a));
        assertEquals(2, innerList.size());
        assertTrue(innerList.contains(a));
    }

    @Test
    void testAddMultipleTimes() {
        testCollection.add(b);
        testCollection.add(c);

        assertEquals(3, innerList.size());
        assertTrue(innerList.contains(a));
        assertTrue(innerList.contains(b));
        assertTrue(innerList.contains(c));
    }

    @Test
    void testRemoveObjectParameterSucceed() {
        assertTrue(testCollection.remove(a));
        assertEquals(0, innerList.size());
    }

    @Test
    void testRemoveObjectParameterFail() {
        assertFalse(testCollection.remove(b));
        assertEquals(1, innerList.size());
    }

    @Test
    void testGet() {
        assertEquals(a, innerList.get(0));
    }
}
