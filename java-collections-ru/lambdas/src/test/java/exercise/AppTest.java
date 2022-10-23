package exercise;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class ImageTest {
    @Test
    public void enlargeArrayImageTest() {
        String[][] image1 = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        String[][] enlargedImage1 = App.enlargeArrayImage(image1);
        String[][] expectedResult1 = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*",  " ", " ", " ", " ", "*", "*"},
                {"*", "*",  " ", " ", " ", " ", "*", "*"},
                {"*", "*",  " ", " ", " ", " ", "*", "*"},
                {"*", "*",  " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };

        assertThat(enlargedImage1).isEqualTo(expectedResult1);

        String[][] image2 = new String[0][];

        String[][] enlargedImage2 = App.enlargeArrayImage(image2);

        assertThat(enlargedImage2).isEqualTo(image2);

        String[][] image3 = {
                {"*", "*", "*"},
                {"*", " ", "*"},
                {"*", "*", "*"},
        };

        String[][] enlargedImage3 = App.enlargeArrayImage(image3);
        String[][] expectedResult3 = {
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*",  " ", " ", "*", "*"},
                {"*", "*",  " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
        };

        assertThat(enlargedImage3).isEqualTo(expectedResult3);

    }
}
// END
