package edu.geekhub.homework.track;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TrackBlockTest {
    @Test
    @Tag("Correct")
    void Create_track_element() {
        TrackBlock trackBlock = new TrackBlock(-10, 1);
        TrackBlock trackBlock11 = new TrackBlock(-9, 1);
        System.out.println(trackBlock.getElementBlocks().containsAll(trackBlock11.getElementBlocks()));
        trackBlock.getElementBlocks().stream().forEach(System.out::println);
    }
}
