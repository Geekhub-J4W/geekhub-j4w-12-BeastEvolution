package edu.geekhub.homework.playlist;

import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

class TxtConvertorImp implements TxtConvertor {
    private static final String TXT_EXTENSION = ".txt";

    public TxtConvertorImp() {
    }

    public String convertToString(File inputFile) {
        Objects.requireNonNull(inputFile, "Can't convert file equal null");
        checkInputFile(inputFile);

        try {
            return Files.readString(inputFile.toPath());
        } catch (IOException e) {
            throw new ReadFromFileException("Failed to read \"playlist.txt\" file data", e);
        }
    }

    private void checkInputFile(File path) {
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
