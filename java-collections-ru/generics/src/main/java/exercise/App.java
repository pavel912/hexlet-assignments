package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {
    public static <K, V> List<Map<K, V>> findWhere(List<Map<K, V>> objects, Map<K, V> where) {
        List<Map<K, V>> matchingObjects = new ArrayList<>();

        for (Map<K, V> object: objects) {
            if (isMatchingObject(object, where)) {
                matchingObjects.add(object);
            }
        }

        return matchingObjects;
    }

    public static <K, V> boolean isMatchingObject(Map<K, V> object, Map<K, V> where) {
        for (Map.Entry<K, V> whereCondition: where.entrySet()) {
            if (!object.get(whereCondition.getKey()).equals(whereCondition.getValue())) {
                return false;
            }
        }

        return true;
    }
}
//END
