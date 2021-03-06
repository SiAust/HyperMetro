package Model;

import java.util.List;
import java.util.stream.Collectors;

public class Metro {

    private final List<Line> lines;

    public Metro(List<Line> lines) {
        this.lines = lines;
    }

    public int getNumberOfStations() {
        return lines.size();
    }

    public List<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return lines.toString();
    }

    public String toDebugString() {
        return lines.stream()
                .map(Line::toDebugString)
                .collect(Collectors.joining()) + "\n";
    }
}
