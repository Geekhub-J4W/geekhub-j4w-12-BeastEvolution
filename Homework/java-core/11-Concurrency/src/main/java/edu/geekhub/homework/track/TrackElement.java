package edu.geekhub.homework.track;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.interfaces.Vehicle;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class TrackElement {
    private final Point location;
    private Set<Vehicle> vehicle;

    public TrackElement(Point location) {
        this.location = location;
        vehicle = new HashSet<>();
    }

    public int getXCoordinate() {
        return location.x();
    }

    public int getYCoordinate() {
        return location.y();
    }

    public Point getLocation() {
        return location;
    }

    public Set<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(Set<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "TrackElement{" +
            "location=" + location +
            ", vehicle=" + vehicle +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackElement that = (TrackElement) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }


}
