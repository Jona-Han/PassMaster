package model;

import org.json.JSONObject;
import persistence.Writable;

public class User implements Writable {
    private char[] username;
    private char[] password;

    /*
     * EFFECTS: constructs a User with specified fields
     */
    public User(char[] username, char[] password) {
        this.username = username;
        this.password = password;
    }


    public char[] getMasterUsername() {
        return username;
    }

    public char[] getMasterPassword() {
        return password;
    }

    public void setMasterUsername(char[] username) {
        this.username = username;
    }

    public void setMasterPassword(char[] password) {
        this.password = password;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        return json;
    }
}
