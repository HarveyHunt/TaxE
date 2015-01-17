package com.taxe.game.cargo;

import org.junit.Test;
import static org.junit.Assert.*;

public class PenguinsTest {

    /**
     * Testing if constructor throws exception when creating objects with negative quantity.
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        // Creating objects with negative quantity
        try {
            Penguins p = new Penguins(-1);
            fail();
        }
        catch (RuntimeException e) {}

        // Creating objects with non-negative quantity
        try {
            Penguins p1 = new Penguins(0);
            Penguins p2 = new Penguins(20);
        }
        catch (RuntimeException e) {fail();}
    }

    /**
     * Testing if setter throws exception when setting cargo quantity to negative
     * @throws Exception
     */
    @Test
    public void testSetQuantity() throws Exception {
        Penguins p = new Penguins(10);

        // Setting quantity to negative
        try {
            p.setQuantity(-5);
            fail();
        }
        catch (RuntimeException e) {}

        // Setting quantity to non-negative
        try {
            p.setQuantity(100);
            p.setQuantity(0);
        }
        catch (RuntimeException e) {fail();}
    }
}