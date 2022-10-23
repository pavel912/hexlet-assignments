package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.StringJoiner;

// BEGIN
class App {
    public static String getForwardedVariables(String config) {
        StringJoiner stringJoiner = new StringJoiner(",");

        Stream.of(config.split("\n"))
                .filter(x -> x.startsWith("environment="))
                .flatMap(x -> Stream.of(x.split("environment=")))
                .flatMap(x -> Stream.of(x.split(",")))
                .flatMap(x -> Stream.of(x.split("\""))) //all splits can be replaced with one regex probably
                .filter(x -> x.startsWith("X_FORWARDED_"))
                .map(x -> x.substring("X_FORWARDED_".length()))
                .forEachOrdered(stringJoiner::add);

        return stringJoiner.toString();
    }
}
//END
