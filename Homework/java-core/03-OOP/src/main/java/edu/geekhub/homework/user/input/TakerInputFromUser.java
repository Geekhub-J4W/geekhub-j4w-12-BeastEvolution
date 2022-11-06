package edu.geekhub.homework.user.input;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TakerInputFromUser {

    private TakerInputFromUser() {}

    public static double getPositiveDoubleFromUser(String massageToUser) {
        String input = getInputFormUser(massageToUser);

        boolean notPositiveDouble = true;
        while (notPositiveDouble) {
            if (!checkIsInputPositiveRealNumber(input)) {
                System.out.println("You entered non-positive real number. ");

                input = getInputFormUser(massageToUser);
                continue;
            }

            if (!checkIsInputDouble(input)) {
                System.out.println("You entered a number outside the range of double values.");

                input = getInputFormUser(massageToUser);
                continue;
            }

            notPositiveDouble = false;
        }

        return Double.parseDouble(input);
    }

    public static String getInputFormUser(String massageToUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(massageToUser);

        return scanner.nextLine();
    }

    private static boolean checkIsInputPositiveRealNumber(String userInput) {
        Scanner scanner = new Scanner(userInput);
        Pattern integerLiteralInTheEndOfLine = Pattern.compile("\\d+(\\.\\d+)?$");

        return scanner.hasNext(integerLiteralInTheEndOfLine);
    }

    private static boolean checkIsInputDouble(String userInput) {
        Scanner scanner = new Scanner(userInput);

        return scanner.hasNextDouble();
    }

    public static boolean checkIsInputInt(String userInput) {
        Scanner scanner = new Scanner(userInput);

        return scanner.hasNextInt();
    }
}
