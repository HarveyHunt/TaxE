package com.taxe.game.nodes;

import com.taxe.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(GdxTestRunner.class)
public class HomebaseTest {

    /**
     * Tests if reading a homebase with health not satisfying the constraints throws an exception
     * @throws Exception
     */
    @Test
    public void testReadNodes() throws Exception {
        // Homebase health < 0
        try {
            List<Node> nodes = Node.readNodes("homebase-health-less-than-zero.json");
            fail();
        }
        catch (RuntimeException e) {
            assertEquals("health < 0", e.getMessage());
        }

        // Homebase health > maxHealth
        try {
            List<Node> nodes = Node.readNodes("homebase-health-greater-than-maxhealth.json");
            fail();
        }
        catch (RuntimeException e) {
            assertEquals("health > maxHealth", e.getMessage());
        }

        // Homebase health satisfies constraints
        try {
            List<Node> nodes = Node.readNodes("homebase-health-ok.json");
            assertEquals(500, ((Homebase)nodes.get(0)).getHealth());
            assertEquals(500, ((Homebase)nodes.get(0)).getMaxHealth());
            assertEquals(400, ((Homebase)nodes.get(1)).getHealth());
            assertEquals(600, ((Homebase)nodes.get(1)).getMaxHealth());
        }
        catch (RuntimeException e) {fail();}
    }

    /**
     * Tests if changing homebase's health does correct clipping to 0 or maximum health
     * @throws Exception
     */
    @Test
    public void testChangeHealthBy() throws Exception {
        List <Node> nodes = Node.readNodes("nodes.json");
        Homebase h = (Homebase)Node.getNodeById("Moscow", nodes);

        // No clipping required
        h.changeHealthBy(-100);
        assertEquals(400, h.getHealth());
        h.changeHealthBy(10);
        assertEquals(410, h.getHealth());

        // Clipping to 0
        h.changeHealthBy(-1000);
        assertEquals(0, h.getHealth());

        // Clipping to maxHealth
        h.changeHealthBy(1000);
        assertEquals(500, h.getHealth());
    }
}