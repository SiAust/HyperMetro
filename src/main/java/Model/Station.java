package Model;

import java.util.Arrays;

public class Station {

    private final String name;
    private Transfer[] transfer;

    /** Creates a Station with Transfer lines. Used during
     * deserialization of .json file */
//    public Station(String name, Transfer[] transfer) {
//        this.name = name;
//        this.transfer = transfer;
//    }

    /** Creates a Station without any transfer lines */
    public Station(String name) {
        this.name = name;
        this.transfer = new Transfer[0];
    }

    public String getName() {
        return name;
    }

    public void addTransfer(String line, String station) {
        Transfer[] temp = new Transfer[transfer.length + 1];
        temp = Arrays.copyOf(transfer, temp.length);
        temp[temp.length -1] = new Transfer(line, station);
        transfer = temp;
//        System.out.println(Arrays.toString(transfer));
    }

    @Override
    public String toString() {
        StringBuilder transferSB = new StringBuilder();
        Arrays.stream(transfer).forEach(transferSB::append);
        return name + (transfer.length > 0 ? " - " + transferSB : "");
    }

    /** Print verbose info about Station to the console.
     * Formatted for readability. */
    public String toDebugString() {
        return "\n\t\tStation: " + name
                + " Transfer: " + Arrays.toString(transfer);
    }
}
