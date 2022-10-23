package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static <L, R> LinkedHashMap<String, String> genDiff(Map<String, L> leftMap, Map<String, R> rightMap) {
        return Stream.concat(leftMap.keySet().stream(), rightMap.keySet().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toMap(x -> x, x -> {
                    if (leftMap.containsKey(x) && !rightMap.containsKey(x)) {
                        return "deleted";
                    } else if (!leftMap.containsKey(x) && rightMap.containsKey(x)) {
                        return "added";
                    } else if (!leftMap.get(x).equals(rightMap.get(x))) {
                        return "changed";
                    } else {
                        return "unchanged";
                    }
                }, (u, v) -> u, LinkedHashMap::new));
    }
}
//END
