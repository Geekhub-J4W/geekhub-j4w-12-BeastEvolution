package edu.geekhub.homework.playlist;

import edu.geekhub.homework.playlist.util.txt.TxtConvertorImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class TxtConvertorImpTest {
    TxtConvertorImp sut;

    @BeforeEach
    void setUp() {
        sut = new TxtConvertorImp();
    }

    @Test
    @Tag("Correct")
    void Conversion_of_file_to_string() throws IOException {
        File inputFile = new File(
            this
                .getClass()
                .getResource("/txt-convertor/textFile.txt")
                .getPath()
        );

        String result = sut.convertToString(inputFile);

        assertThat(result).isEqualTo("some text");
    }
}
