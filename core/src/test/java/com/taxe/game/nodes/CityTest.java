package com.taxe.game.nodes;

import com.taxe.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(GdxTestRunner.class)
public class CityTest {

    private City city;

    @Before
    public void setUp() {
        city = new City();
    }

    @Test
    public void testNotLocked() {
        assertFalse(city.locked);
    }

    @Test
    public void testChangeInfluence() {
        city.changeInfluenceBy(0, 0.1f);
        assertEquals(0.6f, city.getInfluence(0), 0.0001f);
        assertEquals(0.4f, city.getInfluence(1), 0.0001f);
    }

    @Test
    public void testChangeInfluenceMinimum() {
        city.changeInfluenceBy(0, -1f);
        assertEquals(0.0f, city.getInfluence(0), 0.0001f);
        assertEquals(1.0f, city.getInfluence(1), 0.0001f);
    }

    @Test
    public void testChangeInfluenceMaximum() {
        city.changeInfluenceBy(0, 1f);
        assertEquals(1.0f, city.getInfluence(0), 0.0001f);
        assertEquals(0.0f, city.getInfluence(1), 0.0001f);
    }
}
