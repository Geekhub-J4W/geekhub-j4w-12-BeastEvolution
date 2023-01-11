package edu.geekhub.homework.playlist;

import edu.geekhub.homework.playlist.util.chars.CharValidatorImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CharValidatorImpTest {
    CharValidatorImp charValidatorImp;

    @BeforeEach
    void setUp() {
        File allowedSymbolsFile = new File(this.getClass().getResource("/char_validator/allowed_symbols.csv").getPath());
        charValidatorImp = new CharValidatorImp(allowedSymbolsFile);
    }

    @ParameterizedTest
    @ValueSource(chars = {
        ' ',
        '0'
    })
    void Validate_allowed_characters(char checkedCharacter) {
        boolean result = charValidatorImp.isInvalidChar(checkedCharacter);

        assertThat(result)
            .isFalse();

    }

    @Test
    void Validate_not_allowed_path_item_character() {
        boolean result = charValidatorImp.isInvalidChar('a');

        assertThat(result)
            .isTrue();

    }

    @Test
    void Invalid_to_create_instance_if_all_file_fields_not_a_characters() {
        File allowedSymbolsFile = new File(this.getClass().getResource("/char_validator/not_char_values.csv").getPath());

        assertThatThrownBy(() -> new CharValidatorImp(allowedSymbolsFile))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("All value in csv file must be a char");
    }
}
