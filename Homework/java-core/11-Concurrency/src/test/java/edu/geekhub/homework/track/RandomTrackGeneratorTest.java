package edu.geekhub.homework.track;

import edu.geekhub.homework.track.generator.RandomTrackGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;


class RandomTrackGeneratorTest {
    @Test
    @Tag("Correct")
    void Create_random_track() {
        RandomTrackGenerator generator = new RandomTrackGenerator(3,100);
        List<TrackBlock> track = generator.generateTrack();

//        track.stream()
//            .forEach(System.out::println);

//        track.stream()
//            .flatMap(trackElement -> trackElement.getElementBlocks().stream())
//            .filter()

        track.stream()
            .map(trackElement -> {
                System.out.println(trackElement);
                return trackElement.getElementBlocks().stream();
            })
            .forEach(pointStream -> pointStream.forEach(System.out::println));
    }
}
