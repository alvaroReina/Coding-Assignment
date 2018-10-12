package rectangles.services;

import rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IntersectionFinder {

    private List<Rectangle> rectangles;
    private HashMap<Rectangle, String> rectangleNames;

    public IntersectionFinder(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        initializeNamesMap(rectangles);
    }

    private void initializeNamesMap(List<Rectangle> rectangles) {
        rectangleNames = new HashMap<>();
        for (int i = 0; i < rectangles.size(); i++) {
            rectangleNames.put(rectangles.get(i), String.valueOf(i + 1));
        }
    }

    public List<Rectangle> findIntersections() {
        List<Rectangle> intersections = new ArrayList<>();
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle r1 = rectangles.get(i);
            for (int j = i + 1; j < rectangles.size(); j++) {
                Rectangle r2 = rectangles.get(j);
                Optional<Rectangle> result = getIntersection(r1, r2);
                result.ifPresent(x -> {
                    intersections.add(x);
                    rectangleNames.put(x, rectangleNames.get(r1) + ", " + rectangleNames.get(r2));
                    printIntersection(r1, r2, x);
                });
            }
        }
        return intersections;
    }

    private Optional<Rectangle> getIntersection(Rectangle r1, Rectangle r2) {
        if (checkOverlap(r1, r2)) {
            Rectangle intersection = new Rectangle(
                    max(r1.getX(), r2.getX()),
                    max(r1.getY(), r2.getY()),
                    min(r1.getX() + r1.getW(), r2.getX() + r2.getW()) - max(r1.getX(), r2.getX()),
                    min(r1.getY() + r1.getH(), r2.getY() + r2.getH()) - max(r1.getY(), r2.getY())
            );
            return Optional.of(intersection);
        } else {
            return Optional.empty();
        }
    }

    private void printIntersection(Rectangle r1, Rectangle r2, Rectangle intersection) {
        System.out.println("\tBetween rectangle " + rectangleNames.get(r1) + " and " + rectangleNames.get(r2) +
                " at (" + intersection.getX() + "," + intersection.getY() + "), w=" + intersection.getW() + ", h=" + intersection.getH() + ".");
    }

    private boolean checkOverlap(Rectangle r1, Rectangle r2) {
        return !(r1.getX() + r1.getW() < r2.getX() || r1.getX() > r2.getX() + r2.getW() ||
                r1.getY() + r1.getH() < r2.getY() || r1.getY() > r2.getY() + r2.getH());
    }
}
