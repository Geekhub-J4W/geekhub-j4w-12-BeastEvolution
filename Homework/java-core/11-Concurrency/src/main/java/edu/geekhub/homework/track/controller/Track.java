package edu.geekhub.homework.track.controller;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.Map;
import java.util.Set;

public final class Track {
    private final Map<Point, Set<Vehicle>> trackLocations;

    public Track(Map<Point, Set<Vehicle>> trackLocations) {
        this.trackLocations = trackLocations;
    }

    public synchronized void setVehicleOnMapLocation(Vehicle vehicle, Point location) {
        if (isLocationExist(location)) {
            Set<Vehicle> locationContent = trackLocations.get(location);
            locationContent.add(vehicle);
        } else {
            throw new LocationNonexistentException(String.format("Location %s not exist on map %s", location, trackLocations));
        }
    }

    public synchronized boolean deleteVehicleFromLocation(Vehicle vehicle, Point location) {
        if (isLocationExist(location)) {
            Set<Vehicle> locationContent = trackLocations.get(location);
            return locationContent.remove(vehicle);
        } else {
            throw new LocationNonexistentException(String.format("Location %s not exist on map %s", location, trackLocations));
        }
    }

    public synchronized void changeVehicleLocation(Vehicle vehicle, Point oldLocation, Point newLocation) {
            deleteVehicleFromLocation(vehicle, oldLocation);
            setVehicleOnMapLocation(vehicle, newLocation);
    }

    public synchronized Set<Vehicle> getLocationContent(Point location) {
        return trackLocations.get(location);
    }

    public synchronized boolean isLocationExist(Point location) {
        return trackLocations.containsKey(location);
    }
    public synchronized void deleteAllVehicleFromLocation(Point location) {
        trackLocations.get(location).clear();
    }
}
