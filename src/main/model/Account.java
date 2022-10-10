package model;

//Represents an account with a name, a login username, and a login password
public class Account {
    private String name;
    private String username;
    private String password;

    /*
     * REQUIRES: accountName and accountPassword have non-zero length
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /*
     * REQUIRES: name must have non-zero length
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * REQUIRES: username must have non-zero length
     * MODIFIES: this
     * EFFECTS: changes the account's associated username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * REQUIRES: password must have non-zero length
     * MODIFIES: this
     * EFFECTS: changes the account's associated password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;    //stub
    }

    public String getUsername() {
        return username;    //stub
    }

    public String getPassword() {
        return password;    //stub
    }
}

/*
 * REQUIRES:
 * MODIFIES:
 * EFFECTS:
 */