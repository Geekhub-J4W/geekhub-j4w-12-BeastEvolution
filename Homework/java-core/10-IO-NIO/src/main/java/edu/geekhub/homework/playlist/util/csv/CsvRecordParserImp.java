package edu.geekhub.homework.playlist.util.csv;

import edu.geekhub.homework.playlist.util.csv.interfaces.CsvRecordParser;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class CsvRecordParserImp implements CsvRecordParser {
    private static final String FIELD_SEPARATOR = ",";

    @Override
    public String[] parseRecord(String input) {
        String csvRecord = validateRecord(input);
        return Arrays.stream(csvRecord.split(FIELD_SEPARATOR))
            .map(field -> field.replace("\"", ""))
            .toArray(String[]::new);
    }

    private String validateRecord(String csvRecord) {
        if (Objects.isNull(csvRecord)) {
            throw new IllegalArgumentException("CSV record can't be null");
        }
        boolean isNotCsv = !(Pattern.matches("^(\".+\",)*\".+\"$", csvRecord));
        if (isNotCsv) {
            throw new IllegalArgumentException("Not a CSV record");
        }

        return csvRecord;
    }
}
