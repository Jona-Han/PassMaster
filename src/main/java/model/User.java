package model;

import encryption.EncryptionUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Represents a user class with a master passwordHash and a list of accounts
public class User implements Writable {
    private final List<Account> accounts;
    private String username;
    private String passwordHash;

    /*
     * EFFECTS: Constructs an empty CollectionOfAccounts with a master passwordHash
     */
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = password;
        accounts = new ArrayList<>();
    }

    /*
     * EFFECTS: Constructs a new CollectionOfAccounts with an account and a master passwordHash
     */
    public User(String username, String passwordHash, Account account) {
        this.username = username;
        this.passwordHash = passwordHash;
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
                        + " - username: " + account.getUsername()));
        return accounts.add(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an Account object from CollectionOfAccounts, returns true if successfully removed
     */
    public boolean remove(Account account) {
        EventLog.getInstance().logEvent(new Event(
                "Account removed from User: " + account.getName()
                        + " - username: " + account.getUsername()));
        return accounts.remove(account);
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void hashPassword() {
        passwordHash = EncryptionUtil.hashPassword(passwordHash, username);
    }

    /*
     * EFFECTS: Converts this to a JSON Object
     */
    @Override
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("User data converted to JSON."));
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("passwordHash", passwordHash);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(accounts, user.accounts) && username.equals(user.username)
                && passwordHash.equals(user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounts, username, passwordHash);
    }
}
