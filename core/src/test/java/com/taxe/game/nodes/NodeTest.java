package com.taxe.game.nodes;

import com.taxe.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class NodeTest {

    /**
     * Testing if reading json-file with duplicating ids throws an exception.
     *
     * @throws Exception
     */
    @Test
    public void testReadNodes() throws Exception {
        // Some of the nodes in file have same ids
        try {
            List<Node> nodes = Node.readNodes("nodes-same-id.json");
            fail();
        } catch (RuntimeException e) {
            assertEquals("two or more nodes have same ids", e.getMessage());
        }

        // All nodes in file have different ids
        try {
            List<Node> nodes = Node.readNodes("nodes.json");
        } catch (RuntimeException e) {
            fail();
        }
    }

    /**
     * Testing if searching for a node with specific id returns null when such node doesn't exist.
     *
     * @throws Exception
     */
    @Test
    public void testGetNodeById() throws Exception {
        List<Node> nodes = Node.readNodes("nodes.json");

        // No nodes with given id
        assertNull(Node.getNodeById("Jerusalem", nodes));

        // There is node with given id
        assertEquals("London", Node.getNodeById("London", nodes).getId());
        assertEquals("Stockholm", Node.getNodeById("Stockholm", nodes).getId());
        assertEquals("Moscow", Node.getNodeById("Moscow", nodes).getId());
    }

}