package Error;

public class StationNotFoundException extends Throwable {

    public StationNotFoundException(String station, String line) {
        super(String.format("Station \"%s\" was not found on the line \"%s\"", station, line));
    }
}
