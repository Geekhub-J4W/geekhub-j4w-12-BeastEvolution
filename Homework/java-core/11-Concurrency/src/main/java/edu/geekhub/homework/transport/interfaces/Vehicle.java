package edu.geekhub.homework.transport.interfaces;

import edu.geekhub.homework.transport.CarState;

public interface Vehicle extends Runnable {
    void envy();

    void setState(CarState state);
}
