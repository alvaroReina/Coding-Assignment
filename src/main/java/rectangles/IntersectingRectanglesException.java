package rectangles;

/**
 * To not add unnecessary complexity, this is the only custom Exception it this program
 * It gets thrown when there is any problem running the program and needs to end
 */
public class IntersectingRectanglesException extends Exception {
    public IntersectingRectanglesException() {
        super();
    }

    public IntersectingRectanglesException(String msg) {
        super(msg);
    }
}
