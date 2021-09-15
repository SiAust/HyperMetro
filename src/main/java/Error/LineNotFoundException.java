package Error;

public class LineNotFoundException extends Throwable {
    public LineNotFoundException(String line) {
        super(String.format("Line \"%s\" not found in metro", line));
    }
}
