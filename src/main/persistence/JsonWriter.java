package persistence;

import model.StudyLog;
import model.StudySubject;
import org.json.JSONObject;


import java.io.*;

// inspired by JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // inspired by JsonSerializationDemo
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(StudyLog sl) {
        JSONObject json = sl.toJson();
        saveToFile(json.toString(TAB));
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // inspired by JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
