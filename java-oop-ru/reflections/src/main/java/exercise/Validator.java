package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> annotatedNullFields = new ArrayList<>();

        Stream.of(fields).filter(field -> field.getAnnotation(NotNull.class) != null).forEach(field -> {
            field.setAccessible(true);
            try {
                if (field.get(address) == null) {
                    annotatedNullFields.add(field.getName());
                };
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return annotatedNullFields;
    }
}
// END
