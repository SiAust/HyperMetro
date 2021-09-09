package Error;

public class IllegalNumberOfArgumentsException extends Throwable {
    /**
     * Thrown when the number of expected arguments doesn't match
     * the given input arguments */
    public IllegalNumberOfArgumentsException(int expected, int actual) {
        super(String.format("Expected %d %s got %d", expected, expected > 1 ? "arguments" : "argument",  actual));

    }
}
