package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String[] extractArguments(int command, String str) {
        String[] result = null;
        switch (command) {
            case 0:
            case 1:
            case 3:
                // append, output, remove
                result = splitArgs(str.substring(7));
                break;
            case 2:
                // add-head
                result = splitArgs(str.substring(8));
                break;

        }
        return result;
    }

    private static String[] splitArgs(String str) {
        // Hammersmith-and-City
        // Hammersmith-and-City "Test station"
        // Hammersmith-and-City Hammersmith
        /* Split into two strings. First string is the line, second
        * is the station */
        String[] strings = str.strip().split(" ", 2);

        /* Remove the quotes
        * If length < 1, only one argument, i.e "/output Hammersmith-and-city" */
        if (strings.length > 1 && strings[1].startsWith("\"") && strings[1].endsWith("\"")) {
            strings[1] = strings[1].substring(1, strings[1].length() - 1);
        }

        System.out.println(Arrays.toString(strings));

        return strings;
    }
}
