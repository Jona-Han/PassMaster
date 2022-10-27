package model;

import exceptions.CollectionIndexOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.PasswordManagerApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User testCollection;
    List<Account> innerList;
    Account a;
    Account b;
    Account c;

    @BeforeEach
    void setUp() {
        a = createNewAccountFromStrings("a", "userA", "passA");
        b = createNewAccountFromStrings("b", "userB", "passB");
        c = createNewAccountFromStrings("c", "userC", "passC");

        testCollection = new User(a);
        innerList = testCollection.getInnerList();
    }

    private Account createNewAccountFromStrings(String name, String username, String password) {
        return new Account(name.toCharArray(), username.toCharArray(), password.toCharArray());
    }


    @Test
    void testEmptyConstructor() {
        testCollection = new User();
        List<Account> actualList = testCollection.getInnerList();
        assertEquals(0, actualList.size());
    }

    @Test
    void testConstructor() {
        testCollection = new User(a);
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
    void testRemoveIntegerParameterSucceed() {
        try {
            assertEquals(a, testCollection.remove(0));
            assertEquals(0, innerList.size());
        } catch (CollectionIndexOutOfBoundsException e) {
            fail();
        }
    }

    @Test
    void testSizeEmptyCollection() {
        testCollection = new User();
        assertEquals(0, testCollection.size());
    }


    @Test
    void testSize() {
        assertEquals(1, testCollection.size());
    }

    @Test
    void testSizeMultipleItems() {
        testCollection.add(b);
        testCollection.add(c);
        assertEquals(3, testCollection.size());
    }

    @Test
    void testContainsTrue() {
        assertTrue(testCollection.contains(a));
    }

    @Test
    void testContainsFalse() {
        assertFalse(testCollection.contains(b));
    }

    @Test
    void testGet() {
        assertEquals(a, innerList.get(0));
    }

    @Test
    void testGetWithMultipleElementsInList() {
        try {
            testCollection.add(b);
            testCollection.add(c);
            assertEquals(b, testCollection.get(1));
            assertEquals(c, testCollection.get(2));
        } catch (CollectionIndexOutOfBoundsException e) {
            fail();
        }
    }
}
