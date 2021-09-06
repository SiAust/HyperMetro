import Model.Line;
import Model.Metro;
import Model.Station;
import Util.DoublyLinkedList;
import Util.JSONUtil;
import Util.LineDLL;
import Util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//        System.out.println(args.length);

        try {

            Metro metro = JSONUtil.importMetroFromJSON("src/test/" + args[0]);

//            JSONUtil.prettyPrintClasses("src/test/" + args[0]);

            List<DoublyLinkedList<Station>> stationDLLList = new ArrayList<>();
            Station depot = new Station("depot");
            for (Line line : metro.getLines()) {
                List<Station> stations = line.getStations();
                stations.add(0, depot);
                stations.add(stations.size(), depot);
                DoublyLinkedList<Station> stationDoublyLinkedList = new LineDLL<>(line.getStations());
                stationDLLList.add(stationDoublyLinkedList);
            }

            for (DoublyLinkedList<Station> dll : stationDLLList) {
                System.out.println(dll);
            }



            Scanner scanner = new Scanner(System.in);
            String input;
            int state;
            String[] commandArgs = null;
            do {
                input = scanner.nextLine();
                state = StringUtil.parseInputCommand(input);
                switch (state) {
                    case 0:
                        // append
                        System.out.println("append");
                        commandArgs = StringUtil.extractArguments(0, input);

                        break;
                    case 1:
                        // output
                        System.out.println("output");
                        break;
                    case 2:
                        // add-head
                        System.out.println("add-head");
                        break;
                    case 3:
                        // remove
                        System.out.println("remove");
                        break;
                    case -1:
                        // exit
                        System.out.println("exit");
                        break;
                }
            } while (state != -1);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! Such file doesn't exist!");
        }
    }

}
