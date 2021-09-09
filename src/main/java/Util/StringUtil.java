package Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Error.IllegalNumberOfArgumentsException;

public class StringUtil {


    public static int parseInputCommand(String input) {
        int state = -1;
        Pattern pattern;
        Matcher matcher;

        String[] commands = {"^/append.*", "^/output.*", "^/add-head.*", "^/remove.*"};

        if (input.startsWith("/")) {
            for (int i = 0; i < commands.length; i++) {
                pattern = Pattern.compile(commands[i]);
                matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    state = i;
                }
            }
        }
        return state;
    }

    /**
     * Extracts the arguments from the user input.
     * Arguments are copied to a final String[] for
     * use in lambda */
    public static void extractArguments(int command, String str, String[] finalCmdArgs)
            throws IllegalNumberOfArgumentsException {
        String[] result = null;
        switch (command) {
            case 0:
            case 1:
                // output
                result = splitArgs(str.substring(7));
                if (result.length > 1) {
                    throw new IllegalNumberOfArgumentsException(1, result.length);
                }
                break;
            case 2:
                // add-head
                result = splitArgs(str.substring(9));
                if (result.length != 2) {
                    throw new IllegalNumberOfArgumentsException(2, result.length);
                }
                break;
            case 3:
                // append, remove
                result = splitArgs(str.substring(7));
                if (result.length != 2) {
                    throw new IllegalNumberOfArgumentsException(2, result.length);
                }
                break;
        }
        assert result != null;
        System.arraycopy(result, 0, finalCmdArgs, 0, Objects.requireNonNull(result).length);
    }

    private static String[] splitArgs(String str) {
        /* Split into two strings. First string is the line, second
         * is the station */
        String[] strings = str.strip().split(" ", 2);

        /* Remove the quotes
         * If length < 1, only one argument, i.e "/output Hammersmith-and-city" */
        if (strings.length > 1 && strings[1].startsWith("\"") && strings[1].endsWith("\"")) {
            strings[1] = strings[1].substring(1, strings[1].length() - 1);
        }

//        System.out.println(Arrays.toString(strings));

        return strings;
    }
}
