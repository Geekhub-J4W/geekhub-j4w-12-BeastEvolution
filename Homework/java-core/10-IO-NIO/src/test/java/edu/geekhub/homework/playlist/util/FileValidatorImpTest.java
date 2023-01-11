package edu.geekhub.homework.playlist.util;

import edu.geekhub.homework.playlist.util.file.FileValidatorImp;
import edu.geekhub.homework.playlist.util.file.interfaces.FileValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileValidatorImpTest {

    FileValidator sut;

    @BeforeEach
    void setUp() {
        sut = new FileValidatorImp();
    }

    @Test
    void Validate_file() {
        File file = new File(this.getClass().getResource("/file_validator/textFile.txt").getPath());

        File result = sut.validateFile(file, ".txt");

        assertThat(result)
            .isEqualTo(file);
    }

    @Test
    @Tag("null")
    void Validate_null_file() {
        assertThatThrownBy(() -> sut.validateFile(null, ".txt"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("File can't be null");
    }

    @Test
    @Tag("Incorrect argument")
    void Validate_non_existent_file() {
        File file = new File("");

        assertThatThrownBy(() -> sut.validateFile(file, ".txt"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("There is no file for this inputFile \"%s\"", file));
    }

    @Test
    @Tag("Incorrect argument")
    void Validate_file_with_unsuitable_extension() {
        File file = new File(this.getClass().getResource("/file_validator/textFile.txt").getPath());
        String extension = ".csv";

        assertThatThrownBy(() -> sut.validateFile(file, extension))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(
                String.format("File for this \"%s\" inputFile not a \"%s\" format",
                    file,
                    extension)));
    }
}
