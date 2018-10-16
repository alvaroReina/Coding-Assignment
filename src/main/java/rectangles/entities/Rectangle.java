package rectangles.entities;

/**
 * Rectangle entity class, contains information about its position and also a name
 * The name is not required in the specification but its useful to identify each rectangle if its needed
 */
public class Rectangle {

    private String name;
    private int x;
    private int y;
    private int w;
    private int h;

    public Rectangle(int x, int y, int w, int h) {
        this.name = "";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rectangle(String name, int x, int y, int w, int h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    /**
     * Two rectangles overlap don't overlap if there is not a vertical overlap
     * or horizontal overlap between them
     *
     * @return true if both rectangles overlap
     */
    public static boolean checkOverlap(Rectangle r1, Rectangle r2) {
        return !(r1.horizontalSeparationWith(r2) || r1.verticalSeparationWith(r2) ||
                r2.horizontalSeparationWith(r1) || r2.verticalSeparationWith(r1));
    }

    /**
     * @param r A rectangle to compare with
     * @return true if rectangle1 right edge is to the left of rectangle2 left edge
     */
    public boolean horizontalSeparationWith(Rectangle r) {
        return this.x + this.w < r.getX();
    }

    /**
     * @param r A rectangle to compare with
     * @return true if rectangle1 top edge is below rectangle2 bottom edge
     */
    public boolean verticalSeparationWith(Rectangle r) {
        return this.y + this.h < r.getY();
    }

    /**
     * A rectangle is valid if their width and height are >= 1 as stated in the specification
     * (Width and height are always positive integers.)
     *
     * @return true if the rectangle is valid
     */
    public boolean isValidRectangle() {
        return this.h >= 1 && this.w >=  1;
    }

    /**
     *  Two rectangles are equals if each one of their fields are equals
     *
     * @param obj An Object to compare
     * @return true if they are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Rectangle)) return false;
        Rectangle r = (Rectangle) obj;
        return this.x == r.x && this.y == r.y &&
                this.w == r.w && this.h == r.h && this.name.equals(r.name);
    }

    @Override
    public String toString() {
        return "Rectangle at (" +
                x + "," +
                y + "), w=" +
                w + ", h=" +
                h + ".";
    }
}
