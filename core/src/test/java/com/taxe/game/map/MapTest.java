package com.taxe.game.map;

import com.taxe.GdxTestRunner;
import com.taxe.game.map.Map;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.nodes.Node;
import com.taxe.game.tracks.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class MapTest {

    public Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map("nodes.json", "tracks.json");
    }

    @Test
    public void testGetTracksWith() throws Exception {
        // Searching for a track with existing node
        List<Track> t1 = map.getTracksWith(Node.getNodeById("London", map.getNodes()));
        assertEquals(3, t1.size());
        String[] s1 = {"Red", "RedLondon-1", "RedLondon-2", "London"};
        String[] s2 = {"London", "LondonParis-1", "LondonParis-2", "LondonParis-3", "Paris"};
        String[] s3 = {"London", "LondonVenice-1", "LondonVenice-2", "Venice"};
        assertTrue(containsAll(t1.get(0).getNodes(), s1));
        assertTrue(containsAll(t1.get(1).getNodes(), s2));
        assertTrue(containsAll(t1.get(2).getNodes(), s3));

        // Searching for a track with non-existing node
        assertEquals(0, map.getTracksWith(new City()).size());

    }

    @Test
    public void testGetTrackWith() throws Exception {
        // Searching for a track with existing nodes
        Node n1 = Node.getNodeById("Paris", map.getNodes());
        Node n2 = Node.getNodeById("London", map.getNodes());
        String[] s1 = {"Paris", "London", "LondonParis-1", "LondonParis-2", "LondonParis-3"};
        assertTrue(containsAll(map.getTrackWith(n1, n2).getNodes(), s1));

        // Searching for a track with non-existing node
        assertNull(map.getTrackWith(new City(), new Homebase()));
    }

    /**
     * Checks if a list of nodes contains only nodes with given ids
     * @param nodes list of nodes
     * @param ids list of ids
     */
    public boolean containsAll(List<Node> nodes, String[] ids) {
        if (nodes.size() != ids.length) return false;
        for (String id: ids) {
            if (Node.getNodeById(id, nodes) == null)
                return false;
        }
        return true;
    }
}