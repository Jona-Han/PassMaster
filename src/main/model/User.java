package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a user class with a master password and a list of accounts
public class User implements Writable {
    private final List<Account> accounts;
    private String masterPassword;

    /*
     * EFFECTS: Constructs an empty CollectionOfAccounts with a master password
     */
    public User(String masterPassword) {
        this.masterPassword = masterPassword;
        accounts = new ArrayList<>();
    }

    /*
     * EFFECTS: Constructs a new CollectionOfAccounts with an account and a master password
     */
    public User(String masterPassword, Account account) {
        this.masterPassword = masterPassword;
        accounts = new ArrayList<>();
        accounts.add(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an account to the CollectionOfAccounts, returns true if successfully added
     */
    public boolean add(Account account) {
        EventLog.getInstance().logEvent(new Event(
                "Account added to User: " + account.getName()
                        + " - username: " + account.getUsername()
                        + " password: " + account.getPassword()));
        return accounts.add(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an Account object from CollectionOfAccounts, returns true if successfully removed
     */
    public boolean remove(Account account) {
        EventLog.getInstance().logEvent(new Event(
                "Account removed from User: " + account.getName()
                        + " - username: " + account.getUsername()
                        + " password: " + account.getPassword()));
        return accounts.remove(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an account at the specified index and returns that Account object
     */
    public Account remove(int index) {
        EventLog.getInstance().logEvent(new Event(
                "Account removed from User: " + accounts.get(index).getName()
                        + " - username: " + accounts.get(index).getUsername()
                        + " password: " + accounts.get(index).getPassword()));
        return accounts.remove(index);
    }

    /*
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
     * EFFECTS: returns an Account at the specified index
     */
    public Account get(int index) {
        return accounts.get(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: replaces an Account at the specified index with the given account and returns the replaced account
     */
    public Account set(int index, Account account) {
        return accounts.set(index, account);
    }

    public List<Account> getInnerList() {
        return accounts;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String newPassword) {
        this.masterPassword = newPassword;
    }

    /*
     * EFFECTS: Converts this to a JSON Object
     */
    @Override
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("User data converted to JSON."));
        JSONObject json = new JSONObject();
        json.put("masterPassword", masterPassword);
        json.put("accounts", accountsToJson());
        return json;
    }

    /*
     * EFFECTS: converts the list of accounts stored in this to a JSON array
     */
    private JSONArray accountsToJson() {
        JSONArray json = new JSONArray();

        for (Account account : accounts) {
            json.put(account.toJson());
        }
        return json;
    }
}
