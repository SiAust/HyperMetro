import Model.Line;
import Model.Metro;
import Model.Station;
import Util.UserCommand;

import Util.DoublyLinkedList;
import Util.JSONUtil;
import Util.LineDLL;
import Util.StringUtil;

import Error.IllegalNumberOfArgumentsException;
import Error.LineNotFoundException;
import Error.StationNotFoundException;

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

            /* Prints the metro state to the console */
            if (Arrays.stream(args).filter(arg -> arg.contains("--debug")).count() == 1) {
                System.out.println(metro.toDebugString());
            }

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
//                System.out.println(stationDoublyLinkedList.getLineName());
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
                final String[] finalCommandArgs = new String[4];

                try {

                    LineDLL<Station> line;

                    switch (state) {
                        case 0:
                            // append
                            System.out.println("append");
                            StringUtil.extractArguments(
                                    UserCommand.APPEND,
                                    input,
                                    finalCommandArgs);
                            line = stationDLLList.stream()
                                    .filter(stationDoublyLinkedList ->
                                            stationDoublyLinkedList.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().orElse(null);
                            if (line != null) {
                                line.addLast(new Station(finalCommandArgs[1]));
                            } else {
                                throw new LineNotFoundException(finalCommandArgs[0]);
                            }
                            break;
                        case 1:
                            // output
                            System.out.println("output");
                            StringUtil.extractArguments(
                                    UserCommand.OUTPUT,
                                    input,
                                    finalCommandArgs);

                            line = stationDLLList.stream()
                                    .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().orElse(null);
                            if (line != null) {
                                System.out.println(line);
                            } else {
                                throw new LineNotFoundException(finalCommandArgs[0]);
                            }
                            break;
                        case 2:
                            // add-head
                            System.out.println("add-head");
                            StringUtil.extractArguments(
                                    UserCommand.ADD_HEAD,
                                    input,
                                    finalCommandArgs);
                            line = stationDLLList.stream()
                                    .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().orElse(null);
                            if (line != null) {
                                line.addFirst(new Station(finalCommandArgs[1]));
                            } else {
                                throw new LineNotFoundException(finalCommandArgs[0]);
                            }
                            break;
                        case 3:
                            // remove
                            System.out.println("remove");
                            StringUtil.extractArguments(
                                    UserCommand.REMOVE,
                                    input,
                                    finalCommandArgs);
                            line = stationDLLList.stream()
                                    .filter(stationLineDLL -> stationLineDLL.getLineName().equals(finalCommandArgs[0]))
                                    .findFirst().orElse(null);
                            if (line != null) {
                                line.remove(finalCommandArgs[1]);

                            } else {
                                throw new LineNotFoundException(finalCommandArgs[0]);
                            }
                            break;
                        case 4:
                            // connect
                            StringUtil.extractArguments(
                                    UserCommand.CONNECT,
                                    input,
                                    finalCommandArgs);
                            // Update stations with transfer
                            for (int i = 0, j = finalCommandArgs.length -1; i < 3; i += 2, j -= 2) {
                                int finalI = i;
                                line = stationDLLList.stream()
                                        .filter(stationLineDLL -> stationLineDLL.getLineName()
                                                .equals(finalCommandArgs[finalI]))
                                        .findFirst().orElse(null);
                                if (line != null) {
                                    Station stationToUpdate = line.findStation(finalCommandArgs[finalI + 1]);
                                    stationToUpdate.addTransfer(finalCommandArgs[j - 1], finalCommandArgs[j]);
                                } else {
                                    throw new LineNotFoundException(finalCommandArgs[finalI]);
                                }
                            }
                            break;
                        case -1:
                            // exit
                            System.out.println("exit");
                            break;
                    }

                } catch (IllegalNumberOfArgumentsException | LineNotFoundException
                        | StationNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } while (state != -1);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! Such file doesn't exist!");
        }
    }

}
