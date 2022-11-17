package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents an account with a name, a login username, and a login password
public class Account implements Writable {
    private String name;
    private String username;
    private String password;

    /*
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(String name, String username, String password) {
        this.name = name;
        if (username.length() == 0) {
            this.username = "_NO_VALUE_";
        } else {
            this.username = username;
        }
        if (password.length() == 0) {
            this.password = "_NO_VALUE_";
        } else {
            this.password = password;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated username, assigns placeholder if passed in an empty array
     */
    public void setUsername(String username) {
        if (username.length() == 0) {
            this.username = "_NO_VALUE_";
        } else {
            this.username = username;
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated password, assigns placeholder if passed in an empty array
     */
    public void setPassword(String password) {
        if (password.length() == 0) {
            this.password = "_NO_VALUE_";
        } else {
            this.password = password;
        }
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /*
     * EFFECTS: Returns a Json version of this
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("username", username);
        json.put("password", password);
        return json;
    }

    @Override
    public String toString() {
        return "<html><div style=\"font-weight:bold;font-size:12px\">" + name
                + "</div><div style=\"font-size:8px\">" + username + "</div></html>";
    }
}