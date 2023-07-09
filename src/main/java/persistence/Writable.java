package persistence;

import org.json.JSONObject;

//Represents an interface to an object that is writeable to file
public interface Writable {
    //Returns this as JSON object
    JSONObject toJson();
}
