package edu.geekhub.homework.geometrical.figures;

import edu.geekhub.homework.user.input.TakerInputFromUser;

public abstract class ShapeImp implements Shape {

    private static final String AREA_OF = "Area of ";

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

        return selectColorType(TakerInputFromUser.getIntegerFormUser(""));
    }

    private Color selectColorType(int ordinal) {
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
        System.out.println("Perimeter of " + NAME + ": " + this.getPerimeter());
        System.out.println(AREA_OF + NAME + ": " + this.getArea());
    }

    public void compare(ShapeImp anotherFigure) {
        if (this.area > anotherFigure.area) {
            System.out.println(AREA_OF + ShapeImp.NAME + this.index + " is bigger then "
                + ShapeImp.NAME + anotherFigure.index);
        } else if (this.area < anotherFigure.area) {
            System.out.println(AREA_OF + ShapeImp.NAME + anotherFigure.index + " is bigger then "
                + ShapeImp.NAME + this.index);
        } else {
            System.out.println(AREA_OF + ShapeImp.NAME + anotherFigure.index + " is equal to "
                + ShapeImp.NAME + this.index);
        }
    }

    protected static int getCharacteristicOfFigureFromUser(String characteristicName) {

        return TakerInputFromUser.getPositiveIntegerFromUser("Enter " + characteristicName + ": ");
    }
}
