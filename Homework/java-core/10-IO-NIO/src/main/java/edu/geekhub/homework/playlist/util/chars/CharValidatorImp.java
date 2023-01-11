package edu.geekhub.homework.playlist.util.chars;

import edu.geekhub.homework.playlist.util.chars.interfaces.CharValidator;
import edu.geekhub.homework.playlist.util.csv.CsvConvertorImp;
import edu.geekhub.homework.playlist.util.csv.interfaces.CsvConvertor;
import edu.geekhub.homework.playlist.util.file.FileReaderImp;

import java.io.File;
import java.util.Arrays;

public class CharValidatorImp implements CharValidator {
    private static final int CHARACTER_LENGTH = 1;
    private static final int FIRST_CHAR_INDEX = 0;
    private final int[] allowedSymbols;

    public CharValidatorImp(File fileWithAllowedSymbols) {
        allowedSymbols = getAllowedSymbols(
            fileWithAllowedSymbols
        );
    }

    private int[] getAllowedSymbols(File csvFile) {
        CsvConvertor csvConvertor = new CsvConvertorImp(new FileReaderImp());
        String[] recordFields = csvConvertor.getRecordFields(csvFile, 0);
        checkValues(recordFields);

        return getIntRepresentation(recordFields);
    }

    private void checkValues(String[] values) {
        Arrays.stream(values)
            .forEach(value -> {
                if (value.length() != CHARACTER_LENGTH) {
                    throw new IllegalArgumentException("All value in csv file must be a char");
                }
            });
    }

    private int[] getIntRepresentation(String[] recordFields) {
        return Arrays.stream(recordFields)
            .mapToInt(value -> (int) value.toCharArray()[FIRST_CHAR_INDEX])
            .toArray();
    }

    @Override
    public boolean isInvalidChar(int inputCharacter) {
        for (int allowedCharacter : allowedSymbols) {
            if (inputCharacter == allowedCharacter) {
                return false;
            }
        }

        return true;
    }
}
