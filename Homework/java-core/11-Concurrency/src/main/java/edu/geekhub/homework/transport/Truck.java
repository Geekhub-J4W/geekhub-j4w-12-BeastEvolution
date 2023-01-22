package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.Direction;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.Optional;

public class Truck implements Vehicle, Runnable {
    private static final int MOVE_DISTANCE = 1;
    private static int counter = 0;
    private Point location;
    private Point nextLocation;
    private final TrackMap trackMap;
    private final int index;
    private final Thread thread;
    private VehicleState state;

    public Truck(Point location, TrackMap trackMap) {
        this.location = location;
        this.trackMap = trackMap;
        this.index = counter;
        counter++;
        state = VehicleState.STANDS;
        setToLocation();
        thread = new Thread(this);
    }

    private void setToLocation() {
        trackMap.getTrackElements().put(location, Optional.of(this));
        System.out.println(this+ " : Spawn on location : " + location);
    }

    public void envy() {
        thread.start();
    }

    @Override
    public void run() {
        state = VehicleState.RUN;
        while (state == VehicleState.RUN || state == VehicleState.WAIT) {
            move();
            try {
                Thread.sleep(Delays.getRandomDelayInMillis(500, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        printResult();
        if(state == VehicleState.FINISHED) {
            System.exit(0);
        }
    }

    private void printResult() {
        if (state == VehicleState.FINISHED) {
            System.out.println(
                String.format("Finished: %s no position %s%n", this, location)
            );
            System.out.println("Finish location: ");
            trackMap.getFinishLocation().forEach(System.out::println);
            System.out.println("Start location: ");
            trackMap.getStartLocation().forEach(System.out::println);
        }
        if (state == VehicleState.OUTROAD) {
            System.out.println(this + " : Car pulled out the road. To location:" + location);
        }
        if (state == VehicleState.CRASH) {
            System.out.println(this + " : Cars collided in one square " + location);
        }
    }

    private void move() {
        synchronized (trackMap) {
            changePosition();
            System.out.println(this + " : " + location);
        }
    }

    private void changePosition() {
        nextLocation = getNewLocation();
        if (isLocationNotOnRoad(location)) {
            state = VehicleState.OUTROAD;
            System.out.println(this + " : Car pulled out the road. To location:" + location);
            deleteFromOldLocation(location);
            return;
        }
        if (isLocationBusy(nextLocation)) {
            state = VehicleState.WAIT;
            checkDeadLock();
            return;
        }


        deleteFromOldLocation(location);
        setOnNewLocation(getNewLocation());
    }

    private void checkDeadLock() {
        if (trackMap.getTrackElements().get(nextLocation).isPresent()) {
            if (trackMap.getTrackElements().get(nextLocation).get() instanceof Truck) {
                Truck truckOnNextLocation = (Truck) trackMap.getTrackElements().get(nextLocation).get();
                if (truckOnNextLocation.getNextLocation().equals(nextLocation)) {
                    System.out.printf(
                        this + " : %s and %s tow on: %s and %s%n",
                        this,
                        truckOnNextLocation,
                        location,
                        truckOnNextLocation.getLocation()
                    );
                    towVehicle(this);
                    towVehicle(truckOnNextLocation);
                }
            }
        }
    }

    private void towVehicle(Vehicle vehicle) {
        vehicle.setState(VehicleState.TOW);
        deleteFromOldLocation(vehicle.getLocation());
    }

    private void deleteFromOldLocation(Point location) {
        if (trackMap.getTrackElements().get(location).isEmpty()) {
            throw new LocationException(
                String.format(
                    "Vehicle must be on %s location, but track element was empty%n"
                    , location
                )
            );
        } else {
            trackMap.getTrackElements().put(location, Optional.empty());
        }
    }

    private void setOnNewLocation(Point newLocation) {
        browseLocation(newLocation);
        if(carIsNotTowed()) {
            trackMap.getTrackElements().put(newLocation, Optional.of(this));
            location = newLocation;
        } else {
            location = null;
        }
//        if (!trackMap.getTrackElements().containsKey(newLocation)) {
//            throw new CrashException("Car pulled out the road.\nTo location:" + newLocation);
//        }
//        if (trackMap.getTrackElements().get(newLocation).isEmpty()) {
//            trackMap.getTrackElements().put(newLocation, Optional.of(this));
//            location = newLocation;
//        } else {
//            throw new CrashException("Cars collided in one square");
//        }
    }

    private boolean carIsNotTowed() {
        return (state != VehicleState.OUTROAD && state != VehicleState.CRASH);
    }

    private void browseLocation(Point location) {
        if (isLocationNotOnRoad(location)) {
            state = VehicleState.OUTROAD;
            System.out.println(this + " : Car pulled out the road. To location:" + location);
            return;
        }
        if (isLocationBusy(location)) {
            state = VehicleState.CRASH;
            changeStateOfCarOnLocation(location, VehicleState.CRASH);
            deleteFromOldLocation(location);
            System.out.println("Delete from location : " + location);
            return;
        }
        if (isMoveToFinishLocation(location)) {
            state = VehicleState.FINISHED;
            return;
        }
    }

    private void changeStateOfCarOnLocation(Point location, VehicleState state) {
        if (trackMap.getTrackElements().get(location).isPresent()) {
            Vehicle car = trackMap.getTrackElements().get(location)
                .get();
            car.setState(state);
            System.out.printf(this + " : %s and %s crash on: %s%n", this, car, location);
        } else {
            throw new LocationException(
                String.format("On location %s must be a car, but wasn't%n", location)
            );
        }
    }

    private boolean isLocationNotOnRoad(Point location) {
        return !trackMap.getTrackElements().containsKey(location);
    }

    private boolean isLocationBusy(Point location) {
        return trackMap.getTrackElements().get(location).isPresent();
    }

    private boolean isMoveToFinishLocation(Point location) {
        return trackMap.getFinishLocation().contains(location);
    }

    private Point getNewLocation() {
        Direction direction = Direction.randomDirection();
        return location.createRespectToThis(direction.getX(MOVE_DISTANCE), direction.getY(MOVE_DISTANCE));
    }

    @Override
    public String toString() {
        return "Truck{" +
            "index=" + index +
            '}';
    }

    public void setState(VehicleState state) {
        this.state = state;
    }

    public Point getNextLocation() {
        return nextLocation;
    }

    @Override
    public Point getLocation() {
        return location;
    }
}
