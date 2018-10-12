package rectangles.services;

import rectangles.entities.Rectangle;

import java.util.List;

public interface IntersectionOutput {
    void initialize(List<Rectangle> rectangles);
    void printIntersection(Rectangle rectangle1, Rectangle rectangle2, Rectangle intersection);
}
