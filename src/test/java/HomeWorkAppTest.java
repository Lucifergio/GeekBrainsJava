import LessonTestAndLog.HomeWorkApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HomeWorkAppTest {
    private static HomeWorkApp hwa;

    @BeforeEach
    public void init () {
        hwa = new HomeWorkApp();
    }

    @ParameterizedTest
    @MethodSource("dataToTestArrayCopy")
    public void testArrayCopy (int[] arr, int[] result) {
        Assertions.assertArrayEquals(result, hwa.testArrayCopy(arr));
    }

    @ParameterizedTest
    @MethodSource("dataToTestCheckArrayNum")
    public void testCheckArrayNum (int[] arr, boolean result) {
        Assertions.assertEquals(result, hwa.testCheckArrayNum(arr));
    }

    public static Stream<Arguments> dataToTestArrayCopy() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{2, 3, 5, 4, 1, 2}, new int[]{1, 2}));
        out.add(Arguments.arguments(new int[]{2, 2, 2, 4, 7}, new int[]{7}));
        out.add(Arguments.arguments(new int[]{1, 2, 3, 4, 5, 1, 2}, new int[]{5, 1, 2}));
        out.add(Arguments.arguments(new int[]{10, 10, 1, 2, 5}, new int[]{2, 5})); //Кинет исключение.
        return out.stream();
    }

    public static Stream<Arguments> dataToTestCheckArrayNum() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{1, 1, 1, 4}, true));
        out.add(Arguments.arguments(new int[]{1, 2, 1, 1, 4}, false));
        out.add(Arguments.arguments(new int[]{1, 1, 4}, true));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 1}, false));
        return out.stream();
    }
}
