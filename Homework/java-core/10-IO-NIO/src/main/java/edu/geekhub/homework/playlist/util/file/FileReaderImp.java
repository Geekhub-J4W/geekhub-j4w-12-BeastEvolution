package edu.geekhub.homework.playlist.util.file;

import edu.geekhub.homework.playlist.util.file.interfaces.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderImp implements FileReader {
    @Override
    public String readString(Path path) throws IOException {
        return Files.readString(path);
    }
}
