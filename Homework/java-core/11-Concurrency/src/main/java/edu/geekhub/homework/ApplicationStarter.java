package edu.geekhub.homework;

import edu.geekhub.homework.simulator.RaceSimulatorService;

public class ApplicationStarter {
    public static void main(String[] args) throws InterruptedException {
        RaceSimulatorService service = new RaceSimulatorService();
        service.run();
    }
}
