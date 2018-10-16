package rectangles.services;

import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;

import java.util.List;

/**
 *  Uses the standard system output to print rectangles and intersections
 */
public class StandardOutput implements IntersectionOutput {

    public StandardOutput() {
    }

    @Override
    public void showInput(List<Rectangle> rectangles) {
        System.out.println("Input:");
        int count = 1;
        for (Rectangle r : rectangles) {
            System.out.println("\t" + count + ": " + r);
            count++;
        }

    }

    @Override
    public void showIntersections(List<Intersection> intersections) {
        System.out.println("Intersections:");
        int count = 1;
        for (Intersection i : intersections) {
            System.out.println("\t" + count + ": " + i);
            count++;
        }
    }
}
