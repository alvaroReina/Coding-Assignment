package rectangles.services;

import rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IntersectionFinder {

    private List<Rectangle> rectangles;
    private IntersectionOutput output;

    public IntersectionFinder(List<Rectangle> rectangles, IntersectionOutput out) {
        this.rectangles = rectangles;
        this.output = out;
        output.initialize(rectangles);
    }

    public List<Rectangle> findIntersections() {
        List<Rectangle> intersections = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            findIntersections(rectangle, intersections, rectangles.indexOf(rectangle)+1);
        }
        return intersections;
    }

    private List<Rectangle> findIntersections(Rectangle r1, List<Rectangle> intersections, int index) {
        for (int i = index; i < rectangles.size(); i++) {
            Rectangle r2 = rectangles.get(i);
            Optional<Rectangle> result = getIntersection(r1, r2);
            result.ifPresent(x -> {
                intersections.add(x);
                output.printIntersection(r1, r2, x);
                findIntersections(x, intersections, rectangles.indexOf(r2)+1);
            });
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

    private boolean checkOverlap(Rectangle r1, Rectangle r2) {
        return !(horizontalOverlap(r1, r2) || verticalOverlap(r1, r2) ||
                horizontalOverlap(r2, r1) || verticalOverlap(r2, r1));
    }

    private boolean horizontalOverlap(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.getX() + rectangle1.getW() < rectangle2.getX();
    }

    private boolean verticalOverlap(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.getY() + rectangle1.getH() < rectangle2.getY();
    }
}
