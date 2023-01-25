package edu.geekhub.homework.simulator;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.VehicleType;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.concurrent.ThreadLocalRandom;

public class VehicleGenerator {
    private final TrackMap trackMap;
    private final Track track;

    public VehicleGenerator(TrackMap trackMap, Track track) {
        this.trackMap = trackMap;
        this.track = track;
    }

    public Vehicle spawnVehicle(VehicleType vehicleType) {
        Vehicle vehicle;
        switch (vehicleType) {
            case CAR -> {
                Point spawnLocation = getSpawnLocation();
                vehicle = new Car(spawnLocation, track, vehicleType);
                track.setVehicleOnMapLocation(vehicle, spawnLocation);
                return vehicle;
            }
            case SCOOTER -> {
                Point spawnLocation = getSpawnLocation();
                vehicle = new Car(spawnLocation, track, vehicleType);
                track.setVehicleOnMapLocation(vehicle, spawnLocation);
                return vehicle;
            }
            case TRUCK -> {
                Point spawnLocation = getSpawnLocation();
                vehicle = new Car(spawnLocation, track, vehicleType);
                track.setVehicleOnMapLocation(vehicle, spawnLocation);
                return vehicle;
            }
        }
        throw new RuntimeException("Error vehicle type");
    }

    private Point getSpawnLocation() {
        Point generationLocation;
        do {
            generationLocation = getRandomSpawnLocation();
        } while (!track.getLocationContent(generationLocation).isEmpty());
        return generationLocation;
    }

    private Point getRandomSpawnLocation() {
        return trackMap
            .getStartLocation()
            .get(
                ThreadLocalRandom
                    .current()
                    .nextInt(trackMap.getStartLocation().size())
            );
    }
}
