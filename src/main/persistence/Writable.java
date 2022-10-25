package persistence;

import org.json.JSONObject;


//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents an object that is writeable to file
public interface Writable {
    //Returns this as JSON object
    JSONObject toJSON();
}
