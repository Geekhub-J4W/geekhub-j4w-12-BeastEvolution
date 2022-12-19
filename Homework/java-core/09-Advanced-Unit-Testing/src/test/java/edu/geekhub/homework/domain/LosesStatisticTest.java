package edu.geekhub.homework.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

            assertThat(actualStringRepresentation).isEqualTo(expectedStringRepresentation);
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

    @Test
    @Tag("Correct work")
    @Tag("getStatisticItems")
    void get_statistic_items() {
        LosesStatistic losesStatistic = new LosesStatistic(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14
        );

        List<StatisticItem> expectedStatisticItems = new ArrayList<>();
        expectedStatisticItems.add(new StatisticItem("tanks", 1));
        expectedStatisticItems.add(new StatisticItem("armouredFightingVehicles", 2));
        expectedStatisticItems.add(new StatisticItem("cannons", 3));
        expectedStatisticItems.add(new StatisticItem("multipleRocketLaunchers", 4));
        expectedStatisticItems.add(new StatisticItem("antiAirDefenseDevices", 5));
        expectedStatisticItems.add(new StatisticItem("planes", 6));
        expectedStatisticItems.add(new StatisticItem("helicopters", 7));
        expectedStatisticItems.add(new StatisticItem("drones", 8));
        expectedStatisticItems.add(new StatisticItem("cruiseMissiles", 9));
        expectedStatisticItems.add(new StatisticItem("shipsOrBoats", 10));
        expectedStatisticItems.add(new StatisticItem("carsAndTankers", 11));
        expectedStatisticItems.add(new StatisticItem("specialEquipment", 12));
        expectedStatisticItems.add(new StatisticItem("personnel", 13));
        expectedStatisticItems.add(new StatisticItem("id", 14));


        List<StatisticItem> actualStatisticItems = losesStatistic.getStatisticItems();

        assertThat(actualStatisticItems).isEqualTo(expectedStatisticItems);
    }
}
