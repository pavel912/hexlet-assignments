package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return annotatedNullFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> keyToErrors = new HashMap<>();

        Stream.of(fields).forEach(field -> {
            List<String> errors = new ArrayList<>();
            if (field.getAnnotation(NotNull.class) != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        errors.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            MinLength minLength = field.getAnnotation(MinLength.class);
            if (minLength != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null || field.get(address).toString().length() < minLength.minLength()) {
                        errors.add(String.format("length less than %d", minLength.minLength()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (errors.size() > 0) {
                keyToErrors.put(field.getName(), errors);
            }
        });

        return keyToErrors;
    }
}
// END
