package edu.geekhub.homework.track.generator;

import edu.geekhub.homework.track.Direction;
import edu.geekhub.homework.track.TrackBlock;
import edu.geekhub.homework.track.interfaces.TrackGenerator;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.ThreadLocalRandom.current;

public final class RandomTrackGenerator implements TrackGenerator {
    private static final int MOVE_DISTANCE = 1;
    public static final int INDEX_OF_FIRST_ELEMENT = 0;
    private final int minSize;
    private final int maxSize;

    public RandomTrackGenerator(int minSize, int maxSize) {
        if (minSize >= 3) {
            this.minSize = minSize;
        } else {
            throw new IllegalArgumentException(
                "Min size can't be less than 3, but passed: "
                + minSize
            );
        }
        if (maxSize >= 3) {
            this.maxSize = maxSize;
        } else {
            throw new IllegalArgumentException(
                "Min size can't be less than 3, but passed: "
                    + minSize
            );
        }
    }

    @Override
    public List<TrackBlock> generateTrack() {
        List<TrackBlock> track = generateStart();
        track = generateRoad(track);

        track = generateFinish(track);

        return track;
    }

    private List<TrackBlock> generateStart() {
        return List.of(new TrackBlock(0,0));
    }

    private List<TrackBlock> generateRoad(List<TrackBlock> generatedRoad) {
        List<TrackBlock> road = new ArrayList<>(generatedRoad);
        TrackBlock currentElement = getLast(road);

        int size = current().nextInt(minSize, maxSize);
        for (int i = 0; i < size; i++) {
            currentElement = generateNext(currentElement);
            road.add(currentElement);
        }

        return road;
    }

    private TrackBlock getLast(List<TrackBlock> generatedRoad) {
        return generatedRoad.get(generatedRoad.size() - 1);
    }

    private TrackBlock generateNext(TrackBlock current) {
        Direction direction = Direction.randomDirection();
        return current.createRespectToThis(direction.getX(MOVE_DISTANCE), direction.getY(MOVE_DISTANCE));
    }

    private List<TrackBlock> generateFinish(List<TrackBlock> generatedRoad) {
        List<TrackBlock> road = new ArrayList<>(generatedRoad);
        TrackBlock startElement = getStart(road);
        TrackBlock finishElement;
        do {
            finishElement = generateNext(getLast(road));
        } while (finishElement == startElement);

        return road;
    }

    private TrackBlock getStart(List<TrackBlock> generatedRoad) {
        return generatedRoad.get(INDEX_OF_FIRST_ELEMENT);
    }
}
