package edu.geekhub.homework.playlist.util;

import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtConvertor {
    private static final String TXT_EXTENSION = ".txt";

    private TxtConvertor() {
    }

    public static String convertToString(File inputFile) {
        try {
            checkInputFile(inputFile);

            return Files.readString(inputFile.toPath());
        } catch (IOException e) {
            throw new ReadFromFileException("Failed to read \"playlist.txt\" file data", e);
        }
    }

    private static void checkInputFile(File path) {
        boolean isNotFile = !(path.isFile());
        if (isNotFile) {
            throw new IllegalArgumentException(
                "There is no file for this path \"" + path + "\""
            );
        }
        boolean isNotTxtFile = !(path.getName().endsWith(TXT_EXTENSION));
        if (isNotTxtFile) {
            throw new IllegalArgumentException(
                "File for this \"" + path + "\" path not a \".txt\" format"
            );
        }
    }
}
