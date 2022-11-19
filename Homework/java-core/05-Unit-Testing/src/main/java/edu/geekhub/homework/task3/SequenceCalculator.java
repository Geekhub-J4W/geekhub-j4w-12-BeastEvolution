package edu.geekhub.homework.task3;

import edu.geekhub.homework.task3.exceptions.CalculationException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Arithmetic operation calculator for a sequence of values<br/>
 * As real world example try to input: 1*2*3 in a Chrome address bar
 */
public class SequenceCalculator {

    private final IntegerParser parser = new IntegerParser();
    private static final String RESULT_OVERFLOWS_AN_INT = "Result overflows an int";

    private class IntegerParser {

        public int[] parseFromCsv(String csv) {
            String[] separatedValues = separateValues(csv);
            int[] integers = new int[separatedValues.length];

            for (int i = 0; i < separatedValues.length; i++) {
                try {
                    integers[i] = getInteger(separatedValues[i]);
                } catch (IllegalArgumentException e) {
                    return new int[0];
                }
            }

            return integers;
        }

        private String[] separateValues(String text) {
            return text.split(",");
        }

        private int getInteger(String line) {
            int beginOfInteger = findBeginOfInteger(line);

            String partOfLineWithInteger = line.substring(beginOfInteger);

            int endOfInteger = findEndOfInteger(partOfLineWithInteger);
            String integer = partOfLineWithInteger.substring(0, endOfInteger);

            return Integer.parseInt(integer);
        }

        private int findBeginOfInteger(String line) {
            char[] characters = line.toCharArray();

            for (int i = 0; i < characters.length; i++) {
                if ((characters[i] >= '0' && characters[i] <= '9')
                    || (characters[i] == '-'
                        && (characters[i + 1] >= '0' && characters[i + 1] <= '9'))) {
                    return i;
                }
            }

            throw new IllegalArgumentException();
        }

        private int findEndOfInteger(String line) {
            int endOfInteger = line.length();
            char[] characters = line.toCharArray();

            boolean isNotDigit;
            boolean isNotMinus;
            for (int i = 0; i < characters.length; i++) {
                isNotDigit = !(characters[i] >= '0' && characters[i] <= '9');
                isNotMinus = (characters[i] != '-');
                if (isNotDigit && isNotMinus) {
                    endOfInteger = i;
                    break;
                }
            }

            return endOfInteger;
        }
    }

    private final CommaSeparatedOperandsValidator validator = new CommaSeparatedOperandsValidator();

    private class CommaSeparatedOperandsValidator {

        private final IntegerParser parser = new IntegerParser();

        public void validate(String input) {
            checkInputIsPresent(input);
            checkInputIsCommaSeparatedOperands(input);
        }

        private void checkInputIsPresent(String input) {
            if (Objects.isNull(input)) {
                throw new NullPointerException("Input is null");
            }

            if (input.isBlank()) {
                throw new IllegalArgumentException("Input is blank");
            }
        }

        private void checkInputIsCommaSeparatedOperands(String input) {
            if (parser.parseFromCsv(input).length == 0) {
                throw new IllegalArgumentException(
                    "Input has no operands"
                );
            }
        }
    }

    /**
     * Takes an input extract valid integers and calculate them using selected operation.<br/>
     *
     * Example of work:
     * <pre>
     *     input: 1, 2, 3, 4
     *     operation: '*'
     *     result: 1 * 2 * 3 * 4 = 24
     * </pre>
     *
     * @param input that contains a comma - ',' separated characters
     * @param operation {@link ArithmeticOperation} that should be applied to input numbers
     * @return result of calculation
     */
    int calculate(String input, ArithmeticOperation operation) {
        validator.validate(input);
        validate(operation);

        int[] operandsOfExpression = parser.parseFromCsv(input);

        return calculateIntegers(operandsOfExpression, operation);
    }

    private void validate(ArithmeticOperation operation) {
        if (Objects.isNull(operation)) {
            throw new NullPointerException("Operation is null");
        }
    }

    private int calculateIntegers(int[] integers, ArithmeticOperation operation) {
        if (integers.length == 1) {
            return integers[0];
        }
        integers[1] = performMathOperation(operation, integers[0], integers[1]);
        integers = Arrays.copyOfRange(integers, 1, integers.length);

        return calculateIntegers(integers, operation);
    }

    private int performMathOperation(ArithmeticOperation operation, int x, int y) {
        int operationResult;

        switch (operation) {
            case ADDITION: {
                try {
                    operationResult = Math.addExact(x, y);
                    break;
                } catch (ArithmeticException e) {
                    throw new CalculationException(RESULT_OVERFLOWS_AN_INT, e);
                }
            }
            case SUBTRACTION: {
                try {
                    operationResult = Math.subtractExact(x, y);
                    break;
                } catch (ArithmeticException e) {
                    throw new CalculationException(RESULT_OVERFLOWS_AN_INT, e);
                }
            }
            case MULTIPLICATION: {
                try {
                    operationResult = Math.multiplyExact(x, y);
                    break;
                } catch (ArithmeticException e) {
                    throw new CalculationException(RESULT_OVERFLOWS_AN_INT, e);
                }
            }
            case DIVISION: {
                try {
                    operationResult = x / y;
                    break;
                } catch (ArithmeticException e) {
                    throw new CalculationException("Can't divide by zero", e);
                }
            }
            default: {
                operationResult = 0;
            }
        }

        return operationResult;
    }
}
