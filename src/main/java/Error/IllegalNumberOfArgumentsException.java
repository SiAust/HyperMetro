package Error;

import java.util.Arrays;

public class IllegalNumberOfArgumentsException extends Throwable {
    /**
     * Thrown when the number of expected arguments doesn't match
     * the given input arguments */
    public IllegalNumberOfArgumentsException(int expected, int actual, String[] args) {
        super(String.format("Expected %d %s got %d - %s",
                expected,
                expected > 1 ? "arguments" : "argument",
                actual,
                Arrays.toString(args)));

    }
}
