package rectangles.services;

import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO Documentation
 */
public class IntersectionFinder {

    private List<Rectangle> rectangles;

    public IntersectionFinder(List<Rectangle> rectangles) {
        this.rectangles = rectangles != null ? rectangles : new ArrayList<>();
    }

    public List<Intersection> findIntersections() {
        List<Intersection> intersections = new ArrayList<>();
        rectangles.forEach(rectangle -> findIntersections(rectangle, intersections, rectangles.indexOf(rectangle) + 1));
        return intersections;
    }

    private void findIntersections(Rectangle r1, List<Intersection> intersections, int index) {
        for (int i = index; i < rectangles.size(); i++) {
            Rectangle r2 = rectangles.get(i);
            Optional<Intersection> result = Intersection.IntersectionFactory(r1, r2);
            result.ifPresent(x -> {
                intersections.add(x);
                findIntersections(x, intersections, rectangles.indexOf(r2) + 1);
            });
        }
    }
}
