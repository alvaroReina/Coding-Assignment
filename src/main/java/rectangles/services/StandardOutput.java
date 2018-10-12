package rectangles.services;

import rectangles.entities.Rectangle;

import java.util.HashMap;
import java.util.List;

public class StandardOutput implements IntersectionOutput {

    private HashMap<Rectangle, String> rectangleNames;
    private int counter;

    public StandardOutput() {
        rectangleNames = new HashMap<>();
        counter = 1;
    }

    @Override
    public void initialize(List<Rectangle> rectangles) {
        rectangles.forEach(x -> rectangleNames.put(x, String.valueOf(rectangles.indexOf(x)+1)));

    }

    @Override
    public void printIntersection(Rectangle rectangle1, Rectangle rectangle2, Rectangle intersection) {
        registerName(intersection, generateIntersectionName(rectangle1, rectangle2));
        System.out.println("\t" + counter + ": Between rectangle " + rectangleNames.get(rectangle1) + " and " + rectangleNames.get(rectangle2) +
                " at (" + intersection.getX() + "," + intersection.getY() + "), w=" + intersection.getW() + ", h=" + intersection.getH() + ".");
        counter++;
    }

    private String generateIntersectionName(Rectangle r1, Rectangle r2) {
        return rectangleNames.get(r1) + ", " + rectangleNames.get(r2);
    }

    private void registerName(Rectangle r, String name) {
        rectangleNames.put(r, name);
    }
}
