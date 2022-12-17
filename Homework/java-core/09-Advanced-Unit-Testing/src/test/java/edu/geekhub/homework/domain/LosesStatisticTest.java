package edu.geekhub.homework.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class LosesStatisticTest {

    LosesStatistic losesStatistic;

    @Test
    void create_statistic() {
        String expectedStringRepresentation = "LosesStatistic[tanks=2, armouredFightingVehicles=1, cannons=1, multipleRocketLaunchers=1, antiAirDefenseDevices=9, planes=5, helicopters=9, drones=3, cruiseMissiles=5, shipsOrBoats=2, carsAndTankers=6, specialEquipment=3, personnel=3, id=8]";
        String actualStringRepresentation;

        losesStatistic = new LosesStatistic(2,
            1,
            1,1,
            9,
            5,
            9,
            3,
            5,
            2,
            6,
            3,
            3,
            8
        );

        actualStringRepresentation = losesStatistic.toString();

            Assertions.assertThat(actualStringRepresentation).isEqualTo(expectedStringRepresentation);
    }

    @Test
    void fail_create_incorrect_statistic() {
        String expectedStringRepresentation = "LosesStatistic[tanks=2, armouredFightingVehicles=1, cannons=1, multipleRocketLaunchers=1, antiAirDefenseDevices=9, planes=5, helicopters=9, drones=3, cruiseMissiles=5, shipsOrBoats=2, carsAndTankers=6, specialEquipment=3, personnel=3, id=8]";
        String actualStringRepresentation;

        Assertions.assertThatThrownBy(() -> losesStatistic = new LosesStatistic(-1,
            1,
            1,1,
            9,
            5,
            9,
            3,
            5,
            2,
            6,
            3,
            3,
            8
        )).isInstanceOf(IllegalArgumentException.class).hasMessage("Loses can't be negative");
    }

    @Test
    void fail_create_statistic_with_incorrect_id() {
        String expectedStringRepresentation = "LosesStatistic[tanks=2, armouredFightingVehicles=1, cannons=1, multipleRocketLaunchers=1, antiAirDefenseDevices=9, planes=5, helicopters=9, drones=3, cruiseMissiles=5, shipsOrBoats=2, carsAndTankers=6, specialEquipment=3, personnel=3, id=8]";
        String actualStringRepresentation;

        Assertions.assertThatThrownBy(() -> losesStatistic = new LosesStatistic(1,
            1,
            1,1,
            9,
            5,
            9,
            3,
            5,
            2,
            6,
            3,
            3,
            -8
        )).isInstanceOf(IllegalArgumentException.class).hasMessage("ID can't be negative");
    }
}
