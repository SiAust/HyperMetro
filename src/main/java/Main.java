import Model.Station;
import Util.DoublyLinkedList;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//        System.out.println(args.length);

        try {
            File file = new File("src/" + args[0]);
            Scanner scanner = new Scanner(file);

            DoublyLinkedList<Station> stationDoublyLinkedList = new DoublyLinkedList<>();

            /* Add stations to DLL */
            Station depot = new Station("depot");
            stationDoublyLinkedList.addFirst(depot);
            while (scanner.hasNext()) {
                String station = scanner.nextLine();
//                System.out.println(station);

                stationDoublyLinkedList.addLast(new Station(station));
            }
            stationDoublyLinkedList.addLast(depot);

            System.out.println(stationDoublyLinkedList);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! Such file doesn't exist!");
        }
    }
}
