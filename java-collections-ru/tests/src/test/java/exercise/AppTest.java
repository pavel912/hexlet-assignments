package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> testData = new ArrayList<>();
        testData.add(1);
        testData.add(0);
        testData.add(-255);
        testData.add(312312);
        testData.add(13);
        testData.add(7000);

        assertThat(App.take(testData, 1)).isEqualTo(testData.subList(0, 1));

        assertThat(App.take(testData, 0)).isEqualTo(testData.subList(0, 0));

        assertThat(App.take(testData, 10)).isEqualTo(testData.subList(0, testData.size()));

        assertThat(App.take(testData, 6)).isEqualTo(testData.subList(0, testData.size()));
        // END
    }
}
