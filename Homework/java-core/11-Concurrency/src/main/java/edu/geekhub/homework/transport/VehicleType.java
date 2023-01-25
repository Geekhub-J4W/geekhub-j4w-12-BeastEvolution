package edu.geekhub.homework.transport;

import java.util.concurrent.ThreadLocalRandom;

public enum VehicleType {
    CAR, SCOOTER, TRUCK;

    public static VehicleType randomVehicleType() {
        int pick = ThreadLocalRandom.current().nextInt(values().length);
        return VehicleType.values()[pick];
    }
}
