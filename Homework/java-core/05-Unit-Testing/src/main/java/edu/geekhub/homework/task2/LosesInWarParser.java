package edu.geekhub.homework.task2;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;
import static java.util.Objects.requireNonNull;

public class LosesInWarParser {

     LosesStatistic parseLosesStatistic(String input) {
         validateInput(input);

         String[] lines = parseLine(input);

         var statistic = LosesStatistic.newStatistic()
             .withTanks(getAmountOfEquipment(lines[0]))
             .withArmouredFightingVehicles(getAmountOfEquipment(lines[1]))
             .withCannons(getAmountOfEquipment(lines[2]))
             .withMultipleRocketLaunchers(getAmountOfEquipment(lines[3]))
             .withAntiAirDefenseDevices(getAmountOfEquipment(lines[4]))
             .withPlanes(getAmountOfEquipment(lines[5]))
             .withHelicopters(getAmountOfEquipment(lines[6]))
             .withDrones(getAmountOfEquipment(lines[7]))
             .withCruiseMissiles(getAmountOfEquipment(lines[8]))
             .withShipsOrBoats(getAmountOfEquipment(lines[9]))
             .withCarsAndTankers(getAmountOfEquipment(lines[10]))
             .withSpecialEquipment(getAmountOfEquipment(lines[11]))
             .withPersonnel(getAmountOfEquipment(lines[12]))
             .build();

         return statistic;
    }

    static String[] parseLine(String text) {
        return text.split("\n");
    }

    static int getAmountOfEquipment(String line) {
        int amountOFEquipment = 0;
        Scanner scanner = new Scanner(line);

        boolean hasNotNextInt = true;
        while (hasNotNextInt) {
            if(scanner.hasNextInt()) {
                hasNotNextInt = false;
                amountOFEquipment = scanner.nextInt();
            }
            try {
                scanner.next();
            } catch (NoSuchElementException e) {
                break;
            }
        }

        return amountOFEquipment;
    }

    private void validateInput(String input) {
        checkInputIsPresent(input);
    }

    private void checkInputIsPresent(String fieldState) {
        requireNonNull(fieldState);

        if (fieldState.isBlank()) {
            throw new IllegalArgumentException(
                "Cant parse empty statistic"
            );
        }
    }

}
