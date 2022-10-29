package edu.geekhub.homework;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ApplicationStarter {
    public static void main(String[] args) {
        double calculated = calculate(getNumberFromUser());
        System.out.println(calculated);
    }

    private static int getNumberFromUser() {
        Scanner scanner = new Scanner(System.in);
        Pattern integerLiteralInTheEndOfLine = Pattern.compile("\\d+$");

        System.out.print("Please enter a number: ");
        while (!scanner.hasNext(integerLiteralInTheEndOfLine)) {
            System.out.println("You did not enter a number.");
            System.out.print("Please repeat the input: ");
            scanner.nextLine();
        }
        return Integer.parseInt(scanner.next(integerLiteralInTheEndOfLine));
    }

    private static double calculate(int n) {
        if (n % 2 == 0) {
            return findAreaOfSquare(n);
        } else if (n % 3 == 0) {
            return findAreaOfCircle(n);
        } else {
            return findAreaOfTriangle(n);
        }
    }

    private static double findAreaOfSquare(double side) {
        return side * side;
    }

    private static double findAreaOfCircle(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    private static double findAreaOfTriangle(double side) {
        return (Math.sqrt(3) / 4) * Math.pow(side, 2);
    }
}
