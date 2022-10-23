package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
class App {
    public static boolean scrabble(String symbols, String word) {
        ArrayList<String> symbolsArray = new ArrayList<>(Arrays.asList(symbols.toLowerCase().split("")));

        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1).toLowerCase();

            if (!symbolsArray.contains(letter)) {
                return false;
            }

            symbolsArray.remove(letter);
        }
        return true;
    }
}
//END
