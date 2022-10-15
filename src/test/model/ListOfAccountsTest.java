package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfAccountsTest {
    ListOfAccounts testListOfAccounts;
    List<Account> actualInnerList;
    Account a;
    Account b;
    Account c;

    @BeforeEach
    void setUp() {
        a = new Account("a", "usera", "passa");
        b = new Account("b", "userb", "passb");
        c = new Account("c", "userc", "passc");

        testListOfAccounts = new ListOfAccounts(a);
        List<Account> actualInnerList = testListOfAccounts.getInnerList();
    }

    @Test
    void testEmptyConstructor() {
        testListOfAccounts = new ListOfAccounts();
        List<Account> actualList = testListOfAccounts.getInnerList();
        assertEquals(0, actualList.size());
    }

    @Test
    void testConstructor() {
        testListOfAccounts = new ListOfAccounts(a);
        List<Account> actualInnerList = testListOfAccounts.getInnerList();
        assertEquals(1, actualInnerList.size());
    }

    @Test
    void testAddOneTime() {
        assertTrue(testListOfAccounts.add(b));
        assertEquals(2, actualInnerList.size());
        assertTrue(actualInnerList.contains(a));
        assertTrue(actualInnerList.contains(b));
    }

    @Test
    void testAddSameObject() {
        assertFalse(testListOfAccounts.add(a));
        assertEquals(1, actualInnerList.size());
        assertTrue(actualInnerList.contains(a));
    }

    @Test
    void testAddMultipleTimes() {
        testListOfAccounts.add(b);
        testListOfAccounts.add(c);

        assertEquals(3, actualInnerList.size());
        assertTrue(actualInnerList.contains(a));
        assertTrue(actualInnerList.contains(b));
        assertTrue(actualInnerList.contains(c));
    }

    @Test
    void testRemoveObjectParameterSucceed() {
        assertTrue(testListOfAccounts.remove(a));
        assertEquals(0, actualInnerList.size());
    }

    @Test
    void testRemoveObjectParameterFail() {
        assertFalse(testListOfAccounts.remove(b));
        assertEquals(1, actualInnerList.size());
    }

    @Test
    void testRemoveIntegerParameterSucceed() {
        assertTrue(testListOfAccounts.remove(0));
        assertEquals(0, actualInnerList.size());
    }

    @Test
    void testRemoveIntegerParameterFail() {
        assertFalse(testListOfAccounts.remove(1));
        assertEquals(1, actualInnerList.size());
    }

    @Test
    void testGet() {
        assertEquals(a, actualInnerList.get(0));
    }

    @Test
    void testGetWithMultipleElementsInList() {
        testListOfAccounts.add(b);
        testListOfAccounts.add(c);
        assertEquals(b, testListOfAccounts.get(1));
        assertEquals(c, testListOfAccounts.get(2));
    }


}
