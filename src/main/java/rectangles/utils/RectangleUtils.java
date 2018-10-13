package rectangles.utils;

import rectangles.entities.Rectangle;

public class RectangleUtils {

    public static boolean checkOverlap(Rectangle r1, Rectangle r2) {
        return !(horizontalOverlap(r1, r2) || verticalOverlap(r1, r2) ||
                horizontalOverlap(r2, r1) || verticalOverlap(r2, r1));
    }

    private static boolean horizontalOverlap(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.getX() + rectangle1.getW() < rectangle2.getX();
    }

    private static boolean verticalOverlap(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.getY() + rectangle1.getH() < rectangle2.getY();
    }

    public static boolean checkValidRectangle(Rectangle rectangle) {
        return rectangle.getH() >= 1 && rectangle.getW() >= 1;
    }
}
