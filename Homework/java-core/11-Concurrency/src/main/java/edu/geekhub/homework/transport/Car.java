package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.Direction;
import edu.geekhub.homework.track.TrackMap;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.Optional;

public class Car implements Vehicle, Runnable {
    private static int counter = 0;
    private Point location;
    private final TrackMap trackMap;
    private final int index;
    private final Thread thread;
    private CarState state;

    public Car(Point location, TrackMap trackMap) {
        this.location = location;
        this.trackMap = trackMap;
        this.index = counter;
        counter++;
        state = CarState.STANDS;
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
        try {
            state = CarState.RUN;
            while (state == CarState.RUN) {
                move();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (RuntimeException e) {
            throw e;
//            System.out.println(
//                String.format("%s: %s",this, e.getMessage())
//            );
//            return;
        }
        printResult();
        if(state == CarState.FINISHED) {
            System.exit(0);
        }
    }

    private void printResult() {
        if (state == CarState.FINISHED) {
            System.out.println(
                String.format("Finished: %s no position %s%n", this, location)
            );
            System.out.println("Finish location: ");
            trackMap.getFinishLocation().stream().forEach(System.out::println);
            System.out.println("Start location: ");
            trackMap.getStartLocation().stream().forEach(System.out::println);
        }
        if (state == CarState.OUTROAD) {
            System.out.println(this + " : Car pulled out the road. To location:" + location);
        }
        if (state == CarState.CRASH) {
            System.out.println(this + " : Cars collided in one square " + location);
        }
    }

    private Point move() {
        synchronized (trackMap) {
            changePosition();
            System.out.println(this + " : " + location);
            return location;
        }
    }

    private void changePosition() {
        deleteFromOldLocation(location);
        setOnNewLocation(getNewLocation());
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
        return (state != CarState.OUTROAD && state != CarState.CRASH);
    }

    private void browseLocation(Point location) {
        if (isLocationNotOnRoad(location)) {
            state = CarState.OUTROAD;
            System.out.println(this + " : Car pulled out the road. To location:" + location);
            return;
        }
        if (isLocationBusy(location)) {
            state = CarState.CRASH;
            changeStateOfCarOnLocation(location, CarState.CRASH);
            deleteFromOldLocation(location);
            System.out.println("Delete from location : " + location);
            return;
        }
        if (isMoveToFinishLocation()) {
            state = CarState.FINISHED;
            return;
        }
    }

    private void changeStateOfCarOnLocation(Point location, CarState state) {
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

    private boolean isMoveToFinishLocation() {
        return trackMap.getFinishLocation().contains(location);
    }

    private Point getNewLocation() {
        Direction direction = Direction.randomDirection();
        return location.createRespectToThis(direction.getX(), direction.getY());
    }

    @Override
    public String toString() {
        return "Car{" +
            "index=" + index +
            '}';
    }

    public void setState(CarState state) {
        this.state = state;
    }
}
