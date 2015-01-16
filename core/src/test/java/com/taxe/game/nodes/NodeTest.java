package com.taxe.game.nodes;

import com.taxe.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class NodeTest {

    private City ca, cb, cc;
    private Homebase ha, hb, hc;
    private IntermediatePoint ia;
    private Junction ja;
    private List<Node> nlist;

    @Before
    public void setUp() {
//        ca = new City(new Coordinate(176, 210), "ca");
//        cb = new City(new Coordinate(1140, 639), "cb");
//        cc = new City(new Coordinate(242, 706), "cc");
//        ha = new Homebase(new Coordinate(29, 536), "ha");
//        hb = new Homebase(new Coordinate(1140, 639), "cb");
//        hc = new Homebase(new Coordinate(29, 536), "ha");
//        ia = new IntermediatePoint(new Coordinate(47, 470), "ia");
//        ja = new Junction(new Coordinate(176, 210), "ja");
        nlist = new ArrayList<>();
        Collections.addAll(nlist, ca, cb, cc, ha, ia, ja);
    }

    @Test
    public void testGetNodeWithId() throws Exception {
        // Non-existing nodes in empty and not empty lists
        assertNull(Node.getNodeById("ca", new ArrayList<>()));
        assertNull(Node.getNodeById("xx", nlist));

        // Existing nodes in the beginning, middle and end of the list
        assertEquals(ca, Node.getNodeById("ca", nlist));
        assertEquals(ha, Node.getNodeById("ha", nlist));
        assertEquals(ja, Node.getNodeById("ja", nlist));

    }

    @Test
    public void testReadNodes() throws Exception {
        List<Node> readList = new ArrayList<>(Node.readNodes("sample-nodes.json"));
        assertEquals(nlist, readList);
    }

    @Test
    public void testEquals() throws Exception {
        // Compare to null and to itself
        assertFalse(ca.equals(null));
        assertTrue(ca.equals(ca));

        // Compare objects of the same class
        assertFalse(ca.equals(cb));
        assertTrue(ha.equals(hc));

        // Compare objects of different classes
        assertFalse(cb.equals(ha));
        assertFalse(hb.equals(cb));
        assertFalse(ia.equals(ja));
        assertFalse(ja.equals(ca));
    }
}