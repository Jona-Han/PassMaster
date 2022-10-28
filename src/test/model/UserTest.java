package model;

import exceptions.CollectionIndexOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User testCollection;
    List<Account> innerList;
    String pass;
    Account a;
    Account b;
    Account c;

    @BeforeEach
    void setUp() {
        a = new Account("a", "userA", "passA");
        b = new Account("b", "userB", "passB");
        c = new Account("c", "userC", "passC");
        pass = "testPass";

        testCollection = new User(pass, a);
        innerList = testCollection.getInnerList();
    }

    @Test
    void testEmptyConstructor() {
        testCollection = new User(pass);
        List<Account> actualList = testCollection.getInnerList();
        assertEquals(0, actualList.size());
        assertEquals(pass, testCollection.getMasterPassword());
    }

    @Test
    void testConstructor() {
        testCollection = new User(pass, a);
        List<Account> actualInnerList = testCollection.getInnerList();
        assertEquals(1, actualInnerList.size());
    }

    @Test
    void testSetMasterPassword() {
        testCollection.setMasterPassword("newPassword");
        assertEquals("newPassword", testCollection.getMasterPassword());
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
    void testRemoveIntegerParameterOutOfBoundsBoundary() {
        try {
            testCollection.remove(1);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Test
    void testRemoveIntegerParameterOutOfBoundsNegative() {
        try {
            testCollection.remove(-1);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Test
    void testRemoveIntegerParameterOutOfBounds() {
        try {
            testCollection.remove(3);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Test
    void testSizeEmptyCollection() {
        testCollection = new User(pass);
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

    @Test
    void testGetOutOfBoundsBoundary() {
        try {
            testCollection.get(1);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Test
    void testGetOutOfBoundsNegative() {
        try {
            testCollection.get(-1);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }

    @Test
    void testGetOutOfBounds() {
        try {
            testCollection.get(3);
            fail("Exception should have been thrown");
        } catch (CollectionIndexOutOfBoundsException e) {
            //pass
        }
    }
}
