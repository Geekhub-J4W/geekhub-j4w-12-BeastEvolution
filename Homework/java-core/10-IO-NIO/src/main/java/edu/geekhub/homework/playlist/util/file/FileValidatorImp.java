package edu.geekhub.homework.playlist.util.file;

import edu.geekhub.homework.playlist.util.file.interfaces.FileExtensionValidator;
import edu.geekhub.homework.playlist.util.file.interfaces.FileValidator;

import java.io.File;
import java.util.Objects;

public class FileValidatorImp implements FileValidator {
    private final FileExtensionValidator fileExtensionValidator = new FileExtensionValidatorImp();

    @Override
    public File validateFile(File file, String extension) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File can't be null");
        }
        boolean isNotFile = !(file.isFile());
        if (isNotFile) {
            throw new IllegalArgumentException(
                String.format("There is no file for this inputFile \"%s\"", file)
            );
        }
        fileExtensionValidator.validateExtension(extension);
        boolean isNotTxtFile = !(file.getName().endsWith(extension));
        if (isNotTxtFile) {
            throw new IllegalArgumentException(
                String.format("File for this \"%s\" inputFile not a \"%s\" format", file, extension)
            );
        }

        return file;
    }
}
