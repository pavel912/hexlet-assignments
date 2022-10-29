package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final Map<String, String> storage;
    private final String filepath;

    public FileKV(String filepath, Map<String, String> storage) {
        this.filepath = filepath;
        this.storage = new HashMap<>(storage);
        Utils.writeFile(filepath, Utils.serialize(this.storage));
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(this.storage));
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        Utils.writeFile(filepath, Utils.serialize(this.storage));
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }
}
// END
