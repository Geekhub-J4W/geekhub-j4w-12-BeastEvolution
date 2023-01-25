package edu.geekhub.homework.transport;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.controller.Track;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.Set;

import static edu.geekhub.homework.transport.VehicleState.TOWED;

public class TowTruck {
    private final Track track;
    private final Point callLocation;

    public TowTruck(Track track, Point callLocation) {
        this.track = track;
        this.callLocation = callLocation;
    }

    public void towCarsFromLocation() {
        track.getLocationContent(callLocation).stream().forEach((vehicle) -> System.out.print(vehicle + " : "));
        System.out.println("was towed from location : " + callLocation);
        markAllVehicleOnLocation(track.getLocationContent(callLocation));
        setNewLocationForAllVehicle(track.getLocationContent(callLocation));
        track.deleteAllVehicleFromLocation(callLocation);
    }

    private void markAllVehicleOnLocation(Set<Vehicle> locationContent) {
        locationContent.stream().forEach(vehicle -> vehicle.setState(TOWED));
    }
    private void setNewLocationForAllVehicle(Set<Vehicle> locationContent) {
        locationContent.stream().forEach(vehicle -> vehicle.setLocation(null));
    }
}
