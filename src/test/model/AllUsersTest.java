package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllUsersTest {
    @Test
    private void testSingleton() {
        AllUsers first = AllUsers.getInstance();
        AllUsers second = AllUsers.getInstance();
        assertEquals(first, second);
    }

}
