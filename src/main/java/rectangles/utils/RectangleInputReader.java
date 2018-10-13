package rectangles.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import rectangles.entities.Rectangle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static rectangles.utils.RectangleUtils.checkValidRectangle;

public class RectangleInputReader {

    public static List<Rectangle> getRectangleListFromFile(String filename) throws IOException {

        Logger logger = Logger.getLogger(RectangleInputReader.class.getName());
        List<Rectangle> result = new ArrayList<>();

        FileInputStream in = new FileInputStream(filename);
        JsonReader reader = new JsonReader(new InputStreamReader(in));
        try {
            reader.beginObject();
            if (findRectangleArray(reader)) {
                reader.beginArray();
                parseRectangles(reader, result);
            }
        } catch (IllegalStateException | JsonSyntaxException e) {
            logger.warning("Malformed JSON. Returning an empty array");
            return new ArrayList<>();
        }
        in.close();
        reader.close();
        return result;
    }

    private static boolean findRectangleArray(JsonReader reader) throws IOException {
        String name;
        while (reader.hasNext()) {
            name = reader.nextName();
            if (name.equals("rects")) {
                return true;
            } else {
                reader.skipValue();
            }
        }
        return false;
    }

    private static void parseRectangles(JsonReader reader, List<Rectangle> rectangles) throws IOException {
        Gson gson = new GsonBuilder().create();
        int count = 0;
        while (reader.hasNext() && count < 10) {
            Rectangle rectangle = gson.fromJson(reader, Rectangle.class);
            if (checkValidRectangle(rectangle)) {
                rectangles.add(rectangle);
            }
            count++;
        }
    }
}
