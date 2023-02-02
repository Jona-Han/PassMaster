package persistence;

import model.Account;
import model.AllUsers;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads JSON objects from file
//All the code in this class is heavily inspired by...
//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private final String source;

    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads a collection of accounts from the file at destination source
     */
    public void read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseAllUsers(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: Parses a collection of accounts from jsonObject and returns it
     */
    private void parseAllUsers(JSONObject jsonObject) {
        JSONArray jsonUsers = jsonObject.getJSONArray("data");
        AllUsers allUsers = AllUsers.getInstance();
        for (Object jsonUser : jsonUsers) {
            JSONObject nextUser = (JSONObject) jsonUser;
            String username = nextUser.getString("username");
            String passwordHash = nextUser.getString("passwordHash");
            User newUser = new User(username, passwordHash);
            addAccounts(newUser, nextUser);
            allUsers.addUser(newUser);
        }

    }

    /*
     * MODIFIES: CollectionOfAccounts
     * EFFECTS: Parses Accounts from jsonObject and adds them to the collection of accounts
     */
    private void addAccounts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");

        for (Object jsonAccount : jsonArray) {
            JSONObject nextAccount = (JSONObject) jsonAccount;
            addAccount(user, nextAccount);
        }
    }

    /*
     * MODIFIES: CollectionOfAccounts
     * EFFECTS: Parses an account from jsonObject and adds it to the collection of accounts
     */
    private void addAccount(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String salt = jsonObject.getString("salt");
        byte[] iv = (byte[]) jsonObject.get("iv");
        user.add(new Account(name, username, password, salt, iv));
    }
}
