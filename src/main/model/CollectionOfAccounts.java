package model;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfAccounts {
    List<Account> accounts;

    /*
     * EFFECTS: Constructs an empty CollectionOfAccounts
     */
    public CollectionOfAccounts() {
        accounts = new ArrayList<>();
    }

    /*
     * EFFECTS: Constructs a new CollectionOfAccounts with an account
     */
    public CollectionOfAccounts(Account account) {
        accounts = new ArrayList<>();
        accounts.add(account);
    }

    /*
     * REQUIRES: account is non-null
     * MODIFIES: this
     * EFFECTS: adds an account to the CollectionOfAccounts, returns true if successfully added
     */
    public boolean add(Account account) {
        return accounts.add(account);
    }

    /*
     * REQUIRES: account is non-null
     * MODIFIES: this
     * EFFECTS: removes an Account object from CollectionOfAccounts, returns true if successfully removed
     */
    public boolean remove(Account account) {
        return accounts.remove(account);
    }

    /*
     * REQUIRES: size of Collection > index >= 0
     * MODIFIES: this
     * EFFECTS: removes an account at the specified index and returns that Account object
     */
    public Account remove(int index) {
        return accounts.remove(index);
    }

    /*
     * REQUIRES: account is non-null
     * EFFECTS: checks if there's an Account object in the CollectionOfAccounts and returns true if there is
     */
    public boolean contains(Account account) {
        return accounts.contains(account);
    }

    /*
     * EFFECTS: returns the size of the CollectionOfAccounts
     */
    public int size() {
        return accounts.size();
    }

    /*
     * REQUIRES: size of Collection > index >= 0
     * EFFECTS: returns an Account at the specified index
     */
    public Account get(int index) {
        return accounts.get(index);
    }

    /*
     * EFFECTS: returns the inner list. Primarily just for testing
     */
    public List<Account> getInnerList() {
        return accounts;
    }
}
