package rectangles.entities;

/**
 * Rectangle entity class, contains information about its position and name.
 * An invalid Rectangle can be instantiated (width or height less than 1),
 * but it can be checked using isValidRectangle method
 */
public class Rectangle {

    private String name;
    private int x;
    private int y;
    private int w;
    private int h;

    public Rectangle(int x, int y, int w, int h) {
        this("", x, y, w, h);
    }

    public Rectangle(String name, int x, int y, int w, int h) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
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
     * Two rectangles intersect if there is not a horizontal separation
     * or vertical separation between them.
     *
     * @return true if this rectangle and r intersect (Rectangular, edge or point intersection)
     */
    public boolean intersectWith(Rectangle r) {
        return !(this.horizontalSeparationWith(r) || this.verticalSeparationWith(r));
    }

    /**
     * A horizontal separation between two rectangles exists if they dont have a common range of values
     * in the x axis.
     *
     * @return true if they do not touch in the x axis.
     */
    public boolean horizontalSeparationWith(Rectangle r) {
        return (this.x + this.w < r.getX()) || (r.getX() + r.getW() < this.x);
    }

    /**
     * A vertical separation between two rectangles exists if they dont have a common range of values
     * in the y axis.
     *
     * @return true if they do not touch in the y axis.
     */
    public boolean verticalSeparationWith(Rectangle r) {
        return (this.y + this.h < r.getY()) || (r.getY() + r.getH() < this.y);
    }

    /**
     * A rectangle is valid if their width and height are >= 1 as stated in the specification
     * (Width and height are always positive integers.)
     *
     * @return true if the rectangle is valid
     */
    public boolean isValidRectangle() {
        return this.h >= 1 && this.w >= 1;
    }

    /**
     * Two rectangles are equals if each one of their fields are equals
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
