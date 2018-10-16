package rectangles.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import rectangles.Constants;
import rectangles.IntersectingRectanglesException;
import rectangles.entities.Rectangle;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the Rectangle retrieving from external sources, uses the library Gson to parse JSON
 * Reads only Constants.MAX_RECTANGLES Rectangles without having to parse the whole file so it can deal with
 * any input file size
 */
public class RectangleInputReader {

    private Charset charset;

    public RectangleInputReader() {
        this(StandardCharsets.UTF_8);
    }

    public RectangleInputReader(Charset charset) {
        this.charset = charset;
    }

    /**
     * Tries to open a file and create a stream of it to parse its rectangles, the caller will have to decide what to do if the file
     * cannot be parsed.
     *
     * @param filename name of the JSON file
     * @return An array of rectangles parsed from the file
     * @throws IntersectingRectanglesException Gets thrown after something went wrong trying to handle the file
     */
    public List<Rectangle> getRectangleListFromFile(String filename) throws IntersectingRectanglesException {
        List<Rectangle> result = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(filename);
            result = getRectangleListFromStream(in);
            in.close();
            //If any error occurs during the file parsing, the program needs to throw its custom exception,
            //since the Intersection finding will not be possible
        } catch (FileNotFoundException e) {
            throw new IntersectingRectanglesException("File not found");
        } catch (IOException e) {
            throw new IntersectingRectanglesException("Error reading file");
        }
        return result;
    }

    public List<Rectangle> getRectangleListFromStream(InputStream in) throws IntersectingRectanglesException {
        List<Rectangle> result = new ArrayList<>();
        JsonReader reader = new JsonReader(new InputStreamReader(in, charset));
        try {
            //Reader start consuming the file
            reader.beginObject();
            //Reader tries to find the "rects" element
            boolean rectsFound = findRectangleArray(reader);
            //If the element is found it must be an array of Rectangles
            if (rectsFound) {
                reader.beginArray();
                result = parseRectangles(reader);
            }
            reader.close();
        } catch (IllegalStateException|JsonSyntaxException e) {
            throw new IntersectingRectanglesException("Error parsing JSON");
        } catch (IOException e) {
            throw new IntersectingRectanglesException("Error reading stream");
        }
        return result;
    }

    /**
     * Looks for a Rectangle list element in the input data, positions the reader at the start position of the element
     * if it is found
     *
     * @param reader An initialized JsonReader
     * @return true if Constants.RECT_LIST_NAME element exist
     * @throws IOException if there is an error traversing the json file
     */
    private boolean findRectangleArray(JsonReader reader) throws IOException {
        String name;
        while (reader.hasNext()) {
            name = reader.nextName();
            if (name.equals(Constants.RECT_LIST_NAME)) {
                return true;
            } else {
                reader.skipValue();
            }
        }
        return false;
    }

    /**
     * Uses a JsonReader to parse a Rectangle list from a JSON element's value
     * requires the reader to be at the list start position
     * @param reader A reader positioned at the beginning of an Rectangle list
     * @throws IOException if there is a problem parsing rectangles from the list
     */
    private List<Rectangle> parseRectangles(JsonReader reader) throws IOException {
        List<Rectangle> rectangles = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        int count = 1;
        while (reader.hasNext() && count <= Constants.MAX_RECTANGLES) {
            Rectangle rectangle = gson.fromJson(reader, Rectangle.class);
            //The specification states that every input rectangle will be valid, so it checks that
            //every rectangle follows that requirement
            if (rectangle.isValidRectangle()) {
                rectangle.setName(String.valueOf(count));
                rectangles.add(rectangle);
            }
            count++;
        }
        return rectangles;
    }
}
