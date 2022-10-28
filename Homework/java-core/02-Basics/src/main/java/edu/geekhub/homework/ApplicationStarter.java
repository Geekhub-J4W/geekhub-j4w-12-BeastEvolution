package edu.geekhub.homework;

public class ApplicationStarter {
    public static void main(String[] args) {
        double calculated = calculate(12);
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
