package edu.geekhub.homework;

import edu.geekhub.homework.geometrical.figures.*;
import edu.geekhub.homework.user.input.TakerInputFromUser;


public class ApplicationStarter {
    public static void main(String[] args) {
        System.out.println("Select the first form:");
        Shape firstShape = createShape();
        System.out.println("Select the second form:");
        ShapeImp secondShape = createShape();

        System.out.println();
        firstShape.printInfo();
        System.out.println();
        secondShape.printInfo();
        System.out.println();
        firstShape.compare(secondShape);
    }

    private static ShapeImp createShape() {
        switch (getTypeOfFigureFromUser()) {
            case CIRCLE: {
                return new Circle();
            }
            case TRIANGLE: {
                return new Triangle();
            }
            case SQUARE: {
                return new Square();
            }
            case RECTANGLE: {
                return new Rectangle();
            }
            default: {
                System.out.println("You select wrong type of figure.");
                System.exit(0);
            }
        }

        return null;
    }

    private static ShapeEnum getTypeOfFigureFromUser() {
        System.out.println("Select shape type: ");

        for (ShapeEnum shape : ShapeEnum.values()) {
            System.out.println(shape.ordinal() + "." + shape);
        }

        return ShapeEnum.values()[TakerInputFromUser.getShapeEnumOrdinalFromUser()];
    }
}
