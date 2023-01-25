package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.controller.LocationNonexistentException;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.controllers.interfaces.VehicleController;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import static edu.geekhub.homework.transport.VehicleState.*;

public class Car implements Vehicle {
    private static int counter = 0;
    private Point location;
    private final int index;
    private VehicleState state;
    private final Track track;
    private final VehicleType vehicleType;
    private VehicleController vehicleController;

    public Car(Point location, Track track, VehicleType vehicleType) {
        this.location = location;
        this.track = track;
        this.index = counter;
        this.vehicleType = vehicleType;
        counter++;
        state = NORMAL;
//        spawn();
    }

    public void setVehicleController(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }

    //    private void spawn() {
//        track.setVehicleOnMapLocation(this, location);
//        System.out.println(this + " : Spawn on location : " + location);
//    }
    public void move(Point newLocation) {
        synchronized (track) {
            Point oldLocation = location;
            track.deleteVehicleFromLocation(this, location);
            try {
                track.setVehicleOnMapLocation(this, newLocation);
                location = newLocation;
                if (isAccidentHappened(location)) {
                    state = CRASH;
                    System.out.printf("%s :CRASH: move from %s to %s%n", this, oldLocation, newLocation);
                    return;
                }
                System.out.printf("%s :NORMAL: move from %s to %s%n", this, oldLocation, newLocation);
            } catch (LocationNonexistentException e) {
                System.out.printf("%s :OUTROAD: move from %s to %s%n", this, oldLocation, newLocation);
                location = null;
                state = OUTROAD;
            }
        }
    }

    private boolean isAccidentHappened(Point Location) {
        return track.getLocationContent(location).size() > 1;
    }

    @Override
    public String toString() {
        return vehicleType + "{" +
            "index=" + index +
            '}';
    }

    public synchronized void setState(VehicleState state) {
        this.state = state;
    }

    public VehicleState getState() {
        return state;
    }

    @Override
    public synchronized Point getLocation() {
        return location;
    }

    @Override
    public synchronized void setLocation(Point location) {
        this.location = location;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }
}
