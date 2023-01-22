package edu.geekhub.homework.simulator;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.Scooter;
import edu.geekhub.homework.transport.VehicleType;

import java.util.concurrent.ThreadLocalRandom;

public class VehicleGenerator {
    private TrackMap trackMap;

    public VehicleGenerator(TrackMap trackMap) {
        this.trackMap = trackMap;
    }

    public void generateVehicle() {
        synchronized (trackMap) {
//            Car newCar = new Car(getGenerationLocation(), trackMap);
//            newCar.envy();
//            Vehicle newVehicle = new Scooter(getGenerationLocation(), trackMap);
//            newVehicle.envy();
//            Vehicle newVehicle = new Truck(getGenerationLocation(), trackMap);
//            newVehicle.envy();
            VehicleType vehicleType = VehicleType.randomVehicleType();
            switch (vehicleType) {
                case CAR -> new Car(getGenerationLocation(), trackMap).envy();
                case SCOOTER -> new Scooter(getGenerationLocation(), trackMap).envy();
            }
        }
    }

//    private void setToLocation(Car car) {
//        trackMap.getTrackElements().put(location, car);
//    }

    private Point getGenerationLocation() {
        Point generationLocation;
        do {
            generationLocation = getRandomStartLocation();
        } while (trackMap.getTrackElements().get(generationLocation).isPresent());
        return generationLocation;
    }

    private Point getRandomStartLocation() {
        return trackMap
            .getStartLocation()
            .get(
                ThreadLocalRandom
                    .current()
                    .nextInt(trackMap.getStartLocation().size())
            );
    }
}
