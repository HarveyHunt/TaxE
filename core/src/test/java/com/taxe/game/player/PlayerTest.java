package com.taxe.game.player;

import com.taxe.GdxTestRunner;
import com.taxe.game.nodes.IntermediatePoint;
import com.taxe.game.nodes.Node;
import com.taxe.game.trains.BasicTrain;
import com.taxe.game.trains.Train;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player(null, new ArrayList<Train>(), 0, 0, 10, 0);
    }

    @Test
    public void testAddTrain() {
        Train t = new BasicTrain(new IntermediatePoint());
        assertEquals(player.getTrains().size(), 0);
        assertEquals(player.getFuelUsage(), 0);

        player.addTrain(t);
        assertEquals(player.getTrains().size(), 1);
        assertEquals(player.getFuelUsage(), t.getFuelCost());
    }

    @Test
    public void testChangeFuelCapPositive() {
        player.changeFuelCap(2);
        assertEquals(player.getFuelCap(), 12);
    }

    @Test
    public void testChangeFuelCapNegative() {
        player.changeFuelCap(-200);
        assertEquals(player.getFuelCap(), 0);
    }

    @Test
    public void testChangeFuelUsageNegative() {
        player.changeFuelUsage(-200);
        assertEquals(player.getFuelUsage(), 0);
    }

    @Test
    public void testChangeFuelUsagePositive() {
        player.changeFuelUsage(2);
        assertEquals(player.getFuelUsage(), 2);
    }

    @Test
    public void testChangeGoldPositive() {
        player.changeGold(2);
        assertEquals(player.getGold(), 2);
    }

    @Test
    public void testChangeGoldNegative() {
        player.changeGold(-200);
        assertEquals(player.getGold(), 0);
    }

    @Test
    public void fuelCapExceeded() {
        player.changeFuelUsage(100);
        assertTrue(player.fuelCapExceeded());
    }
}
