package com.taxe.game.tracks;

import com.taxe.GdxTestRunner;
import com.taxe.game.nodes.Node;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(GdxTestRunner.class)
public class TrackTest {

    /**
     * Tests if creating a track with non-existent id throws an exception
     * @throws Exception
     */
    @Test
    public void testReadTracks() throws Exception {
        // Trying to create tracks with non-existent id
        try {
            List<Node> nodes = Node.readNodes("nodes.json");
            List<Track> tracks = Track.readTracks("tracks-non-existent-id.json", nodes);
            fail();
        }
        catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("can't construct track with non-existent id"));
        }

        // Trying to read tracks with existent id
        try {
            List<Node> nodes = Node.readNodes("nodes.json");
            List<Track> tracks = Track.readTracks("tracks.json", nodes);
        }
        catch (RuntimeException e) {fail();}
    }
}