package edu.geekhub.orcostat;

import edu.geekhub.orcostat.exceptions.IllegalOptionException;
import edu.geekhub.orcostat.menu.Menu;
import edu.geekhub.orcostat.model.Orc;
import edu.geekhub.orcostat.model.Tank;
import edu.geekhub.orcostat.model.TrivialCollection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationStarter {
    private static final OrcoStatService orcoStatService = new OrcoStatService();
    private static final OrcoStatPrinter orcoStatPrinter = new OrcoStatPrinter(orcoStatService);

    public static void main(String[] args) {
        Menu menu = new Menu();

        while (true) {
            menu.printCurrentOptions();

            try {
                runCommand(menu.moveThroughMenuNodes(getUserChoice()));
            } catch (IllegalOptionException e) {
                System.out.println("Sorry, " + e.getMessage().toLowerCase());
                System.out.println("Try again");
            }
        }

    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        int userChoice;
        try {
            userChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalOptionException(
                "You did not enter a number",
                e
                );
        }

        return userChoice;
    }

    private static void runCommand(String commandIndex) {
        switch (commandIndex) {
            case "1.1": {
                orcoStatService.addNegativelyAliveOrc(new Orc());
                break;
            }
            case "1.2": {
                orcoStatService.addNegativelyAliveOrc(new Orc(50_000));
                break;
            }
            case "2.1": {
                orcoStatService.addDestroyedTank(new Tank());
                break;
            }
            case "2.2": {
                TrivialCollection equipage = new TrivialCollection();
                for (int i = 0; i < 6; i++) {
                    equipage.add(new Orc());
                }

                orcoStatService.addDestroyedTank(new Tank(equipage));
                break;
            }
            case "4": {
                orcoStatPrinter.printMoneyDamage();
                break;
            }
            case "5": {
                orcoStatPrinter.printStatistic();
                break;
            }
            case "6": {
                System.exit(0);
                break;
            }
            default: {
            }
        }
    }
}
