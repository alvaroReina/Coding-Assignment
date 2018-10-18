package rectangles.utils;

/**
 * This error gets thrown when a RectangleInputReader finds an error that prevents it from parsing the input
 */
public class InputReaderException extends Exception {
    public InputReaderException() {
        super();
    }

    public InputReaderException(String msg) {
        super(msg);
    }
}
