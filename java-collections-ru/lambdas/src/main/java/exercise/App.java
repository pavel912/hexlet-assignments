package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return new String[0][];
        }
        return Stream
                .of(image)
                .flatMap(x -> Stream.of(x, x))
                .map(x -> Stream.of(x).flatMap(y -> Stream.of(y, y)).toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
// END
