package rectangles;

import rectangles.entities.Rectangle;
import rectangles.services.IntersectionFinder;
import rectangles.services.IntersectionOutput;
import rectangles.services.StandardOutput;
import rectangles.utils.RectangleInputReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) throws IntersectingRectanglesException {
        Logger logger = Logger.getLogger(Application.class.getName());
        String fileName;

        if (args.length == 1) {
            fileName = args[0];
        } else {
            throw new IllegalArgumentException("Usage: (filename)");
        }

        List<Rectangle> rectangles = new ArrayList<>();

        try {
            rectangles = RectangleInputReader.getRectangleListFromFile(fileName);
        } catch (FileNotFoundException e) {
            logger.severe("File not found.");
            throw new IntersectingRectanglesException();
        } catch (IOException e) {
            logger.severe("Error reading file.");
            throw new IntersectingRectanglesException();
        }

        IntersectionOutput out = new StandardOutput();
        IntersectionFinder intersectionFinder = new IntersectionFinder(rectangles, out);
        intersectionFinder.findIntersections();
    }
}
