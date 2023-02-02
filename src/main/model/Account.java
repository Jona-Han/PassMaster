package model;

import encryption.Encryption;
import encryption.EncryptionUtil;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import persistence.Writable;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;
import java.util.Objects;


//Represents an account with a name, a login username, and a login password
public class Account implements Writable {
    private String name;
    private String username;
    private String password;
    private final byte[] iv;
    private SecretKey key;

    /*
     * EFFECTS: constructs an Account object with specified fields
     */
    public Account(String name, String username, String password) {
        IvParameterSpec ivSpec = EncryptionUtil.generateIv();
        this.iv = ivSpec.getIV();
        this.name = name;
        this.key = EncryptionUtil.generateSecretKey(password, EncryptionUtil.generateRandomSalt());
        this.username = username;
        this.password = password;
    }

    public Account(String name, String username, String password, SecretKey key, byte[] iv) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.key = key;
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

    public SecretKey getSecretKey() {
        return key;
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
        json.put("key", Base64.encodeBase64String(key.getEncoded()));
        json.put("iv", Base64.encodeBase64String(iv));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return name.equals(account.name) && Objects.equals(username, account.username)
                && Objects.equals(password, account.password) && Arrays.equals(iv, account.iv)
                && key.equals(account.key);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, username, password, key);
        result = 31 * result + Arrays.hashCode(iv);
        return result;
    }
}