package edu.geekhub.homework.task3;

public class ApplicationStarter {

    public static void main(String[] args) {
        SequenceCalculator sequenceCalculator = new SequenceCalculator();
        try {
            System.out.println(sequenceCalculator.calculate(
                "1adasd,adad2,-3",
                ArithmeticOperation.MULTIPLICATION)
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
