package persistence;

import model.AllUsers;
import model.User;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes JSON objects to file
//All the code in this class is heavily inspired by...
//SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String path;

    /*
     * EFFECTS: Constructs a new JsonWriter that writes a file to destination
     */
    public JsonWriter(String path) {
        this.path = path;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Opens a new writer and throws an account failed to open exception if path is not valid
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(path);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Closes the writer
     */
    public void close() {
        writer.close();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Writes the Json representation of AllUsers to the file system
     */
    public void write(AllUsers userData) {
        JSONObject json = userData.toJson();
        saveToFile(json.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS:Saves the string to file
     */
    public void saveToFile(String string) {
        writer.print(string);
    }
}
