package edu.geekhub.homework.playlist.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class CsvConvertor {

    private static final int CHARACTER_LENGTH = 1;

    private CsvConvertor() {
    }

    private static final String SCV = ".csv";
    private static final String VAlUE_SEPARATOR = ",";

    public static int[] convert(File inputFile) {
        checkInputFile(inputFile);

        String[] values = getValues(inputFile);
        checkValues(values);

        return Arrays.stream(values)
            .mapToInt(value -> (int) value.toCharArray()[0])
            .toArray();
    }

    private static void checkInputFile(File path) {
        boolean isNotFile = !(path.isFile());
        if (isNotFile) {
            throw new IllegalArgumentException("Not a file");
        }
        boolean isNotTxtFile = !(path.getName().endsWith(SCV));
        if (isNotTxtFile) {
            throw new IllegalArgumentException("Not a .scv file");
        }
    }

    private static String[] getValues(File csvFile) {
        return convertToString(csvFile).split(VAlUE_SEPARATOR);
    }

    private static String convertToString(File inputFile) {
        checkInputFile(inputFile);

        try {
            return Files.readString(inputFile.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("File is to large");
        }
    }

    private static void checkValues(String[] values) {
        Arrays.stream(values)
            .forEach(value -> {
                if (value.length() != CHARACTER_LENGTH) {
                    throw new IllegalArgumentException("All value must be a char");
                }
            });
    }

}
