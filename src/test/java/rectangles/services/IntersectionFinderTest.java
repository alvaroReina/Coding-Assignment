package rectangles.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rectangles.entities.Intersection;
import rectangles.entities.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionFinderTest {

    private List<Intersection> expectedResult;
    private List<Rectangle> input;
    private IntersectionFinder finder;
    private List<Intersection> result;

    @BeforeEach
    public void setUp() {
        expectedResult = new ArrayList<>();
        input = new ArrayList<>();
        finder = new IntersectionFinder();
    }

    //List size Cases: Empty list, Single element, More than one element
    @Test
    public void testEmptyInput() {
        result = finder.findIntersections();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSingleElementInput() {
        Rectangle r = new Rectangle(1, 1, 1, 1);
        input.add(r);
        finder.setRectangles(input);
        result = finder.findIntersections();
        assertEquals(expectedResult, result);
    }

    //When input size is more than 1:
    //There is no intersections, there is intersections: valid rectangle/not valid
    @Test
    public void testNoIntersections() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 1, 1);
        input.add(r1);
        input.add(r2);
        finder.setRectangles(input);
        result = finder.findIntersections();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidRectangleIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 2, 2);
        Intersection i = Intersection.generateIntersection(r1, r2).get();
        expectedResult.add(i);
        assertTrue(i.isValidRectangle());
        input.add(r1);
        input.add(r2);
        finder.setRectangles(input);
        result = finder.findIntersections();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testInvalidRectangleIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Intersection i = Intersection.generateIntersection(r1, r2).get();
        assertFalse(i.isValidRectangle());
        input.add(r1);
        input.add(r2);
        finder.setRectangles(input);
        result = finder.findIntersections();
        assertEquals(expectedResult, result);
    }

    //Intersection between two or more rectangles
    @Test
    public void testSimpleMultipleRectangleIntersection() {
        Rectangle r1 = new Rectangle("a", 0, 1, 6, 3);
        Rectangle r2 = new Rectangle("b", 2, 1, 2, 2);
        Rectangle r3 = new Rectangle("c", 3, 2, 4, 5);
        Intersection i12 = Intersection.generateIntersection(r1, r2).get();
        Intersection i123 = Intersection.generateIntersection(i12, r3).get();
        Intersection i13 = Intersection.generateIntersection(r1, r3).get();
        Intersection i23 = Intersection.generateIntersection(r2, r3).get();
        expectedResult.add(i12);
        expectedResult.add(i123);
        expectedResult.add(i23);
        expectedResult.add(i13);
        input.add(r1);
        input.add(r2);
        input.add(r3);
        finder.setRectangles(input);
        result = finder.findIntersections();
        assertTrue(result.containsAll(expectedResult));
        assertEquals(expectedResult.size(), result.size());
    }

    //Concentric rectangles
    /*
        The result list must contain at least the intersections 1,2; 1,2,3; 1,2,3,4; 1,2,3,4,5 and 1,2,3,4,5,
        The number of intersections must be 57 according to my understanding of the problem, because in the case of
        concentric rectangles there is a intersection between every combination without repetition of these rectangles.
        C6,2 + C6,3 + C6,4 + C6,5 + C6,6 = 15 + 20 + 15 + 6 + 1 = 57
        (Cn,k == Combination without repetition of n elements choosing k elements)
    */
    @Test
    public void testSixConcentricRectangles() {
        Rectangle r1 = new Rectangle(200, 200, 100, 100);
        Rectangle r2 = new Rectangle(210, 210, 90, 90);
        Rectangle r3 = new Rectangle(220, 220, 80, 80);
        Rectangle r4 = new Rectangle(230, 230, 70, 70);
        Rectangle r5 = new Rectangle(240, 240, 60, 60);
        Rectangle r6 = new Rectangle(250, 250, 50, 50);
        Intersection i12 = Intersection.generateIntersection(r1, r2).get();
        Intersection i123 = Intersection.generateIntersection(i12, r3).get();
        Intersection i1234 = Intersection.generateIntersection(i123, r4).get();
        Intersection i12345 = Intersection.generateIntersection(i1234, r5).get();
        Intersection i123456 = Intersection.generateIntersection(i12345, r6).get();
        input.addAll(Arrays.asList(r1, r2, r3, r4, r5, r6));
        finder.setRectangles(input);
        result = finder.findIntersections();
        expectedResult = Arrays.asList(i12, i123, i1234, i12345, i123456);
        assertTrue(result.containsAll(expectedResult));
        assertEquals(57, result.size());
    }

    //Example given in the specification
    @Test
    public void testExampleIntersectionFinder() {
        Rectangle r1 = new Rectangle("1", 100, 100, 250, 80);
        Rectangle r2 = new Rectangle("2", 120, 200, 250, 150);
        Rectangle r3 = new Rectangle("3", 140, 160, 250, 100);
        Rectangle r4 = new Rectangle("4", 160, 140, 350, 190);
        input.add(r1);
        input.add(r2);
        input.add(r3);
        input.add(r4);
        finder.setRectangles(input);
        Intersection i13 = Intersection.generateIntersection(r1, r3).get();
        Intersection i14 = Intersection.generateIntersection(r1, r4).get();
        Intersection i134 = Intersection.generateIntersection(i13, r4).get();
        Intersection i23 = Intersection.generateIntersection(r2, r3).get();
        Intersection i24 = Intersection.generateIntersection(r2, r4).get();
        Intersection i234 = Intersection.generateIntersection(i23, r4).get();
        Intersection i34 = Intersection.generateIntersection(r3, r4).get();
        expectedResult.addAll(Arrays.asList(i13, i14, i134, i23, i24, i234, i34));
        result = finder.findIntersections();
        assertTrue(result.containsAll(expectedResult));
        assertEquals(expectedResult.size(), result.size());
    }
}
