package rectangles.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    public void testGetters() {
        Rectangle r1 = new Rectangle("name", 0, 1, 2, 3);
        assertEquals("name", r1.getName());
        assertEquals(0, r1.getX());
        assertEquals(1, r1.getY());
        assertEquals(2, r1.getW());
        assertEquals(3, r1.getH());
    }

    @Test
    public void testSetters() {
        Rectangle r1 = new Rectangle("name", 0, 1, 2, 3);
        r1.setName("name2");
        r1.setX(1);
        r1.setY(2);
        r1.setW(3);
        r1.setH(4);
        assertEquals("name2", r1.getName());
        assertEquals(1, r1.getX());
        assertEquals(2, r1.getY());
        assertEquals(3, r1.getW());
        assertEquals(4, r1.getH());
    }

    //Intersection cases: Rectangular, edge x, edge y, point, dont intersect
    @Test
    public void testRectangularIntersection() {
        Rectangle r1 = new Rectangle(0, 1, 2, 2);
        Rectangle r2 = new Rectangle(1, 2, 2, 2);
        assertTrue(r1.intersectWith(r2));
    }

    @Test
    public void testEdgeYIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 0, 1, 1);
        assertTrue(r1.intersectWith(r2));
    }

    @Test
    public void testEdgeXIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(0, 1, 1, 1);
        assertTrue(r1.intersectWith(r2));
    }

    @Test
    public void testPointIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        assertTrue(r1.intersectWith(r2));
    }

    @Test
    public void testNotIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 1, 1);
        assertFalse(r1.intersectWith(r2));
    }

    //Separation cases: No separation, Separated, Touching edges
    @Test
    public void testHorizontalSeparation() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(2, 0, 1, 1);
        assertTrue(r1.horizontalSeparationWith(r2));
    }

    @Test
    public void testNotHorizontalSeparation() {
        Rectangle r1 = new Rectangle(0, 0, 3, 1);
        Rectangle r2 = new Rectangle(2, 0, 1, 1);
        assertFalse(r1.horizontalSeparationWith(r2));
    }

    @Test
    public void testHorizontalSeparationTouchingEdges() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 0, 1, 1);
        assertFalse(r1.horizontalSeparationWith(r2));
    }

    @Test
    public void testVerticalSeparation() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(0, 2, 1, 1);
        assertTrue(r1.verticalSeparationWith(r2));
    }

    @Test
    public void testNotVerticalSeparation() {
        Rectangle r1 = new Rectangle(0, 0, 1, 3);
        Rectangle r2 = new Rectangle(0, 2, 1, 1);
        assertFalse(r1.verticalSeparationWith(r2));
    }

    @Test
    public void testVerticalSeparationTouchingEdges() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(0, 1, 1, 1);
        assertFalse(r1.horizontalSeparationWith(r2));
    }

    //Valid cases: Valid, Not valid because w | h | w AND h
    @Test
    public void testIsValidRectangle() {
        Rectangle r = new Rectangle(1, 1, 1, 1);
        assertTrue(r.isValidRectangle());
    }

    @Test
    public void notValidRectangleW() {
        Rectangle r = new Rectangle(1, 1, 0, 1);
        assertFalse(r.isValidRectangle());
    }

    @Test
    public void notValidRectangleH() {
        Rectangle r = new Rectangle(1, 1, 1, 0);
        assertFalse(r.isValidRectangle());
    }

    @Test
    public void notValidRectangleBoth() {
        Rectangle r = new Rectangle(1, 1, 0, 0);
        assertFalse(r.isValidRectangle());
    }

    @Test
    public void testEquals() {
        Rectangle r1 = new Rectangle(1, 1, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        assertEquals(r1, r2);
    }

    //Omitting cases when only 1 field differs or combination of fields
    @Test
    public void testNotEquals() {
        Rectangle r1 = new Rectangle(1, 1, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 2, 2);
        assertNotEquals(r1, r2);
    }

    @Test
    public void testNotEqualsName() {
        Rectangle r1 = new Rectangle("n1", 1, 1, 1, 1);
        Rectangle r2 = new Rectangle("n2", 1, 1, 1, 1);
        assertNotEquals(r1, r2);
    }

    @Test
    public void testNotEqualsName2() {
        Rectangle r1 = new Rectangle("name", 1, 1, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        assertNotEquals(r1, r2);
    }

    @Test
    public void testToString() {
        Rectangle r = new Rectangle(1, 1, 1, 1);
        final String expectedResult = "Rectangle at (1,1), w=1, h=1.";
        assertEquals(expectedResult, r.toString());
    }
}
