import Model.Line;
import Model.Metro;
import Model.Station;
import Util.DoublyLinkedList;
import Util.JSONUtil;
import Util.LineDLL;
import Util.StringUtil;
import Error.IllegalNumberOfArgumentsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//        System.out.println(args.length);

        try {

            Metro metro = JSONUtil.importMetroFromJSON("src/test/" + args[0]);

//            JSONUtil.prettyPrintClasses("src/test/" + args[0]);
            /* Holds the lines as DLL objects */
            List<LineDLL<Station>> stationDLLList = new ArrayList<>();

            Station depot = new Station("depot");
            for (Line line : metro.getLines()) {
                List<Station> stations = line.getStations();
                /* Depot must begin and end the line */
                stations.add(0, depot);
                stations.add(stations.size(), depot);

                LineDLL<Station> stationDoublyLinkedList = new LineDLL<>(stations, line.getName());
                stationDLLList.add(stationDoublyLinkedList);
                System.out.println(stationDoublyLinkedList.getLineName());
            }

            for (DoublyLinkedList<Station> dll : stationDLLList) {
                System.out.println(dll);
            }


            Scanner scanner = new Scanner(System.in);
            String input;
            int state;

            do {
                input = scanner.nextLine();
                state = StringUtil.parseInputCommand(input);
                final String[] finalCommandArgs = new String[2];
                switch (state) {
                    case 0:
                        // append
                        System.out.println("append");
                        try {
                            StringUtil.extractArguments(0, input, finalCommandArgs);
                            stationDLLList.stream()
                                    .filter(stationDoublyLinkedList -> stationDoublyLinkedList.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().get().addLast(new Station(finalCommandArgs[1]));
                            break;
                        } catch (IllegalNumberOfArgumentsException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 1:
                        // output
                        System.out.println("output");
                        try {
                            StringUtil.extractArguments(1, input, finalCommandArgs);
                            System.out.println(
                                    stationDLLList.stream()
                                            .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                            .findFirst().get()
                            );
                            break;
                        } catch (IllegalNumberOfArgumentsException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 2:
                        // add-head
                        System.out.println("add-head");
                        try {
                            StringUtil.extractArguments(2, input, finalCommandArgs);
                            stationDLLList.stream()
                                    .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().get().addFirst(new Station(finalCommandArgs[1]));
                            break;
                        } catch (IllegalNumberOfArgumentsException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case 3:
                        // remove
                        System.out.println("remove");
                        try {
                            StringUtil.extractArguments(3, input, finalCommandArgs);
                            LineDLL<Station> line = stationDLLList.stream()
                                    .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().get();
                            line.remove(finalCommandArgs[1]);
                            break;
                        } catch (IllegalNumberOfArgumentsException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
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
