package com.taxe.game.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testCoordinateAlongLine() throws Exception {
        Coordinate c0 = new Coordinate(5, 5);
        Coordinate c1 = new Coordinate(-2, 3);

        // Same point
        assertEquals(c0, Coordinate.coordinateAlongLine(c0, c0, 0));
        assertEquals(c0, Coordinate.coordinateAlongLine(c0, c0, 1));
        assertEquals(c0, Coordinate.coordinateAlongLine(c0, c0, 0.5f));

        // Different points
        assertEquals(c0, Coordinate.coordinateAlongLine(c0, c1, 0));
        assertEquals(c1, Coordinate.coordinateAlongLine(c0, c1, 1));
        assertEquals(new Coordinate(1.5f, 4f), Coordinate.coordinateAlongLine(c0, c1, 0.5f));

        // Percentage not in range 0..1
        try {
            Coordinate.coordinateAlongLine(c0, c0, -0.1f);
            Coordinate.coordinateAlongLine(c0, c0, 1.1f);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testEquals() throws Exception {
        Coordinate c = new Coordinate(10, 15);

        //Comparing to null and itself
        assertNotEquals(c, null);
        assertEquals(c, c);

        //Comparing to different point with same x and y
        assertEquals(c, new Coordinate(10, 15));

        //Comparing to different point with different x and y
        assertNotEquals(c, new Coordinate(15, 10));
        assertNotEquals(c, new Coordinate(2, 3));
        assertNotEquals(c, new Coordinate(10, 10));
    }
}