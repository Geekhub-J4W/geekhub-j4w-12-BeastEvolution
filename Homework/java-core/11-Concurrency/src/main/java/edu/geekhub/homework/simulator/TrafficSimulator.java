package edu.geekhub.homework.simulator;

import edu.geekhub.homework.track.LocationService;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.VehicleType;
import edu.geekhub.homework.transport.controllers.CarController;
import edu.geekhub.homework.transport.controllers.ScooterController;
import edu.geekhub.homework.transport.controllers.TruckController;
import edu.geekhub.homework.transport.util.Delays;

public class TrafficSimulator {
    TrackMap trackMap = TrackMap.generate();
    Track track = new Track(trackMap.getTrackElements());
    VehicleGenerator vehicleGenerator = new VehicleGenerator(trackMap, track);

    public void run() throws InterruptedException {
        while (true) {
            Car car = (Car) vehicleGenerator.spawnVehicle(VehicleType.randomVehicleType());
            switch (car.getVehicleType()) {
                case CAR -> {
                    CarController carController = new CarController(car, track, trackMap, new LocationService());
                    car.setVehicleController(carController);
                    carController.crankUp();
                }
                case SCOOTER -> {
                    ScooterController scooterController = new ScooterController(car, track, trackMap, new LocationService());
                    car.setVehicleController(scooterController);
                    scooterController.crankUp();
                }
                case TRUCK -> {
                    TruckController scooterController = new TruckController(car, track, trackMap, new LocationService());
                    car.setVehicleController(scooterController);
                    scooterController.crankUp();
                }
            }
            Thread.sleep(Delays.getRandomDelayInMillis(500, 1000));
        }
    }

}
