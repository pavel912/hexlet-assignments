package exercise;

import java.util.List;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeEmails = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .filter(email -> StringUtils.isNotBlank(email))
                .filter(email -> freeEmails.contains(email.split("@")[1])) // assume the correct format
                .count();
    }
}
// END
