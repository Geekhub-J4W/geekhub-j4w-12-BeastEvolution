package edu.geekhub.homework.playlist.util;

import edu.geekhub.homework.playlist.util.csv.CsvConvertorImp;
import edu.geekhub.homework.playlist.util.file.FileReaderImp;
import edu.geekhub.homework.playlist.util.file.interfaces.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CsvConvertorImpTest {
    CsvConvertorImp sut;

    @Mock
    FileReader fileReader;

    @BeforeEach
    void setUp() {
        sut = new CsvConvertorImp(new FileReaderImp());
    }

    @Test
    void Get_record_from_CSV_file() {
        File csvFile = new File(this.getClass().getResource("/csv_convertor/file.csv").getPath());
        String[] recordFields = {"a", "b", "c"};

        String[] result = sut.getRecordFields(csvFile, 0);
        assertThat(result)
            .isEqualTo(recordFields);
    }

    @ParameterizedTest
    @ValueSource(ints = {
        -1,
        1
    })
    void It_is_invalid_to_get_record_by_incorrect_index(int recordIndex) {
        File csvFile = new File(this.getClass().getResource("/csv_convertor/file.csv").getPath());

        assertThatThrownBy(() -> sut.getRecordFields(csvFile, recordIndex))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(
                "Record index \"%d\" out of bound of records array(0, %d)",
                recordIndex,
                1
            ));
    }

    @Test
    void It_invalid_to_get_record_fields_if_CSV_file_is_too_large() throws IOException {
        //arrange
        Mockito.when(fileReader.readString(ArgumentMatchers.any(Path.class)))
            .thenThrow(IOException.class);
        sut = new CsvConvertorImp(fileReader);

        File csvFile = new File(this.getClass().getResource("/csv_convertor/file.csv").getPath());

        //act, assert
        assertThatThrownBy(() -> sut.getRecordFields(csvFile, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("File is to large");
    }
}
