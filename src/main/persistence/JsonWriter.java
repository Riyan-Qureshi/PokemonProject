package persistence;

import model.Challenger;
import model.Trainer;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representations of objects to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: takes trainer and challenger objects and stores them as data in json file
    public void writeAll(Trainer trainerState, Challenger challengerState) {
        JSONObject jsonTrainer = trainerState.toJson();
        JSONObject jsonChallenger = challengerState.toJson();

        JSONArray jsonCharacters = new JSONArray();
        jsonCharacters.put(jsonTrainer);
        jsonCharacters.put(jsonChallenger);

        saveToFile(jsonCharacters.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
