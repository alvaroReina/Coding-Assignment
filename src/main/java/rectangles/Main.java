package rectangles;

import rectangles.entities.Rectangle;
import rectangles.services.IntersectionFinder;
import rectangles.services.IntersectionOutput;
import rectangles.services.StandardOutput;
import rectangles.utils.RectangleInputReader;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName;

        if (args.length == 1) {
            fileName = args[0];
        } else {
            throw new IllegalArgumentException("Usage: (filename)");
        }

        RectangleInputReader reader = new RectangleInputReader();
        List<Rectangle> rectangles = reader.getRectangleListFromFile(fileName);
        System.out.println("Input: ");
        rectangles.forEach(x -> System.out.println("\t" + (rectangles.indexOf(x)+1)  + ": " + x));
        System.out.println("\nIntersections:");
        IntersectionOutput out = new StandardOutput();
        IntersectionFinder intersectionFinder = new IntersectionFinder(rectangles, out);
        intersectionFinder.findIntersections();
    }
}
