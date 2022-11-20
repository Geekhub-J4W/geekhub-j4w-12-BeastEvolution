package edu.geekhub.homework.task2;

import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class LosesInWarParser {

    private static final String STATISTIC_WORKPIECE = """
                Танки —
                ББМ —
                Гармати —
                РСЗВ —
                Засоби ППО —
                Літаки —
                Гелікоптери —
                БПЛА —
                Крилаті ракети —
                Кораблі (катери) —
                Автомобілі та автоцистерни —
                Спеціальна техніка —
                Особовий склад —
                """;

    LosesStatistic parseLosesStatistic(String input) {
        validateInput(input);
        if (inputIsInputNotLosesStatistic(input)) {
            return LosesStatistic.empty();
        }

        String[] lines = parseLine(input);

        return LosesStatistic.newStatistic()
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
    }

    static String[] parseLine(String text) {
        return text.split("\n");
    }

    static int getAmountOfEquipment(String line) {
        int amountOfEquipment = 0;
        Scanner scanner = new Scanner(line);

        boolean hasNotNextInt = true;
        while (hasNotNextInt) {
            if (scanner.hasNextInt()) {
                hasNotNextInt = false;
                amountOfEquipment = scanner.nextInt();
            }
            try {
                scanner.next();
            } catch (NoSuchElementException e) {
                break;
            }
        }

        return amountOfEquipment;
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

    private boolean inputIsInputNotLosesStatistic(String fieldState) {
        String[] actualLines = parseLine(fieldState);
        String[] expectedLines = parseLine(STATISTIC_WORKPIECE);

        for (int i = 0; i < actualLines.length; i++) {
            boolean isNotStartWithExpectedLines = !(actualLines[i].startsWith(expectedLines[i]));
            if (isNotStartWithExpectedLines) {
                return true;
            }
        }

        return false;
    }

}
