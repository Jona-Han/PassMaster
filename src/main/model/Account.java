package model;

import org.json.JSONObject;
import persistence.Writable;
import ui.PasswordManagerApp;

//Represents an account with a name, a login username, and a login password
public class Account implements Writable {
    private String name;
    private String username;
    private String password;

    /*
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(String name, String username, String password) {
        if (name.length() == 0) {
            this.name = "_NO_VALUE_";
        } else {
            this.name = name;
        }
        this.username = username;
        this.password = password;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     *          assigns placeholder if passed in an empty string
     */
    public void setName(String name) {
        if (name.length() == 0) {
            this.name = "_NO_VALUE_";
        } else {
            this.name = name;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated password
     */
    public void setPassword(String password) {
        this.password = password;
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

    /*
     * EFFECTS: Returns a HTML formatted String representing an account
     */
    @Override
    public String toString() {
        return "<html><div style=\"font-size:12px;font-weight:bold;\">" + name
                + "</div><div style=\"font-size:8px;\">" + username + "</div></html>";
    }
}