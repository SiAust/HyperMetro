package Model;

import java.util.Arrays;

public class Station {

    private final String name;
    private final Transfer[] transfer;

    public Station(String name, Transfer[] transfer) {
        this.name = name;
        this.transfer = transfer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\n\t\tStation: " + name
                + " Transfer: " + Arrays.toString(transfer);
    }
}
