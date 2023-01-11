package edu.geekhub.homework.playlist.util.file;

import edu.geekhub.homework.playlist.util.file.interfaces.FileExtensionValidator;

import java.util.Objects;
import java.util.regex.Pattern;

public class FileExtensionValidatorImp implements FileExtensionValidator {
    @Override
    public String validateExtension(String extension) {
        if (Objects.isNull(extension)) {
            throw new IllegalArgumentException("Extension can't be null");
        }
        boolean isNotExtension = !(Pattern.matches("^\\.[a-zA-Z]+$", extension));
        if (isNotExtension) {
            throw new IllegalArgumentException("Incorrect extension");
        }
        return extension;
    }
}
