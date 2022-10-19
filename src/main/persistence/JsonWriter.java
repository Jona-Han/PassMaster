package persistence;

import model.CollectionOfAccounts;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private PrintWriter writer;
    private String path;

    /*
     * EFFECTS: Constructs a new JsonWriter that writes a file to destination
     */
    public void JsonWriter(String path) {
        this.path = path;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Opens a new writer and throws an account failed to open exception if path is not valid
     */
    public void open(String path) throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
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
     * EFFECTS: Writes the Json representation of CollectionOfAccounts to the file system
     */
    public void write(CollectionOfAccounts collection) {
        JSONObject json = collection.toJson();
        saveToFile(json.toString());
    }

    /*
     * MODIFIES: this
     * EFFECTS:Saves the string to file
     */
    public void saveToFile(String file) {}

}
