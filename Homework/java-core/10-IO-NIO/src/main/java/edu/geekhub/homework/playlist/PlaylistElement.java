package edu.geekhub.homework.playlist;

import java.net.URL;
import java.nio.file.Path;

public record PlaylistElement(String name, Path pathToFile, URL fileUrl) {

}
