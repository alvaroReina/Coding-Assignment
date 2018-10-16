package rectangles.entities;

import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * An Intersection is a Rectangle because it have a Rectangle shape and behaves like a Rectangle
 * Has information about the two Rectangles that it intersects, this will be useful at some time in the future
 */
public class Intersection extends Rectangle {

    //Intersection type also fit here, therefore Intersection from more than two Rectangle are possible
    private Rectangle rectangle1;
    private Rectangle rectangle2;

    /**
     * Returns an Optional<Intersection> if is possible to generate an intersection from both rectangles
     * otherwise returns an Optional.empty
     *
     * @param r1 A rectangle
     * @param r2 Another rectangle
     * @return An optional intersection
     */
    public static Optional<Intersection> IntersectionFactory(Rectangle r1, Rectangle r2) {
        if (r1 != null && r2 != null && Rectangle.checkOverlap(r1, r2)) {
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
     * it only can be created after cheeking that a Intersection is possible between two Rectangle
     */
    private Intersection(Rectangle r1, Rectangle r2, int x, int y, int w, int h) {
        super(x, y, w, h);
        this.rectangle1 = r1;
        this.rectangle2 = r2;
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
     * Those next 4 methods are private because they assume that the rectangles overlap and are only used
     * to calculate the intersection rectangle values in a controlled environment
     *
     * They give the intersection rectangle values from two rectangles
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
        Rectangle r = (Rectangle) obj;
        Rectangle that = this;
        if (that.equals(r)) {
            Intersection x = (Intersection) obj;
            return ((rectangle1.equals(x.rectangle1) && rectangle2.equals(x.rectangle2)) ||
                    (rectangle2.equals(x.rectangle1) && rectangle1.equals(x.rectangle2)));
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
