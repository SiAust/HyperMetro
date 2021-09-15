import Util.StringUtil;
import Util.UserCommand;
import Error.IllegalNumberOfArgumentsException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringUtilTests {

    /* Test methodSource's. Creates an argument stream for input into test method.
    Allows use of parameterized tests with an index argument, useful for checking
    result against an array of expected results */

    // Test 1
    static Stream<Arguments> test1MethodSource() {
        List<String> testValues = List.of(
                "/append line station",
                "/output line",
                "/add-head line station",
                "/remove line station",
                "append line station",
                "output line",
                "add-head line station",
                "/removehead");
        return IntStream.range(0, testValues.size())
                .mapToObj(index -> Arguments.arguments(testValues.get(index), index));
    }

    // Test 2
    static final String[] resultTest2 = new String[2];
    static Stream<Arguments> test2MethodSource() {
        List<Integer> commandValue = List.of(
                UserCommand.OUTPUT,
                UserCommand.OUTPUT,
                UserCommand.REMOVE,
                UserCommand.REMOVE,
                UserCommand.REMOVE,
                UserCommand.REMOVE,
                UserCommand.ADD_HEAD,
                UserCommand.ADD_HEAD,
                UserCommand.ADD_HEAD,
                UserCommand.APPEND,
                UserCommand.APPEND,
                UserCommand.APPEND
        );

        List<String> testValues = List.of(
                "/output line",
                "/output \"line\"",

                "/remove line station",
                "/remove line \"station\"",
                "/remove \"line\" station",
                "/remove \"line\" \"station\"",

                "/add-head \"line\" \"station one\"",
                "/add-head \"line\" \"station one two\"",
                "/add-head \"line\" \"station one two three\"",

                "/append line \"station one\"",
                "/append line \"station one two\"",
                "/append line \"station one two three\"");

        return IntStream.range(0, testValues.size())
                .mapToObj(index -> Arguments.arguments(
                        commandValue.get(index),
                        testValues.get(index),
                        resultTest2,
                        index));
    }

    // Expect results for tests
    // Test 1
    int[] test1ExpectedResults = {0, 1, 2, 3, -1, -1, -1, -1};

    // Test 2
    String[] line = {"line", null};
    String[] lineStation = {"line", "station"};
    String[] lineStationOne = {"line", "station one"};
    String[] lineStationOneTwo = {"line", "station one two"};
    String[] lineStationOneTwoThree = {"line", "station one two three"};

    String[][] test2ExpectedResults = {
            line,
            line,
            lineStation,
            lineStation,
            lineStation,
            lineStation,
            lineStationOne,
            lineStationOneTwo,
            lineStationOneTwoThree,
            lineStationOne,
            lineStationOneTwo,
            lineStationOneTwoThree,
    };

    // Tests
    @DisplayName("Test 1: StringUtil.parseInputCommand")
    @ParameterizedTest
    @MethodSource("test1MethodSource")
    public void testParseInputCommands(String input, Integer index) {
        assertEquals(test1ExpectedResults[index], StringUtil.parseInputCommand(input));
    }

    @DisplayName("Test 2: StringUtil.extractArguments")
    @ParameterizedTest
    @MethodSource("test2MethodSource")
    public void testStringsWithQuoteMarks(int command, String string, String[] result, Integer index) {
        try {
            StringUtil.extractArguments(command, string, result);
        } catch (IllegalNumberOfArgumentsException e) {
            e.printStackTrace();
        }
        assertArrayEquals(test2ExpectedResults[index], resultTest2);
    }
}
