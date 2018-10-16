package rectangles.services;

import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;

import java.util.List;

/**
 * Provides an interface to show the program data
 */
public interface IntersectionOutput {
    void showInput(List<Rectangle> rectangles);
    void showIntersections(List<Intersection> intersections);
}
