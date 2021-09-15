package Model;

import java.util.List;
import java.util.stream.Collectors;

public class Line {

    private final String name;
    private final List<Station> stations;

    public Line(String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toDebugString() {
        return "\nLine: " + name + '\n' +
                "\tStations:" + stations.stream().map(Station::toDebugString)
                .collect(Collectors.joining());
    }
}
