package rectangles;

import rectangles.entities.Intersection;
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
        String fileName;

        if (args.length == 1) {
            fileName = args[0];
        } else {
            throw new IllegalArgumentException("Usage: (filename)");
        }

        RectangleInputReader rectangleInputReader = new RectangleInputReader();

        List<Rectangle> rectangles = rectangleInputReader.getRectangleListFromFile(fileName);

        IntersectionOutput out = new StandardOutput();
        IntersectionFinder intersectionFinder = new IntersectionFinder(rectangles);

        out.showInput(rectangles);
        List<Intersection> intersections = intersectionFinder.findIntersections();
        out.showIntersections(intersections);

    }
}
