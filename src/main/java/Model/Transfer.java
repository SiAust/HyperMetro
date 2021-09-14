package Model;

public class Transfer {

    private final String line;
    private final String station;

    public Transfer(String line, String station) {
        this.line = line;
        this.station = station;
    }

    public String getLine() {
        return line;
    }

    public String getStation() {
        return station;
    }

    @Override
    public String toString() {
        return station + " (" + line + ")";
    }

    public String toDebugString() {
        return "\n\tline: " + line + " station: " + station;
    }
}
