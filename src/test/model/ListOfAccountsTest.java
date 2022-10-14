package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOfAccountsTest {
    ListOfAccounts list;
    Account a;
    Account b;
    Account c;

    @BeforeEach
    private void setUp() {
        list = new ListOfAccounts();
        a = new Account("a", "usera", "passa");
        b = new Account("a", "usera", "passa");
        c = new Account("a", "usera", "passa");

        list.add(a);
        list.add(b);
        list.add(c);
    }


}
