package rectangles;

import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;
import rectangles.services.IntersectionFinder;
import rectangles.services.IntersectionOutput;
import rectangles.services.StandardOutput;
import rectangles.utils.InputReaderException;
import rectangles.utils.RectangleInputReader;

import java.util.List;

/**
 * The entry point for this application, checks the program arguments
 * and proceeds to use the public API to solve the assignment.
 */
public class Application {

    public static void main(String[] args) throws InputReaderException {
        String fileName;

        if (args.length == 1) {
            fileName = args[0];
        } else {
            throw new IllegalArgumentException("Usage: (filename)");
        }

        RectangleInputReader rectangleInputReader = new RectangleInputReader();
        IntersectionOutput out = new StandardOutput();

        List<Rectangle> rectangles = rectangleInputReader.getRectangleListFromFile(fileName);
        IntersectionFinder intersectionFinder = new IntersectionFinder(rectangles);
        out.showInput(rectangles);
        List<Intersection> intersections = intersectionFinder.findIntersections();
        out.showIntersections(intersections);
    }
}
