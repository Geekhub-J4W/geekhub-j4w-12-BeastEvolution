package edu.geekhub.homework;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class TxtConvertor {

    private static final String TXT_EXTENSION = ".txt";
    private static final String LINE_ELEMENTS_SEPARATOR = " \\| ";

    private static final int[] ALLOWED_PATH_NAME_SYMBOLS = CSVConvertor.convert(new File("./Homework/java-core/10-IO-NIO/src/main/resources/SymbolsForPathNaming.csv"));


    public List<PlaylistElement> convertToMusic(File inputFile) {
        String fileText = convertToString(inputFile);
        String[] lines = getLines(fileText);

        return Arrays.stream(lines)
            .map(this::convertToPlaylistElement)
            .toList();
    }

    public String convertToString(File inputFile) {
        checkInputFile(inputFile);

        try {
            return Files.readString(inputFile.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("File is to large");
        }
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

    public PlaylistElement convertToPlaylistElement(String line) {
        Path pathToFile = getPathToFile(line);
        URL fileUrl = getMusicUrl(line);
        return new PlaylistElement(pathToFile, fileUrl);
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

    public Path getPathToFile(String line) {
        String[] lineElements = line.split(LINE_ELEMENTS_SEPARATOR);
        String[] pathElements = getPathElements(lineElements);

        validatePath(pathElements);
        makeFileNameCorrect(pathElements);

        return Paths.get(joinPath(pathElements));
    }

    private String[] getPathElements(String[] lineElements) {
        int urlIndex = lineElements.length - 1;
        return Arrays.copyOf(lineElements, urlIndex);
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
        if(isInvalidChar(character)) {
            throw new IllegalArgumentException("Incorrect directory name");
        }
    }

    private boolean isInvalidChar(int inputCharacter) {
        for (int allowedCharacter : ALLOWED_PATH_NAME_SYMBOLS) {
            if(inputCharacter == allowedCharacter) {
                return false;
            }
        }

        return true;
    }

    private String[] makeFileNameCorrect(String[] path) {
        int songNameIndex = path.length - 1;
        String fileName = path[songNameIndex] + ".mp3";
        path[songNameIndex] = fileName;

        return path;
    }

    private String joinPath(String[] pathElements) {
        return Arrays.stream(pathElements)
            .collect(joining(FileSystems.getDefault().getSeparator()));
    }
}
