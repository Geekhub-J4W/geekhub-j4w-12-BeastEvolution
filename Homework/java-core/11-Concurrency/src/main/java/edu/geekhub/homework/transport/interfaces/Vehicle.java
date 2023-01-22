package edu.geekhub.homework.transport.interfaces;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.transport.VehicleState;

public interface Vehicle extends Runnable {
    void envy();
    void setState(VehicleState state);
    Point getLocation();
}
