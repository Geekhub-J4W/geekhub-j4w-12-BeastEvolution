package edu.geekhub.homework.user.input;

import edu.geekhub.homework.geometrical.figures.ShapeEnum;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TakerInputFromUser {

    private TakerInputFromUser() {}

    private static final String TRY_AGAIN = "Try again: ";

    public static int getPositiveIntegerFromUser(String massageToUser) {
        String input = getInputFormUser(massageToUser);

        if (!checkIsInputPositiveNumber(input)) {
            System.out.println("You entered non-positive number. ");

            return getPositiveIntegerFromUser(massageToUser);
        }
        if (!checkIsInputInteger(input)) {
            System.out.println("You entered a number outside the range of values.");

            return getPositiveIntegerFromUser(massageToUser);
        }

        return Integer.parseInt(input);
    }

    public static String getInputFormUser(String massageToUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(massageToUser);

        return scanner.nextLine();
    }

    public static String getInputPositiveNumberFormUser(String input) {
        while (!checkIsInputPositiveNumber(input)) {
            System.out.println("You entered non-positive number. ");
            input = getInputFormUser(TRY_AGAIN);
        }

        return input;
    }

    public static boolean checkIsInputPositiveNumber(String userInput) {
        Scanner scanner = new Scanner(userInput);
        Pattern integerLiteralInTheEndOfLine = Pattern.compile("\\d+$");

        return scanner.hasNext(integerLiteralInTheEndOfLine);
    }

    public static int getIntegerFormUser(String massageToUser) {
        String input = getInputFormUser(massageToUser);

        while (!checkIsInputInteger(input)) {
            System.out.println("You entered a number outside the range of values.");
            input = getInputFormUser(TRY_AGAIN);
        }

        return Integer.parseInt(input);
    }

    public static boolean checkIsInputInteger(String userInput) {
        Scanner scanner = new Scanner(userInput);

        return scanner.hasNextInt();
    }

    public static int getShapeEnumOrdinalFromUser() {
        String userInput = getInputFormUser("");
        while (!validateShapeEnumOrdinalFromUser(userInput)) {
            System.out.println("You select wrong type.");
            userInput = getInputFormUser(TRY_AGAIN);
        }

        return Integer.parseInt(userInput);
    }

    private static boolean validateShapeEnumOrdinalFromUser(String userInput) {
        if (!checkIsUserInputIsNumber(userInput)) {
            return false;
        }
        if (!checkIsInputInteger(userInput)) {
            return false;
        }

        if (!checkIsUserInputInRangeOfShapeEnumOrdinals(Integer.parseInt(userInput))) {
            return false;
        }

        return true;
    }

    private static boolean checkIsUserInputIsNumber(String userInput) {
        Scanner scanner = new Scanner(userInput);
        Pattern integerLiteralInTheEndOfLine = Pattern.compile("-?\\d+$");

        return scanner.hasNext(integerLiteralInTheEndOfLine);
    }

    private static boolean checkIsUserInputInRangeOfShapeEnumOrdinals(int userInput) {

        return (userInput >= ShapeEnum.getOrdinalOfFirstEnumerationConstant()
            && userInput <= ShapeEnum.getOrdinalOfLastEnumerationConstant());
    }
}
