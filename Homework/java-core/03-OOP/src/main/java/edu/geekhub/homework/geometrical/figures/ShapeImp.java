package edu.geekhub.homework.geometrical.figures;

import edu.geekhub.homework.characteristics.Color;
import edu.geekhub.homework.user.input.TakerInputFromUser;
import java.util.Scanner;

public abstract class ShapeImp implements Shape {

    private static final String AREA_OF = "Area of ";
    private static final String PERIMETER_OF = "Perimeter of ";
    private static final String IS_BIGGER_THEN = " is bigger then ";

    public static final String NAME = "Shape";
    private final int index = this.hashCode();
    private final Color color;
    private double area;
    private double perimeter;

    ShapeImp() {
        color = getColorFromUser();
    }

    public String getName() {
        return NAME;
    }

    private Color getColorFromUser() {
        System.out.println("Select color of figure:");

        for (Color color : Color.values()) {
            System.out.println(color.ordinal() + "." + color);
        }

        Scanner scanner = new Scanner(System.in);
        return selectColorType(scanner.next());
    }

    private Color selectColorType(String userInput) {
        if (!TakerInputFromUser.checkIsInputInt(userInput)) {
            return Color.BLACK;
        }
        int ordinal = Integer.parseInt(userInput);

        boolean isUserInputInRangeEnumerationConstant =
            (ordinal >= Color.getOrdinalOfFirstEnumerationConstant()
                && ordinal <= Color.getOrdinalOfLastEnumerationConstant());
        if (!isUserInputInRangeEnumerationConstant) {
            return Color.BLACK;
        }

        return Color.values()[ordinal];
    }

    public int getIndex() {
        return index;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }

    protected void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    protected void setArea(double area) {
        this.area = area;
    }

    @Override
    public void printInfo() {
        printName();
        printCharacteristics();
    }

    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }

    protected void printCharacteristics() {
        System.out.println("Index: " + this.getIndex());
        System.out.println("Shape color: " + color.toString());
        System.out.println(PERIMETER_OF + NAME + ": " + this.getPerimeter());
        System.out.println(AREA_OF + NAME + ": " + this.getArea());
    }

    @Override
    public void compare(Shape anotherFigure) {
        compareShapeArea((ShapeImp) anotherFigure);
        System.out.println();
        compareShapePerimeter((ShapeImp) anotherFigure);
    }

    private void compareShapeArea(ShapeImp anotherFigure) {
        if (this.area > anotherFigure.area) {
            System.out.println(AREA_OF + ShapeImp.NAME + "(" + this.index + ")"
                + IS_BIGGER_THEN + ShapeImp.NAME + "(" + anotherFigure.index + ")");
        } else if (this.area < anotherFigure.area) {
            System.out.println(AREA_OF + ShapeImp.NAME + "(" + anotherFigure.index + ")"
                + IS_BIGGER_THEN + ShapeImp.NAME + "(" + this.index + ")");
        } else {
            System.out.println(AREA_OF + ShapeImp.NAME + "(" + anotherFigure.index + ")"
                + " is equal to " + ShapeImp.NAME + "(" + this.index + ")");
        }
    }

    private void compareShapePerimeter(ShapeImp anotherFigure) {
        if (this.perimeter > anotherFigure.perimeter) {
            System.out.println(PERIMETER_OF + ShapeImp.NAME + "(" + this.index + ")"
                + IS_BIGGER_THEN + ShapeImp.NAME + "(" + anotherFigure.index + ")");
        } else if (this.perimeter < anotherFigure.perimeter) {
            System.out.println(PERIMETER_OF + ShapeImp.NAME + "(" + anotherFigure.index + ")"
                + IS_BIGGER_THEN + ShapeImp.NAME + "(" + this.index + ")");
        } else {
            System.out.println(PERIMETER_OF + ShapeImp.NAME + "(" + anotherFigure.index + ")"
                + " is equal to " + ShapeImp.NAME + "(" + this.index + ")");
        }
    }

    protected static double getCharacteristicOfFigureFromUser(String characteristicName) {

        return TakerInputFromUser.getPositiveDoubleFromUser("Enter " + characteristicName + ": ");
    }
}
