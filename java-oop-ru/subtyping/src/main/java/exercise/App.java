package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        for (Entry<String, String> keyValue : storage.toMap().entrySet()) {
            storage.set(keyValue.getValue(), keyValue.getKey());
            storage.unset(keyValue.getKey());
        }
    }
}
// END
