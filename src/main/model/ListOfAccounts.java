package model;

public class ListOfAccounts {

    /*
     * REQUIRES: account is non-null
     * MODIFIES: this
     * EFFECTS: adds an account to the ListOfAccounts
     */
    public void add(Account account) {}

    /*
     * REQUIRES: account is non-null
     * MODIFIES: this
     * EFFECTS: removes an Account account form ListOfAccounts
     */
    public void remove(Account account) {}

    /*
     * REQUIRES: index >= 0
     * MODIFIES: this
     * EFFECTS: removes an account at the specified index
     */
    public void remove(int index) {}

    /*
     * REQUIRES: accounts is non-null
     * EFFECTS: checks if there's an Account account in the ListOfAccounts and returns true if there is
     */
    public boolean contains(Account account) {}

    /*
     * EFFECTS: returns the size of the ListOfAccounts
     */
    public int size() {}

    /*
     * REQUIRES: index >= 0
     * EFFECTS: returns an Account at the specified index
     */
    public Account get(int index) {}
}
