package edu.geekhub.homework.task3;

import static org.junit.jupiter.api.Assertions.*;

import edu.geekhub.homework.task3.exceptions.CalculationException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SequenceCalculatorTest {

    private final SequenceCalculator calculator = new SequenceCalculator();

    @Tag("correct operands")
    @Test
    void add_correct_integers() {
        int expectedSummationResult = -10;

        int actualSummationResult = calculator.calculate(
            "3,-7,4,-10",
            ArithmeticOperation.ADDITION
        );

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("correct operands")
    @Test
    void subtract_correct_integers() {
        int expectedSummationResult = 6;

        int actualSummationResult = calculator.calculate("3,-7,4", ArithmeticOperation.SUBTRACTION);

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("correct operands")
    @Test
    void multiply_correct_integers() {
        int expectedSummationResult = -84;

        int actualSummationResult = calculator.calculate(
            "3,-7,4",
            ArithmeticOperation.MULTIPLICATION
        );

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("correct operands")
    @Test
    void divide_correct_integers() {
        int expectedSummationResult = -2;

        int actualSummationResult = calculator.calculate("27,-3,4", ArithmeticOperation.DIVISION);

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("unacceptable operands")
    @Test
    void failed_calculation_divide_by_zero() {
        CalculationException thrown = assertThrows(
            CalculationException.class,
            () -> calculator.calculate("2,0", ArithmeticOperation.DIVISION));

        assertEquals("Can't divide by zero", thrown.getMessage());
        assertEquals(ArithmeticException.class, thrown.getCause().getClass());
    }

    @Tag("unacceptable operands")
    @Test
    void failed_calculation_add_boundary_integers() {
        CalculationException thrown = assertThrows(
            CalculationException.class,
            () -> calculator.calculate(
                "2147483647,2147483647,2147483647",
                ArithmeticOperation.ADDITION
            )
        );

        assertEquals("Result overflows an int", thrown.getMessage());
        assertEquals(ArithmeticException.class, thrown.getCause().getClass());
    }

    @Tag("unacceptable operands")
    @Test
    void failed_calculation_subtract_boundary_integers() {
        CalculationException thrown = assertThrows(
            CalculationException.class,
            () -> calculator.calculate(
                "2147483647,-2147483647,-2147483647",
                ArithmeticOperation.SUBTRACTION
            )
        );

        assertEquals("Result overflows an int", thrown.getMessage());
        assertEquals(ArithmeticException.class, thrown.getCause().getClass());
    }

    @Tag("unacceptable operands")
    @Test
    void failed_calculation_multiply_boundary_integers() {
        CalculationException thrown = assertThrows(
            CalculationException.class,
            () -> calculator.calculate(
                "2147483647,-2147483647,-2147483647",
                ArithmeticOperation.MULTIPLICATION
            )
        );

        assertEquals("Result overflows an int", thrown.getMessage());
        assertEquals(ArithmeticException.class, thrown.getCause().getClass());
    }

    @Tag("parsing")
    @Test
    void calculate_with_integer_around_spaces() {
        int expectedSummationResult = -10;

        int actualSummationResult = calculator.calculate(
            " 3,-7 , 4 ,   -10",
            ArithmeticOperation.ADDITION
        );

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("parsing")
    @Test
    void calculate_with_integer_around_text() {
        int expectedSummationResult = 6;

        int actualSummationResult = calculator.calculate(
            "3#$, &^%-7   ,!!- 4@@",
            ArithmeticOperation.SUBTRACTION
        );

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("number of values")
    @Test
    void calculate_odd_integers() {
        int expectedSummationResult = 55;

        int actualSummationResult = calculator.calculate(
            "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10",
            ArithmeticOperation.ADDITION);

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("number of values")
    @Test
    void calculate_even_integers() {
        int expectedSummationResult = 55;

        int actualSummationResult = calculator.calculate(
            "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10",
            ArithmeticOperation.ADDITION);

        assertEquals(expectedSummationResult, actualSummationResult);
    }

    @Tag("validation")
    @Test
    void failed_calculation_null_input() {
        NullPointerException thrown = assertThrows(
            NullPointerException.class,
            () -> calculator.calculate(null, ArithmeticOperation.ADDITION)
        );

        assertEquals(
            "Input is null",
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_calculation_blank_input() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.calculate("     ", ArithmeticOperation.ADDITION)
        );

        assertEquals(
            "Input is blank",
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_calculation_random_input() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.calculate("some text", ArithmeticOperation.ADDITION)
        );

        assertEquals(
            "Input has no operands",
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_calculation_null_operation() {
        NullPointerException thrown = assertThrows(
            NullPointerException.class,
            () -> calculator.calculate("1,2,3", null)
        );

        assertEquals(
            "Operation is null",
            thrown.getMessage()
        );
    }
}
