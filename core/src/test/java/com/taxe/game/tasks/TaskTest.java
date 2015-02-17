package com.taxe.game.tasks;

import com.taxe.GdxTestRunner;
import com.taxe.game.cargo.Penguins;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.trains.Train;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

@RunWith(GdxTestRunner.class)
public class TaskTest {

    @Test
    public void testNotComplete() {
        // ALERT: Animal testing.
        Task t = new Task(new City(), new Penguins(100), 4);
        assertFalse(t.isComplete(new Player(null, new ArrayList<Train>(), 100, 1, 1, 0)));
    }

    @Test
    public void testNegativeTurns() {
        try {
            Task t = new Task(new City(), new Penguins(100), -1);
        } catch (RuntimeException e) {

        }
    }

    @Test
    public void testNullCity() {
        try {
            Task t = new Task(null, new Penguins(100), 1);
        } catch (RuntimeException e) {

        }
    }

    @Test
    public void testNullCargo() {
        try {
            Task t = new Task(new City(), null, 1);
        } catch (RuntimeException e) {

        }
    }
}
