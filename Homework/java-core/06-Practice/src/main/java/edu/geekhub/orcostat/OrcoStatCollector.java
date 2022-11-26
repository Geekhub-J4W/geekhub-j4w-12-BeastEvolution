package edu.geekhub.orcostat;

import edu.geekhub.orcostat.exeptions.IllegalOptionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrcoStatCollector {
    private static final String[] options = {
        "1. Add orc",
        "2. Add tank",
        "3. Show money damage",
        "4. Show Statistic",
        "5. Exit"
    };

    private static OrcoStatService orcoStatService = new OrcoStatService();

    private static final int firstMenuOption = 1;
    private static final int lastMenuOption = options.length;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            try {
                getUserChoice();
            } catch (IllegalOptionException e) {
                System.out.println("Sorry, "+ e.getMessage().toLowerCase());
                System.out.println("Try again");
            }
        }

    }
    private static void printMenu() {
        for (String option:
             options) {
            System.out.println(option);
        }
    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalOptionException(
                "You did not enter a number",
                e
                );
        }

        checkIsChoseLegalMenuOption(userChoice);

        return userChoice;
    }

    private static void checkIsChoseLegalMenuOption(int userChoice) {
        boolean isNotInRangeOfMenuOption =
            !(userChoice >= firstMenuOption && userChoice <= lastMenuOption);
        if (isNotInRangeOfMenuOption) {
            throw new IllegalOptionException(
                "You have selected a menu option that is not allowed"
            );
        }
    }

    private static void addOrc() {
        orcoStatService.addNegativelyAliveOrc();
    }
}
