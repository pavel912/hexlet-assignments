package exercise;

import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordsCount = new HashMap<String, Integer>();

        for (String word: sentence.split(" ")) {
            if (word.length() == 0) {
                continue; // "" empty string is not a word (probably)
            }

            wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
        }

        return wordsCount;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Map.Entry<String, Integer> word: wordsCount.entrySet()) {
            stringBuilder.append("\n\s\s");
            stringBuilder.append(word.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(word.getValue());
        }

        stringBuilder.append("\n}");

        return stringBuilder.toString();
    }
}
//END
