package com.taxe.game.resources;

import org.junit.Test;

import static org.junit.Assert.*;

public class FuelTest {

    /**
     * Testing if constructor throws exception when creating objects with negative usedFuel and fuelCap
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        // Constructing fuel with negative parameters
        try {
            Fuel f = new Fuel(-1, -1);
            fail();
        }
        catch (RuntimeException e) {}
        try {
            Fuel f = new Fuel(-2, 0);
            fail();
        }
        catch (RuntimeException e) {}
        try {
            Fuel f = new Fuel(0, -5);
            fail();
        }
        catch (RuntimeException e) {}

        // Constructing fuel with non-negative parameters
        try {
            Fuel f1 = new Fuel(10, 20);
            Fuel f2 = new Fuel(20, 10);
        }
        catch (RuntimeException e) {fail();}
    }

    /**
     * Testing if changing fuel cap correctly clips it to 0.
     * @throws Exception
     */
    @Test
    public void testChangeFuelCapBy() throws Exception {
        Fuel f = new Fuel(10, 20);

        // Result doesn't need clipping
        f.changeFuelCapBy(5);
        assertEquals(15, f.getFuelCap());
        f.changeFuelCapBy(-6);
        assertEquals(9, f.getFuelCap());

        //Result needs clipping
        f.changeFuelCapBy(-100);
        assertEquals(0, f.getFuelCap());
    }

    /**
     * Testing if changing used fuel correctly clips it to 0.
     * @throws Exception
     */
    @Test
    public void testChangeUsedFuelBy() throws Exception {
        Fuel f = new Fuel(10, 20);

        // Result doesn't need clipping
        f.changeUsedFuelBy(5);
        assertEquals(25, f.getUsedFuel());
        f.changeUsedFuelBy(-6);
        assertEquals(19, f.getUsedFuel());

        //Result needs clipping
        f.changeUsedFuelBy(-100);
        assertEquals(0, f.getUsedFuel());
    }

    @Test
    public void testFuelCapExceeded() throws Exception {
        Fuel f1 = new Fuel(10, 20);
        Fuel f2 = new Fuel(20, 10);
        Fuel f3 = new Fuel(10, 10);
        assertTrue(f1.fuelCapExceeded());
        assertFalse(f2.fuelCapExceeded());
        assertFalse(f3.fuelCapExceeded());
    }
}