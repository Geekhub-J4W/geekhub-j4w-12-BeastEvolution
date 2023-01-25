package edu.geekhub.homework.transport.controllers.interfaces;

import edu.geekhub.homework.geometry.Point;

public interface VehicleController extends Runnable {
    Point getNextLocation();
}
