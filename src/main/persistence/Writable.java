package persistence;

import org.json.JSONObject;

public interface Writable {
    // code from JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
