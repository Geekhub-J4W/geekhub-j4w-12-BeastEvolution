package edu.geekhub.homework.playlist.util.file.interfaces;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {
    String readString(Path path) throws IOException;
}
