package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrainTest {

    /**
     * Tests if creating a train not satisfying the constraints throws an exception.
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        // Trying to create a train not satisfying the constraints
        try {
            SimpleTrain s = new SimpleTrain(-1, 0, 1, new City());
            fail();
        }
        catch (RuntimeException e) {}
        try {
            SimpleTrain s = new SimpleTrain(1, -1, 0, new City());
            fail();
        }
        catch (RuntimeException e) {}
        try {
            SimpleTrain s = new SimpleTrain(0, 1, -1, new City());
            fail();
        }
        catch (RuntimeException e) {}
        try {
            SimpleTrain s = new SimpleTrain(-1, 1, 1, null);
            fail();
        }
        catch (RuntimeException e) {}

        // Trying to create a train satisfying constraints
        try {
            SimpleTrain s = new SimpleTrain(1, 2, 3, new City());
            assertEquals(1, s.getSpeed());
            assertEquals(2, s.getCargoCap());
            assertEquals(3, s.getFuelCost());
        }
        catch (RuntimeException e) {fail();}
    }

    /**
     * Class representing a basic train. A very simple train.
     */
    private static class SimpleTrain extends Train {

        public SimpleTrain(int speed, int cargoCap, int fuelCost, Node node) {
            super(speed, cargoCap, fuelCost, "Simple train", node);
        }

        public Texture getTexture() {
            return null;
        }

        public void adjustActor() {
        }

    }
}