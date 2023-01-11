package edu.geekhub.homework.playlist.util;

import edu.geekhub.homework.playlist.util.file.FileExtensionValidatorImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileExtensionValidatorImpTest {

    FileExtensionValidatorImp sut;

    @BeforeEach
    void setUp() {
        sut = new FileExtensionValidatorImp();
    }

    @Test
    void Validate_extension() {
        String argument = ".txt";

        String result = sut.validateExtension(argument);

        Assertions.assertThat(result)
            .isEqualTo(argument);
    }

    @Test
    @Tag("null")
    void Validate_null_extension() {
        assertThatThrownBy(() -> sut.validateExtension(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Extension can't be null");
    }

    @Test
    @Tag("empty")
    void Validate_empty_extension() {
        assertThatThrownBy(() -> sut.validateExtension(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Incorrect extension");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        ".1",
        "txt",
        ".тхт",
        ".t_x_t"
    })
    @Tag("Incorrect argument")
    void Validate_incorrect_extension(String argument) {
        assertThatThrownBy(() -> sut.validateExtension(argument))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Incorrect extension");
    }
}
