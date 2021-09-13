package Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Error.IllegalNumberOfArgumentsException;

public class StringUtil {

    static final String[] COMMANDS = {"^/append.*", "^/output.*", "^/add-head.*", "^/remove.*", "^/connect.*"};

    public static int parseInputCommand(String input) {
        int state = -1;
        Pattern pattern;
        Matcher matcher;

        if (input.startsWith("/")) {
            for (int i = 0; i < COMMANDS.length; i++) {
                pattern = Pattern.compile(COMMANDS[i]);
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
            case 3:
                // append, remove
                result = splitArgs(str.substring(7));
                if (result.length != 2) {
                    throw new IllegalNumberOfArgumentsException(2, result.length, result);
                }
                break;
            case 1:
                // output
                result = splitArgs(str.substring(7));
                if (result.length > 1) {
                    throw new IllegalNumberOfArgumentsException(1, result.length, result);
                }
                break;
            case 2:
                // add-head
                result = splitArgs(str.substring(9));
                if (result.length != 2) {
                    throw new IllegalNumberOfArgumentsException(2, result.length, result);
                }
                break;
            case 4:
                // connect
                result = splitArgs(str.substring(8));
                if (result.length != 4) {
                    throw new IllegalNumberOfArgumentsException(4, result.length, result);
                }
                break;

        }
        assert result != null;
        System.arraycopy(result, 0, finalCmdArgs, 0, Objects.requireNonNull(result).length);
    }

    private static String[] splitArgs(String str) {
        /* Holds the resulting arguments */
        List<String> result = new ArrayList<>();

        /* Matches a word whether it is surrounded by quotes or not */
        String regex = "([\\w-]+)|(\"[\\s\\w]+\")";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            result.add(matcher.group().replaceAll("\"", ""));
        }

        System.out.println(result);

        return result.toArray(String[]::new);
    }
}
