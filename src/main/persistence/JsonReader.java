package persistence;

import model.Account;
import model.CollectionOfAccounts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads JSON objects from file
public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads a collection of accounts from the file at destination source
     */
    public CollectionOfAccounts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollectionOfAccounts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: Parses a collection of accounts from jsonObject and returns it
     */
    private CollectionOfAccounts parseCollectionOfAccounts(JSONObject jsonObject) {
        CollectionOfAccounts accounts = new CollectionOfAccounts();
        addAccounts(accounts, jsonObject);
        return accounts;
    }

    /*
     * MODIFIES: CollectionOfAccounts
     * EFFECTS: Parses Accounts from jsonObject and adds them to the collection of accounts
     */
    private void addAccounts(CollectionOfAccounts accounts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");

        for (Object jsonAccount : jsonArray) {
            JSONObject nextAccount = (JSONObject) jsonAccount;
            addAccount(accounts, nextAccount);
        }
    }

    /*
     * MODIFIES: CollectionOfAccounts
     * EFFECTS: Parses an account from jsonObject and adds it to the collection of accounts
     */
    private void addAccount(CollectionOfAccounts accounts, JSONObject jsonObject) {
        char[] name = jsonObject.getString("name").toCharArray();
        char[] username = jsonObject.getString("username").toCharArray();
        char[] password = jsonObject.getString("password").toCharArray();
        accounts.add(new Account(name, username, password));
    }
}
