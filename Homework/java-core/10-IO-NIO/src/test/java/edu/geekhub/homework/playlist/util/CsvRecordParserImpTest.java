package edu.geekhub.homework.playlist.util;

import edu.geekhub.homework.playlist.util.csv.CsvRecordParserImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CsvRecordParserImpTest {
    CsvRecordParserImp sut;

    @BeforeEach
    void setUp() {
        sut = new CsvRecordParserImp();
    }

    @Test
    @Tag("Correct")
    void Parse_CSV_record() {
        String csvRecord = "\"a\",\"b\",\"c\"";
        String[] recordFields = {"a", "b", "c"};

        String[] result = sut.parseRecord(csvRecord);

        assertThat(result)
            .isEqualTo(recordFields);
    }

    @Test
    @Tag("Null")
    void Parse_null_is_invalid() {
        assertThatThrownBy(() -> sut.parseRecord(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("CSV record can't be null");
    }

    @Test
    @Tag("Empty")
    void Parse_empty_record_is_invalid() {
        assertThatThrownBy(() -> sut.parseRecord(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Not a CSV record");
    }


    @ParameterizedTest
    @ValueSource(strings = {
        "abc",
        "a,b,c,"
    })
//    @Tag("Incorrect")
    void Parse_incorrect_records(String csvRecord) {
        assertThatThrownBy(() -> sut.parseRecord(csvRecord))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Not a CSV record");
    }
}
