package model;

import exceptions.EmptyNameException;

//Represents an account with a name, a login username, and a login password
public class Account {
    private char[] name;
    private char[] username;
    private char[] password;

    /*
     * REQUIRES: name must have non-zero length
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(char[] name, char[] username, char[] password) throws EmptyNameException {
        if (name.length == 0) {
            throw new EmptyNameException();
        }

        this.name = name;
        if (username.length == 0) {
            this.username = "_NO_VALUE_".toCharArray();
        } else {
            this.username = username;
        }
        if (password.length == 0) {
            this.password = "_NO_VALUE_".toCharArray();
        } else {
            this.password = password;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     */
    public void setName(char[] name) throws EmptyNameException {
        if (name.length == 0) {
            throw new EmptyNameException();
        }
        this.name = name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated username, assigns placeholder if passed in an empty array
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
     * EFFECTS: changes the account's associated password, assigns placeholder if passed in an empty array
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