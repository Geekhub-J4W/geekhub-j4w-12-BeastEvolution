package edu.geekhub.homework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TxtConvertor {

    public static final String TXT_EXTENSION = ".txt";
    public static final String LINE_ELEMENTS_SEPARATOR = "|";


    public PlaylistElement convertToMusic(File inputFile) throws IOException {
        String fileText = convertToString(inputFile);
        String[] lines = getLines(fileText);

        return  null;
    }

    public String convertToString(File inputFile) throws IOException {
        checkInputFile(inputFile);

        return Files.readString(inputFile.toPath());
    }

    private void checkInputFile(File path) {
        boolean isNotFile = !(path.isFile());
        if (isNotFile) {
            throw new IllegalArgumentException("Not a file");
        }
        boolean isNotTxtFile = !(path.getName().endsWith(TXT_EXTENSION));
        if (isNotTxtFile) {
            throw new IllegalArgumentException("Not a .txt file");
        }
    }

    private String[] getLines(String playlist) {
        return playlist.split(System.lineSeparator());
    }

    private PlaylistElement convertToPlaylistElement(String line) {
        return null;
    }

    private URL getMusicUrl(String line) {
        String url = Arrays.stream(line.split(LINE_ELEMENTS_SEPARATOR))
            .reduce((a, b) -> b)
            .orElseThrow(() -> new IllegalArgumentException("String is null"));

        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Playlist element don't contain link");
        }
    }

    private Path getPathToFile(String line) {
        String[] lineElements = line.split(LINE_ELEMENTS_SEPARATOR);
        int urlIndex = lineElements.length - 1;
        String[] path = Arrays.copyOf(lineElements, urlIndex);
        int songNameIndex = path.length - 1;
        String fileName = path[songNameIndex] + ".mp3";


        return null;
    }

    public void validatePath(String[] path) {
        Arrays.stream(path)
            .forEach(this::validateDirectoryName);
    }

    private void validateDirectoryName(String text) {
        char[] chars = text.toCharArray();
        CharBuffer.wrap(chars).chars()
            .forEach(this::validateCharacter);
    }

    private void validateCharacter(int character) {
        boolean isNotFigure = !(character >= 30 && character <= 39);
        boolean isNotEnglishLetter = !(
            (character >= 65 && character <= 90)
                || (character >= 97 && character <= 122));
        boolean isNotUkrainianLetter = !(character >= 1040 && character <= 1103);
        boolean isNotSpace = !(character == 32);

        if(isNotEnglishLetter
        && isNotUkrainianLetter
        && isNotSpace) {
            throw new IllegalArgumentException("Incorrect directory name");
        }
    }
}
