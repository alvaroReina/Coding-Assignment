package rectangles.utils;

import rectangles.adapters.RectangleListAdapter;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import rectangles.entities.Rectangle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class RectangleInputReader {

    public static List<Rectangle> getRectangleListFromFile(String filename) throws FileNotFoundException {
        RectangleListAdapter rectangleListAdapter = readJsonFile(filename);
        return getRectangleList(rectangleListAdapter);
    }

    private static RectangleListAdapter readJsonFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        JsonReader jsonReader = new JsonReader(reader);
        RectangleListAdapter rectangles = gson.fromJson(jsonReader, RectangleListAdapter.class);
        return rectangles;
    }

    private static List<Rectangle> getRectangleList(RectangleListAdapter rectangleListAdapter) {
        return rectangleListAdapter.getRectangleList().stream().limit(10).collect(Collectors.toList());
    }


}
