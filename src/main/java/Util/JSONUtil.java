package Util;

import Model.Line;
import Model.Metro;
import Model.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class JSONUtil {

    public static Metro importMetroFromJSON(String path) {
        Metro metro = null;
        try {
            Gson gson = new Gson();

            /* Parse JSON from file */
            Reader reader = Files.newBufferedReader(Paths.get(path));
            /* Initial json object */
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

//            System.out.println(jsonObject.keySet());

            /* Line names */
            Set<String> lines = jsonObject.keySet();
            List<Line> linesList = new ArrayList<>();

            // Create a set which is ordered by int value
            Set<String> stationsSet = new TreeSet<>(Comparator.comparingInt(Integer::parseInt));
            List<Station> stationsList = new ArrayList<>();

            for (String line : lines) {
//                System.out.println(jsonObject.getAsJsonObject(line));
                JsonObject lineObj = jsonObject.getAsJsonObject(line);

                stationsSet.addAll(lineObj.keySet());
//                System.out.println(stationsSet);
                // Iterate over the names of the stations and create a new Station
                for (String station : stationsSet) {
                    Station tempStation = gson.fromJson(lineObj.get(station).getAsJsonObject(), Station.class);
                    stationsList.add(tempStation);
                }

                Line tempLine = new Line(line, stationsList);
                linesList.add(tempLine);
                stationsList = new ArrayList<>();
                // Clear the set before next iteration
                stationsSet.clear();
            }

            metro = new Metro(linesList);
            reader.close();

//            System.out.println(metro.toDebugString());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return metro;
    }

    public static void prettyPrintClasses(Metro metro) {
        try {
//            List<Station> stations = List.of(new Station("hammersmith"), new Station("waterloo"));
//            Line line1 = new Line("m1", stations);
//
//            List<Station> stations1 = List.of(new Station("testStation1"), new Station("testStation2"));
//            Line line2 = new Line("m2", stations1);
//
//
//            List<Line> lines = List.of(line1, line2);
//            Metro metro = new Metro(lines);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("test-output.json"));
            gson.toJson(metro, writer);
            writer.close();
            System.out.println("JSON file written to disk");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
