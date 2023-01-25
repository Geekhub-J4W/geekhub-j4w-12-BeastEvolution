package edu.geekhub.homework;

import edu.geekhub.homework.simulator.TrafficSimulator;

public class ApplicationStarter {
    public static void main(String[] args) throws InterruptedException {
        TrafficSimulator service = new TrafficSimulator();
        service.run();
    }
}
