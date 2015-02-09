package com.taxe.game.resources;

import com.taxe.game.nodes.Homebase;
import com.taxe.game.player.Player;
import com.taxe.game.trains.Train;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InfluenceTest {

    private final float delta = 0.000001f;
    private Player p1, p2, p3;
    private List<Player> l1, l2, l3;
    private Influence i1, i2, i3;

    @Before
    public void setUp() {
        p1 = new Player(new Homebase(), new ArrayList<Train>(), 100, 0, 20);
        p2 = new Player(new Homebase(), new ArrayList<Train>(), 100, 0, 20);
        p3 = new Player(new Homebase(), new ArrayList<Train>(), 100, 0, 20);
        l1 = new ArrayList<>(); Collections.addAll(l1, p1);
        l2 = new ArrayList<>(); Collections.addAll(l2, p1, p2);
        l3 = new ArrayList<>(); Collections.addAll(l3, p1, p2, p3);
        i1 = new Influence(l1);
        i2 = new Influence(l2);
        i3 = new Influence(l3);
    }

    /**
     * Tests if influence is distributed equally among players.
     * @throws Exception
     */
    @Test
    public void testInfluenceConstructor() throws Exception {
        // Influence with one player
        assertEquals(1f, i1.getInfluence(l1.get(0)), delta);

        // Influence with two players
        assertEquals(0.5f, i2.getInfluence(l2.get(0)), delta);
        assertEquals(0.5f, i2.getInfluence(l2.get(1)), delta);

        // Influence with three players
        assertEquals(0.333333f, i3.getInfluence(l3.get(0)), delta);
        assertEquals(0.333333f, i3.getInfluence(l3.get(1)), delta);
        assertEquals(0.333333f, i3.getInfluence(l3.get(2)), delta);
    }

    @Test
    public void testChangeInfluenceBy() throws Exception {
        // Influence with single player - no matter what, must always be 1
        i1.changeInfluenceBy(l1.get(0), 0.5f);
        assertEquals(1, i1.getInfluence(l1.get(0)), delta);
        i1.changeInfluenceBy(l1.get(0), 1);
        assertEquals(1, i1.getInfluence(l1.get(0)), delta);
        i1.changeInfluenceBy(l1.get(0), -0.5f);
        assertEquals(1, i1.getInfluence(l1.get(0)), delta);

        // Influence with multiple players
        i3.changeInfluenceBy(l3.get(1), -0.2f);
        assertEquals(0.433333, i3.getInfluence(l3.get(0)), delta);
        assertEquals(0.133333, i3.getInfluence(l3.get(1)), delta);
        assertEquals(0.433333, i3.getInfluence(l3.get(2)), delta);

        i3.changeInfluenceBy(l3.get(2), 0.1f);
        assertEquals(0.356863, i3.getInfluence(l3.get(0)), delta);
        assertEquals(0.109804, i3.getInfluence(l3.get(1)), delta);
        assertEquals(0.533333, i3.getInfluence(l3.get(2)), delta);

        i3.changeInfluenceBy(l3.get(0), 1);
        assertEquals(1, i3.getInfluence(l3.get(0)), delta);
        assertEquals(0, i3.getInfluence(l3.get(1)), delta);
        assertEquals(0, i3.getInfluence(l3.get(2)), delta);

        i3.changeInfluenceBy(l3.get(1), -1);
        assertEquals(1, i3.getInfluence(l3.get(0)), delta);
        assertEquals(0, i3.getInfluence(l3.get(1)), delta);
        assertEquals(0, i3.getInfluence(l3.get(2)), delta);
    }
}