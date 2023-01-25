package edu.geekhub.homework.transport.interfaces;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.VehicleState;
import edu.geekhub.homework.transport.VehicleType;
import edu.geekhub.homework.transport.controllers.interfaces.VehicleController;

public interface Vehicle {
    VehicleState getState();
    void setState(VehicleState state);
    Point getLocation();
    void setLocation(Point location);
    VehicleType getVehicleType();
    VehicleController getVehicleController();
}
