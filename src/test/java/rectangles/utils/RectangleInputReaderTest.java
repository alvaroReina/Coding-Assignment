package rectangles.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rectangles.Constants;
import rectangles.entities.Rectangle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleInputReaderTest {

    private RectangleInputReader inputReader;

    private final String validRects = "\"rects\":[" +
            "{\"x\":100, \"y\":100, \"w\":250, \"h\":80}," +
            "{\"x\":120, \"y\":200, \"w\":250, \"h\":150}," +
            "{\"x\":140, \"y\":160, \"w\":250, \"h\":100}," +
            "{\"x\":160, \"y\":140, \"w\":350, \"h\":190}]";


    @BeforeEach
    public void setUp() {
        inputReader = new RectangleInputReader();
    }

    @Test
    public void testGetRectangleListFromStream() throws InputReaderException {
        Rectangle r1 = new Rectangle("1", 100, 100, 250, 80);
        Rectangle r2 = new Rectangle("2", 120, 200, 250, 150);
        Rectangle r3 = new Rectangle("3", 140, 160, 250, 100);
        Rectangle r4 = new Rectangle("4", 160, 140, 350, 190);
        List<Rectangle> expectedResult = Arrays.asList(r1, r2, r3, r4);
        String input = "{" + validRects + "}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        List<Rectangle> result = inputReader.getRectangleListFromStream(stream);
        assertTrue(result.containsAll(expectedResult));
        assertEquals(expectedResult.size(), result.size());
    }

    @Test
    public void testEmptyJSON() throws InputReaderException {
        String input = "{}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        List<Rectangle> result = inputReader.getRectangleListFromStream(stream);
        assertEquals(0, result.size());
    }

    @Test
    public void testEmptyRectangleList() throws InputReaderException {
        String input = "{\"rects\": []}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        List<Rectangle> result = inputReader.getRectangleListFromStream(stream);
        assertEquals(0, result.size());
    }

    @Test
    public void testMoreRectanglesThanSpecified() throws InputReaderException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"rects\": [");
        for (int i = 0; i < Constants.MAX_RECTANGLES * 5; i++) {
            sb.append("{\"x\":100, \"y\":100, \"w\":250, \"h\":80},");
        }
        sb.append("{\"x\":100, \"y\":100, \"w\":250, \"h\":80}]}");
        InputStream stream = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
        List<Rectangle> result = inputReader.getRectangleListFromStream(stream);
        assertEquals(Constants.MAX_RECTANGLES, result.size());
    }

    @Test
    public void testBadRectangleFormat() {
        String input = "{\"rects\": [{\"x\": \"notAValue\" }]}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        assertThrows(InputReaderException.class, () -> inputReader.getRectangleListFromStream(stream));
    }

    @Test
    public void testBadJSONFormat() {
        InputStream stream = new ByteArrayInputStream(validRects.getBytes(StandardCharsets.UTF_8));
        assertThrows(InputReaderException.class, () -> inputReader.getRectangleListFromStream(stream));
    }

    @Test
    public void testInvalidRectangle() throws InputReaderException {
        //{} = Rectangle(0,0,0,0)
        String input = "{\"rects\": [{}]}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        assertEquals(0, inputReader.getRectangleListFromStream(stream).size());
    }

    @Test
    public void testListElementIsNotAList() {
        String input = "{" +
                "\"rects\": " +
                "{\"x\":120, \"y\":200, \"w\":250, \"h\":150}" +
                "}";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        assertThrows(InputReaderException.class, () -> inputReader.getRectangleListFromStream(stream));
    }

    @Test
    public void testListElementAfterOtherElements() throws InputReaderException {
        String input = "{ " +
                "\"name\": \"nameValue\"," +
                "\"aList\": []," +
                validRects + "}";
        Rectangle r1 = new Rectangle("1", 100, 100, 250, 80);
        Rectangle r2 = new Rectangle("2", 120, 200, 250, 150);
        Rectangle r3 = new Rectangle("3", 140, 160, 250, 100);
        Rectangle r4 = new Rectangle("4", 160, 140, 350, 190);
        List<Rectangle> expectedResult = Arrays.asList(r1, r2, r3, r4);
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        List<Rectangle> result = inputReader.getRectangleListFromStream(stream);
        assertTrue(result.containsAll(expectedResult));
        assertEquals(expectedResult.size(), result.size());
    }
}
