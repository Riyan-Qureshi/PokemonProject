package persistence;

import org.json.JSONObject;

// Represents an interface that can be used to declare a class as Writable to file
public interface Writable {
    // EFFECTS: returns object as JSON object
    JSONObject toJson();
}
