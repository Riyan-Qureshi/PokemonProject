package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns object as JSON object
    JSONObject toJson();
}
