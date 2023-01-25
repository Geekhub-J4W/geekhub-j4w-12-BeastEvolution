package edu.geekhub.homework.transport.controllers;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.LocationService;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.TowTruck;
import edu.geekhub.homework.transport.controllers.interfaces.VehicleController;
import edu.geekhub.homework.transport.util.Delays;

import static edu.geekhub.homework.transport.VehicleState.CRASH;
import static edu.geekhub.homework.transport.VehicleState.NORMAL;

public class CarController implements VehicleController {
    private static final int MOVE_DISTANCE = 1;

    private final Thread thread;
    private final TrackMap trackMap;
    private final Track track;
    private final Car car;
    private Point nextLocation;
    LocationService locationService;

    public CarController(Car car, Track track, TrackMap trackMap, LocationService locationService) {
        this.car = car;
        this.track = track;
        this.thread = new Thread(this);
        this.trackMap = trackMap;
        this.locationService = locationService;
    }

    public void crankUp() {
        thread.start();
    }

@Override
    public void run() {
        while (car.getState() == NORMAL) {
            nextLocation = locationService.chooseNewRandomLocation(MOVE_DISTANCE, car.getLocation());
            synchronized (track) {
                car.move(nextLocation);
                if (car.getState() == CRASH) {
                    callTowCar();
                }
                if (isCarOnFinishLocation(car.getLocation())) {
                    printResult();
                    System.exit(0);
                }
            }
            try {
                Thread.sleep(Delays.getRandomDelayInMillis(500, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printResult() {
        System.out.printf("%s : Finished on location : %s%n", car, car.getLocation());
        System.out.println("Finish location: ");
        trackMap.getFinishLocation().forEach(System.out::println);
        System.out.println("Start location: ");
        trackMap.getStartLocation().forEach(System.out::println);

    }

    private void callTowCar() {
        TowTruck calledTowTruck = new TowTruck(track, car.getLocation());
        calledTowTruck.towCarsFromLocation();
    }

    private boolean isCarOnFinishLocation(Point location) {
        return trackMap.getFinishLocation().contains(location);
    }

    public Point getNextLocation() {
        return nextLocation;
    }

    //    private Point chooseNewLocation() {
//        Direction direction = Direction.randomDirection();
//        return carLocation.createRespectToThis(direction.getX(MOVE_DISTANCE), direction.getY(MOVE_DISTANCE));
//    }
}
