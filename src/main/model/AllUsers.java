package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Singleton pattern - contains all the users of the application
public class AllUsers implements Writable, Iterable<User> {
    private static AllUsers singleton;
    private List<User> users = new ArrayList<>();
    private AllUsers() {}
    public static AllUsers getInstance() {
        if (singleton == null) {
            singleton = new AllUsers();
        }
        return singleton;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public User get(int index) {
        return users.get(index);
    }

    public int size() {
        return users.size();
    }

    public void clear() {
        users.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            jsonArray.put(user.toJson());
        }

        JSONObject json = new JSONObject();
        json.put("data", jsonArray);
        return json;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
