package rectangles.services;

import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class purpose is to look for intersections with a valid area from a rectangles list
 */
public class IntersectionFinder {

    private List<Rectangle> rectangles;

    public IntersectionFinder() {
        this.rectangles = new ArrayList<>();
    }

    public IntersectionFinder(List<Rectangle> input) {
        this.rectangles = input != null ? input : new ArrayList<>();
    }

    /**
     * Finds every intersection between the rectangles in the rectangles list (this.rectangles)
     *
     * @return the intersections found
     */
    public List<Intersection> findIntersections() {
        List<Intersection> intersections = new ArrayList<>();
        //Calls the findIntersections method with each rectangle in an ascendant order
        for (int i = 0; i < rectangles.size(); i++) {
            findIntersections(rectangles.get(i), intersections, i + 1);
        }
        return intersections;
    }

    /**
     * This method iterates the rectangles list from index to the end, looking for intersections with r1.
     * By following a recursive approach, it explores the whole tree of intersections between rectangles
     * and avoids repeated intersections by traversing the tree in a depth-first search
     *
     * @param r1            A rectangle to compare with every other
     * @param intersections a list where the intersections found are added
     * @param index         the start point in the rectangles list
     */
    private void findIntersections(Rectangle r1, List<Intersection> intersections, int index) {
        for (int i = index; i < rectangles.size(); i++) {
            Rectangle r2 = rectangles.get(i);
            Optional<Intersection> result = Intersection.generateIntersection(r1, r2);
            if (result.isPresent()) {
                Intersection intersection = result.get();
                if (intersection.isValidRectangle()) {
                    //If an intersection between r1 and r2 exists and is valid, it is added to the intersections list
                    intersections.add(intersection);
                    //Calls this method again with the intersection as r1, to find intersections between two or more rectangles
                    //and the index from r2, to be always ascendant.
                    findIntersections(intersection, intersections, i + 1);
                }
            }
        }
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }
}
