package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfAccounts {
    List<Account> accounts;

    /*
     * EFFECTS: Constructs an empty ListOfAccounts
     */
    public ListOfAccounts() {
        accounts = new ArrayList<>();
    }
    /*
     * EFFECTS: Constructs a new ListOfAccounts with an account
     */
    public ListOfAccounts(Account account) {
        accounts = new ArrayList<>();
        accounts.add(account);
    }

    /*
     * REQUIRES: account is non-null and not already in the ListOfAccounts
     * MODIFIES: this
     * EFFECTS: adds an account to the ListOfAccounts, returns true if successfully added
     */
    public boolean add(Account account) {
        if (!accounts.contains(account)) {
            return accounts.add(account);
        }
        return false;
    }

    /*
     * REQUIRES: account is non-null
     * MODIFIES: this
     * EFFECTS: removes an Account account form ListOfAccounts, returns true if successfully removed
     */
    public boolean remove(Account account) {
        return accounts.remove(account);
    }

    /*
     * REQUIRES: index >= 0
     * MODIFIES: this
     * EFFECTS: removes an account at the specified index and returns that Account object
     */
    public Account remove(int index) {
        return accounts.remove(index);
    }

    /*
     * REQUIRES: accounts is non-null
     * EFFECTS: checks if there's an Account account in the ListOfAccounts and returns true if there is
     */
    public boolean contains(Account account) {
        return true;
    }

    /*
     * EFFECTS: returns the size of the ListOfAccounts
     */
    public int size() {
        return 0;
    }

    /*
     * REQUIRES: index >= 0
     * EFFECTS: returns an Account at the specified index
     */
    public Account get(int index) {
        return null;
    }

    /*
     * EFFECTS: returns the inner list
     */
    public List<Account> getInnerList() {
        return accounts;
    }
}
