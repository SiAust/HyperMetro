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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JSONUtil {

    public static Metro importMetroFromJSON(String path) {
        Metro metro = null;
        try {
            /* Parse JSON from file */
//            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));

            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

//            System.out.println(jsonObject.keySet());

            /* Line names */
            Set<String> lines = jsonObject.keySet();
            List<Line> linesList = new ArrayList<>();

            Set<String> stationsSet;
            List<Station> stationsList = new ArrayList<>();
            for (String line : lines) {
//                System.out.println(jsonObject.getAsJsonObject(line));
                JsonObject stationObj = jsonObject.getAsJsonObject(line);
                stationsSet = jsonObject.getAsJsonObject(line).keySet();
                for (String station : stationsSet) {
                    stationsList.add(new Station(stationObj.get(station).getAsString()));
                }
                Line tempLine = new Line(line, stationsList);
                linesList.add(tempLine);
                stationsList = new ArrayList<>();
            }

            metro = new Metro(linesList);
            reader.close();

//            System.out.println(linesList);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return metro;
    }

    public static void prettyPrintClasses(String path) {
        try {
            List<Station> stations = List.of(new Station("hammersmith"), new Station("waterloo"));
            Line line1 = new Line("m1", stations);

            List<Station> stations1 = List.of(new Station("testStation1"), new Station("testStation2"));
            Line line2 = new Line("m2", stations1);


            List<Line> lines = List.of(line1, line2);
            Metro metro = new Metro(lines);

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
