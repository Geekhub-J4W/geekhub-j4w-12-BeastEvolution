package edu.geekhub.homework.transport.controllers;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.LocationService;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.Car;
import edu.geekhub.homework.transport.TowTruck;
import edu.geekhub.homework.transport.controllers.interfaces.VehicleController;
import edu.geekhub.homework.transport.interfaces.Vehicle;
import edu.geekhub.homework.transport.util.Delays;

import static edu.geekhub.homework.transport.VehicleState.CRASH;
import static edu.geekhub.homework.transport.VehicleState.NORMAL;
import static edu.geekhub.homework.transport.VehicleType.TRUCK;
import static edu.geekhub.homework.transport.controllers.TruckControllerStatus.*;

public class TruckController implements VehicleController {
    private static final int MOVE_DISTANCE = 1;

    private final Thread thread;
    private final TrackMap trackMap;
    private final Track track;
    private final Car car;
    private final LocationService locationService;
    private TruckControllerStatus controllerStatus;
    private Point nextLocation;

    public TruckController(Car car, Track track, TrackMap trackMap, LocationService locationService) {
        this.car = car;
        this.track = track;
        this.thread = new Thread(this);
        this.trackMap = trackMap;
        this.locationService = locationService;
        this.controllerStatus = STAND;
    }

    @Override
    public Point getNextLocation() {
        return nextLocation;
    }

    public void crankUp() {
        controllerStatus = RUN;
        thread.start();
    }

    @Override
    public void run() {
        controllerStatus = RUN;
        while (car.getState() == NORMAL) {
            if (controllerStatus == RUN) {
                nextLocation = locationService.chooseNewRandomLocation(MOVE_DISTANCE, car.getLocation());
            }
            synchronized (track) {
                if (!track.isLocationExist(nextLocation) || track.getLocationContent(nextLocation).isEmpty()) {
                    controllerStatus = RUN;
                    car.move(nextLocation);

                    if (car.getState() == CRASH) {
                        callTowCar();
                    }
                    if (isCarOnFinishLocation(car.getLocation())) {
                        printResult();
                        System.exit(0);
                    }
                } else {
                    if (isDeadLock()) {
                        System.out.printf("DeadLock : on location : %s : %s%n", car.getLocation(), nextLocation);
                        new TowTruck(track, car.getLocation()).towCarsFromLocation();
                        new TowTruck(track, nextLocation).towCarsFromLocation();
                        return;
                    }

                    System.out.printf("%s : WAIT on location : %s : when run to : %s%n", car, car.getLocation(), nextLocation);
                    controllerStatus = WAIT;
                }
            }
            try {
                Thread.sleep(Delays.getRandomDelayInMillis(500, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isDeadLock() {
        if (track.getLocationContent(nextLocation).size() == 1) {
            Vehicle vehicleOnNextLocation = track.getLocationContent(nextLocation).stream().findFirst().get();
            if(vehicleOnNextLocation.getVehicleType() == TRUCK) {
                return vehicleOnNextLocation.getVehicleController().getNextLocation().equals(car.getLocation());
            }
        } else {
            throw new RuntimeException("Location must contain only one vehicle");
        }

        return false;
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

}
