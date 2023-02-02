package model;

import encryption.EncryptionUtil;
import org.json.JSONObject;
import persistence.Writable;
import javax.crypto.spec.IvParameterSpec;


//Represents an account with a name, a login username, and a login password
public class Account implements Writable {
    private String name;
    private String username;
    private String password;
    private final byte[] iv;
    private final String salt;

    /*
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(String name, String username, String password) {
        IvParameterSpec ivSpec = EncryptionUtil.generateIv();
        this.iv = ivSpec.getIV();
        this.name = name;
        this.salt = EncryptionUtil.generateRandomSalt();
        this.username = username;
        this.password = password;
    }

    public Account(String name, String username, String password, String salt, byte[] iv) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.iv = iv;
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes the account's associated name
     *          assigns placeholder if passed in an empty string
     */
    public void setName(String name) {
        this.name = name;
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

    public String getSalt() {
        return salt;
    }

    public byte[] getIV() {
        return iv;
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
        json.put("salt", salt);
        json.put("iv", iv);
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