package com.taxe.game.resources;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoldTest {

    /**
     * Testing if constructor throws exception when creating objects with negative quantity
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        // Trying to construct gold with negative quantity
        try {
            Gold g = new Gold(-1);
            fail();
        }
        catch (RuntimeException e) {}

        // Trying to construct gold with non-negative quantity
        try {
            Gold g1 = new Gold(0);
            Gold g2 = new Gold(10);
        }
        catch (RuntimeException e) {fail();}
    }

    /**
     * Testing if changing quantity of gold correctly clips it to 0
     * @throws Exception
     */
    @Test
    public void testChangeQuantityBy() throws Exception {
        Gold g = new Gold(10);

        // Result is clipped to 0
        g.changeQuantityBy(-10);
        assertEquals(0, g.getQuantity());

        // Result doesn't need clipping
        g.changeQuantityBy(20);
        assertEquals(20, g.getQuantity());
        g.changeQuantityBy(-5);
        assertEquals(15, g.getQuantity());

    }
}