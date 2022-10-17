package model;

//Represents an account with a name, a login username, and a login password
public class Account {
    private char[] name;
    private char[] username;
    private char[] password;

    /*
     * REQUIRES: name must have non-zero length
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(char[] name, char[] username, char[] password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /*
     * REQUIRES: name must have non-zero length
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     */
    public void setName(char[] name) {
        this.name = name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated username
     */
    public void setUsername(char[] username) {
        if (username.length == 0) {
            this.username = "_NO_VALUE_".toCharArray();
        } else {
            this.username = username;
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated password
     */
    public void setPassword(char[] password) {
        if (password.length == 0) {
            this.password = "_NO_VALUE_".toCharArray();
        } else {
            this.password = password;
        }
    }

    public char[] getName() {
        return name;
    }

    public char[] getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
}