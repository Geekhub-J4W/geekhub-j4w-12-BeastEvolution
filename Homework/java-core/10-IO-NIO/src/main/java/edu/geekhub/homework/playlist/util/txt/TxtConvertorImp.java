package edu.geekhub.homework.playlist.util.txt;

import edu.geekhub.homework.playlist.util.file.FileValidatorImp;
import edu.geekhub.homework.playlist.util.file.interfaces.FileValidator;
import edu.geekhub.homework.playlist.util.txt.interfaces.TxtConvertor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtConvertorImp implements TxtConvertor {
    private static final String TXT_EXTENSION = ".txt";

    private static final FileValidator fileValidator = new FileValidatorImp();

    public String convertToString(File inputFile) throws IOException {
        fileValidator.validateFile(inputFile, TXT_EXTENSION);

        return Files.readString(inputFile.toPath());
    }
}
