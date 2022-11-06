package edu.geekhub.homework.geometrical.figures;

public interface Shape {

    static final String NAME = "Shape";
    default double calculateArea() {
        return 0.0;
    }

    default double calculatePerimeter() {
        return 0.0;
    }

    default void printInfo() {
        System.out.println("Shape without area and perimeter.");
    }

    default void compare(Shape anotherFigure) {
        if (this.calculateArea() > anotherFigure.calculateArea()) {
            System.out.println("Area of " + Shape.NAME + "(" + this.hashCode() + ")"
                + " is bigger then " + Shape.NAME + "(" + anotherFigure.hashCode() + ")");
        } else if (this.calculateArea() < anotherFigure.calculateArea()) {
            System.out.println("Area of " + Shape.NAME + "(" + anotherFigure.hashCode() + ")"
                + " is bigger then " + Shape.NAME + "(" + this.hashCode() + ")");
        } else {
            System.out.println("Area of " + Shape.NAME + "(" + anotherFigure.hashCode() + ")"
                + " is equal to " + Shape.NAME + "(" + this.hashCode() + ")");
        }
    }
}
