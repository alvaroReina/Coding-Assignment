package rectangles.entities;

import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * This Intersection class represents an Intersection between two Rectangle objects, to instantiate this class
 * we can not rely on the caller to make sure that two Rectangle objects actually intersect.
 * That's why generateIntersection method exists and the constructor is private.
 * <p>
 * A Intersection can be of the following types: Point Intersection, Edge Intersection, Rectangular Intersection
 * <p>
 * Clarification: my initial thought was to create an Intersection interface and the following implementations:
 * RectangularIntersection, EdgeIntersection, PointIntersection. And an Intersection factory.
 * But this would add unnecessary complexity to the assignment of looking for rectangular intersections.
 * So I have grouped all types of intersections in this class and we can use isValidRectangle method to check it.
 */
public class Intersection extends Rectangle {

    //An intersection has information about the two Rectangle objects that it intersects
    //Intersection type also fits here, therefore Intersection from more than two Rectangle objects are possible
    private Rectangle rectangle1;
    private Rectangle rectangle2;

    /**
     * Intersection Factory, entry point for Intersection instantiation.
     * Returns an Optional<Intersection> if is possible to generate an intersection from both rectangles
     * otherwise returns an Optional.empty
     *
     * @param r1 A rectangle
     * @param r2 Another rectangle
     * @return An optional intersection
     */
    public static Optional<Intersection> generateIntersection(Rectangle r1, Rectangle r2) {
        if (r1 != null && r2 != null && r1.intersectWith(r2)) {
            Intersection i = new Intersection(
                    r1,
                    r2,
                    intersectionX(r1, r2),
                    intersectionY(r1, r2),
                    intersectionW(r1, r2),
                    intersectionH(r1, r2));
            return Optional.of(i);
        } else {
            return Optional.empty();
        }
    }

    /**
     * It is NOT possible to force a new Intersection,
     * it only can be created after cheeking that an Intersection is possible between two Rectangle
     */
    private Intersection(Rectangle r1, Rectangle r2, int x, int y, int w, int h) {
        super(x, y, w, h);
        this.rectangle1 = r1;
        this.rectangle2 = r2;
        //The default name value is a combination of the rectangles names
        this.setName(rectangle1.getName() + ", " + rectangle2.getName());
    }

    public Rectangle getRectangle1() {
        return rectangle1;
    }

    public void setRectangle1(Rectangle rectangle1) {
        this.rectangle1 = rectangle1;
    }

    public Rectangle getRectangle2() {
        return rectangle2;
    }

    public void setRectangle2(Rectangle rectangle2) {
        this.rectangle2 = rectangle2;
    }

    /**
     * These next 4 methods are private because they assume that the rectangles overlap and are only used
     * to calculate the intersection rectangle values in a controlled environment.
     * They give the intersection rectangle values between two rectangles.
     * As a Rectangle area goes from x to x+w and y to y+h,
     * the intersection area between two rectangles is the minimum common range of values on the x and y axes.
     * And that would be maximum(x) to minimum(x+w) and maximum(y) to minimum(y+h).
     */
    private static int intersectionX(Rectangle r1, Rectangle r2) {
        return max(r1.getX(), r2.getX());
    }

    private static int intersectionY(Rectangle r1, Rectangle r2) {
        return max(r1.getY(), r2.getY());
    }

    private static int intersectionW(Rectangle r1, Rectangle r2) {
        return min(r1.getX() + r1.getW(), r2.getX() + r2.getW()) - max(r1.getX(), r2.getX());
    }

    private static int intersectionH(Rectangle r1, Rectangle r2) {
        return min(r1.getY() + r1.getH(), r2.getY() + r2.getH()) - max(r1.getY(), r2.getY());
    }

    /**
     * Two intersections are equals if they are equals as a rectangle
     * and they intersect the same two rectangles
     *
     * @param obj An Object to compare
     * @return true if they are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Intersection)) return false;
        Intersection r = (Intersection) obj;
        if (super.equals(r)) {
            return ((rectangle1.equals(r.rectangle1) && rectangle2.equals(r.rectangle2)) ||
                    (rectangle2.equals(r.rectangle1) && rectangle1.equals(r.rectangle2)));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Between rectangle " + rectangle1.getName() + " and " + rectangle2.getName() +
                " at (" + this.getX() + "," + this.getY() + "), w=" + this.getW() + ", h=" + this.getH() + ".";
    }
}
