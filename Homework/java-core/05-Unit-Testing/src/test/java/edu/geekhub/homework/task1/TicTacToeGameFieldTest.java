package edu.geekhub.homework.task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TicTacToeGameFieldTest {

    private static final int FIELD_INPUT_LENGTH = 9;
    private static final char X_CHAR = 'X';
    private static final char O_CHAR = 'O';
    private static final char EMPTY_CELL_CHAR = ' ';

    private final TicTacToeGameField field = new TicTacToeGameField();

    @Tag("validation")
    @Test
    void failed_generate_field_null_input() {
        assertThrows(
            NullPointerException.class,
            () -> field.generateField(null)
        );
    }

    @Tag("validation")
    @Test
    void failed_generate_field_blank_input() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.generateField("")
        );

        assertEquals(
            "Cant process empty field state",
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_generate_field_longer_input() {
        String fieldInput = "XOX   OXO ";

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.generateField(fieldInput)
        );

        assertEquals(
            "Field length: " + fieldInput.length()
                + " is not equal allowed length: " + FIELD_INPUT_LENGTH,
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_generate_field_random_input() {
        String fieldInput = "some text";
        String playersTurns = "s";

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.generateField(fieldInput)
        );

        assertEquals(
            "Player turn: " + playersTurns
                + " can be only '" + X_CHAR + "' or '" + O_CHAR + "' or '" + EMPTY_CELL_CHAR + "'",
            thrown.getMessage()
        );
    }

    @Tag("correct")
    @Test
    void generate_field_correct_input() {
        String fieldInput = "XOX   OXO";
        String expectedGameField = new StringBuilder("+-----+")
            .append(System.lineSeparator())
            .append("|X|O|X|")
            .append(System.lineSeparator())
            .append("| | | |")
            .append(System.lineSeparator())
            .append("|O|X|O|")
            .append(System.lineSeparator())
            .append("+-----+")
            .toString();

        String actualGameField = field.generateField(fieldInput);

        assertEquals(expectedGameField, actualGameField);
    }

    @Tag("validation")
    @Test
    void failed_save_state_null_input() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.saveFieldState(null)
        );

        assertEquals(
            "Cant process empty field state",
            thrown.getMessage()
        );
    }

    @Tag("validation")
    @Test
    void failed_save_state_longer_input() {
        String fieldInput = new StringBuilder(" +-----+")
            .append(System.lineSeparator())
            .append("|X|O|X|")
            .append(System.lineSeparator())
            .append("| | | |")
            .append(System.lineSeparator())
            .append("|O|X|O|")
            .append(System.lineSeparator())
            .append("+-----+")
            .toString();

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.saveFieldState(fieldInput)
        );

        assertEquals(
            "Field length: " + (FIELD_INPUT_LENGTH + 1)
                + " is not equal allowed length: " + FIELD_INPUT_LENGTH,
            thrown.getMessage()
        );
    }

    @Test
    void failed_save_state_random_input() {
        String fieldInput = "some text";
        String playersTurns = "s";

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> field.saveFieldState(fieldInput)
        );

        assertEquals(
            "Player turn: " + playersTurns
                + " can be only '" + X_CHAR + "' or '" + O_CHAR + "' or '" + EMPTY_CELL_CHAR + "'",
            thrown.getMessage()
        );
    }

    @Tag("correct")
    @Test
    void save_state_correct_input() {
        String expectedGameField = "XOX   OXO";
        String fieldInput = new StringBuilder("+-----+")
            .append(System.lineSeparator())
            .append("|X|O|X|")
            .append(System.lineSeparator())
            .append("| | | |")
            .append(System.lineSeparator())
            .append("|O|X|O|")
            .append(System.lineSeparator())
            .append("+-----+")
            .toString();

        String actualGameField = field.saveFieldState(fieldInput);

        assertEquals(expectedGameField, actualGameField);
    }
}
