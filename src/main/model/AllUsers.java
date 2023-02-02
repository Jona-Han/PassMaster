package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Singleton pattern - contains all the users of the application
public class AllUsers implements Writable, Iterable<User> {
    private static AllUsers singleton;
    private Set<User> users = new HashSet<>();
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
