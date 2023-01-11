package edu.geekhub.homework.playlist;

import java.net.URL;
import java.nio.file.Path;

public record PlaylistElement(String name, Path pathToSave, URL linkToSongFile) {
    public static final int NUMBERS_OF_SONG_CHARACTERISTICS = 4;
}
