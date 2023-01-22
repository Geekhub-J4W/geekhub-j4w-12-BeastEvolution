package edu.geekhub.homework.track;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.generator.RandomTrackGenerator;
import edu.geekhub.homework.track.interfaces.TrackGenerator;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public final class TrackMap {
    private final Map<Point, Optional<Vehicle>> trackElements;
    private final List<Point> startLocation;
    private final List<Point> finishLocation;

    public TrackMap(Map<Point, Optional<Vehicle>> trackElements, List<Point> startLocation, List<Point> finishLocation) {
        this.trackElements = Collections.synchronizedMap(trackElements);
        this.startLocation = startLocation;
        this.finishLocation = finishLocation;
    }

    public Map<Point, Optional<Vehicle>> getTrackElements() {
        return trackElements;
    }

    public List<Point> getStartLocation() {
        return startLocation;
    }

    public List<Point> getFinishLocation() {
        return finishLocation;
    }

    public static TrackMap generate() {
        TrackGenerator randomTrackGenerator = new RandomTrackGenerator(3, 20);
        List<TrackBlock> trackBlocks = randomTrackGenerator.generateTrack();

        return new TrackMap(
            trackBlocks.stream()
                .flatMap(trackBlock -> trackBlock.getElementBlocks().stream())
                .collect(toSet()).stream()
                .collect(toMap(TrackElement::getLocation, TrackElement::getVehicle)),
            trackBlocks.get(0).getElementBlocks().stream()
                .map(TrackElement::getLocation)
                .toList(),
            trackBlocks.get(trackBlocks.size() - 1).getElementBlocks().stream()
                .map(TrackElement::getLocation)
                .toList()
        );

//        return new TrackMap(trackBlocks.stream()
//            .flatMap(trackBlock -> trackBlock.getElementBlocks().stream())
//            .collect(Collectors.toMap(TrackElement::getLocation, TrackElement::getVehicle))
//            , trackBlocks.get(0).getElementBlocks().stream().toList(),
//            trackBlocks.get(trackBlocks.size() - 1).getElementBlocks().stream().toList()
//        );


    }
}
