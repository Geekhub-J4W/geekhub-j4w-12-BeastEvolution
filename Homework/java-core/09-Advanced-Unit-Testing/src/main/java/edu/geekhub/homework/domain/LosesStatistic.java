package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.LosesStatisticHttpClient;

/**
 * This class should contain data received through {@link LosesStatisticHttpClient}
 * via <a href="https://en.wikipedia.org/wiki/JSON">JSON</a> String
 */
public record LosesStatistic(int tanks,
                             int armouredFightingVehicles,
                             int cannons,
                             int multipleRocketLaunchers,
                             int antiAirDefenseDevices,
                             int planes,
                             int helicopters,
                             int drones,
                             int cruiseMissiles,
                             int shipsOrBoats,
                             int carsAndTankers,
                             int specialEquipment,
                             int personnel,
                             int id
                             ) {
    private static final String errorMassage = "Loses can't be negative";

    public LosesStatistic {
        checkFieldValue(tanks, errorMassage);
        checkFieldValue(armouredFightingVehicles, errorMassage);
        checkFieldValue(multipleRocketLaunchers, errorMassage);
        checkFieldValue(antiAirDefenseDevices, errorMassage);
        checkFieldValue(planes, errorMassage);
        checkFieldValue(helicopters, errorMassage);
        checkFieldValue(drones, errorMassage);
        checkFieldValue(cruiseMissiles, errorMassage);
        checkFieldValue(shipsOrBoats, errorMassage);
        checkFieldValue(carsAndTankers, errorMassage);
        checkFieldValue(specialEquipment, errorMassage);
        checkFieldValue(personnel, errorMassage);
        checkFieldValue(id, "ID can't be negative");

    }

    private void checkFieldValue(int integer, String errorMassage) {
        if (isNotPositiveInteger(integer)) {
            throw new IllegalArgumentException(errorMassage);
        }
    }

    private boolean isNotPositiveInteger(int integer) {
        return integer < 0;
    }

    public int id() {
        return id;
    }
}
