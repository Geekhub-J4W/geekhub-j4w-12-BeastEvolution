package edu.geekhub.homework.simulator;

import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.transport.Delays;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class RaceSimulatorService {
    TrackMap trackMap = TrackMap.generate();
    VehicleGenerator vehicleGenerator = new VehicleGenerator(trackMap);
//    Map<Point, Optional<Vehicle>> track = trackMap.getTrackElements().stream()
//        .collect(Collectors.toMap(
//            trackElement -> trackElement.location,
//            TrackElement::getVehicle));
//    Set<TrackElement> mapElements;
//    Set<Point> finish = trackMap.getTrackFinish().getElementBlocks().stream()
//        .map(trackElement -> trackElement.location)
//        .collect(Collectors.toSet());

    public void run() throws InterruptedException {
        ExecutorService executorService = new ForkJoinPool();
        int i = 0;
        while (true) {
//            executorService.submit(vehicleGenerator.generateVehicle());
            vehicleGenerator.generateVehicle();
            Thread.sleep(Delays.getRandomDelayInMillis(500, 1000));
        }
    }

}
