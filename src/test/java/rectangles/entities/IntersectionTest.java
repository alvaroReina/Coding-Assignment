package rectangles.entities;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    //Intersection cases: Rectangular intersection, edge x|y intersection, point intersection, dont intersect.
    @Test
    public void testRectangularIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertEquals(1, i.getX());
        assertEquals(1, i.getY());
        assertEquals(1, i.getW());
        assertEquals(1, i.getH());
    }

    @Test
    public void testEdgeYIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 0, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertFalse(i.isValidRectangle());
    }

    @Test
    public void testEdgeXIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(0, 1, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertFalse(i.isValidRectangle());
    }

    @Test
    public void testNotIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertFalse(optionalIntersection.isPresent());
    }


    @Test
    public void testPointIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertFalse(i.isValidRectangle());
    }

    @Test
    public void testIntersectionFromNegativeRectangles() {
        Rectangle r1 = new Rectangle(-6, -5, 4, 3);
        Rectangle r2 = new Rectangle(-3, -3, 2, 2);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertEquals(-3, i.getX());
        assertEquals(-3, i.getY());
        assertEquals(1, i.getW());
        assertEquals(1, i.getH());
    }

    @Test
    public void testIntersectionFromMixedRectangles() {
        Rectangle r1 = new Rectangle(-3, -3, 6, 6);
        Rectangle r2 = new Rectangle(-1, 1, 3, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        assertTrue(optionalIntersection.isPresent());
        Intersection i = optionalIntersection.get();
        assertEquals(-1, i.getX());
        assertEquals(1, i.getY());
        assertEquals(3, i.getW());
        assertEquals(1, i.getH());
    }

    @Test
    public void testCommutativeIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 2, 2);
        Rectangle r3 = new Rectangle(2, 2, 2, 2);
        Intersection i12 = Intersection.generateIntersection(r1, r2).get();
        Intersection i23 = Intersection.generateIntersection(r2, r3).get();
        Intersection i123 = Intersection.generateIntersection(i12, r3).get();
        Intersection i231 = Intersection.generateIntersection(i23, r1).get();
        assertEquals(i123.getX(), i231.getX());
        assertEquals(i123.getY(), i231.getY());
        assertEquals(i123.getW(), i231.getW());
        assertEquals(i123.getH(), i231.getH());
    }

    @Test
    public void testEquals() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Intersection i1 = Intersection.generateIntersection(r1, r2).get();
        Intersection i2 = Intersection.generateIntersection(r2, r1).get();
        assertEquals(i1, i2);
    }

    @Test
    public void testNotEquals() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 2, 2);
        Rectangle r3 = new Rectangle(0, 0, 3, 3);
        Intersection i1 = Intersection.generateIntersection(r1, r2).get();
        Intersection i2 = Intersection.generateIntersection(r3, r2).get();
        assertNotEquals(i1, i2);
    }

    @Test
    public void testSameIntersectionSpaceFromRectanglesNotEquals() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Rectangle r3 = new Rectangle(1, 1, 2, 2);
        Intersection i1 = Intersection.generateIntersection(r1, r2).get();
        Intersection i2 = Intersection.generateIntersection(r2, r3).get();
        assertEquals(i1.getX(), i2.getX());
        assertEquals(i1.getY(), i2.getY());
        assertEquals(i1.getW(), i2.getW());
        assertEquals(i1.getH(), i2.getH());
        assertNotEquals(i1, i2);
    }

    @Test
    public void testToString() {
        Rectangle r1 = new Rectangle("a", 0, 0, 2, 2);
        Rectangle r2 = new Rectangle("b", 1, 1, 1, 1);
        Optional<Intersection> optionalIntersection = Intersection.generateIntersection(r1, r2);
        Intersection i = optionalIntersection.get();
        final String expectedResult = "Between rectangle a and b at (1,1), w=1, h=1.";
        assertEquals(expectedResult, i.toString());
    }
}
