package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.util.CSVConvertor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class PlaylistConvertor {

    private static final String TXT_EXTENSION = ".txt";
    private static final String LINE_ELEMENTS_SEPARATOR = " \\| ";

    private static final int[] ALLOWED_PATH_NAME_SYMBOLS = CSVConvertor.convert(
        new File(PlaylistConvertor.class.getResource("/SymbolsForPathNaming.csv").getPath())
    );

    private static final Logger logger = new Logger();


    public List<PlaylistElement> convertToMusic(File inputFile) {
        String fileContent = convertToString(inputFile).get();
        String[] elements = getElement(fileContent);

        return Arrays.stream(elements)
            .map(this::convertToPlaylistElement)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    public Optional<String> convertToString(File inputFile) {
        try {
            checkInputFile(inputFile);

            return Optional.of(Files.readString(inputFile.toPath()));
        } catch (IOException e) {
            logger.log(
                new LogRecord(
                    LogType.ERROR,
                    "Failed to read \"playlist.txt\" file data",
                    e
                ));

            System.exit(1);
        }

        return Optional.empty();
    }

    private void checkInputFile(File path) {
        boolean isNotFile = !(path.isFile());
        if (isNotFile) {
            logger.log(
                new LogRecord(
                    LogType.ERROR,
                    "There is no file for this path \""
                        + path.toString() + "\"",
                    Thread.currentThread().getStackTrace()
                ));
        }
        boolean isNotTxtFile = !(path.getName().endsWith(TXT_EXTENSION));
        if (isNotTxtFile) {
            logger.log(
                new LogRecord(
                    LogType.ERROR,
                    "File for this \"" + path.toString() + "\" path not a \".txt\" format",
                    Thread.currentThread().getStackTrace()
                ));
        }
    }

    private String[] getElement(String playlist) {
        return playlist.split(System.lineSeparator());
    }

    public Optional<PlaylistElement> convertToPlaylistElement(String line) {
        try {
            Path pathToFile = getPathToFile(line);
            URL fileUrl = getMusicUrl(line);
            String fileName = getFileName(pathToFile);

            return Optional.of(new PlaylistElement(fileName, pathToFile, fileUrl));
        } catch (IllegalArgumentException e) {
            logger.log(
                new LogRecord(LogType.ERROR,
                    "Can save \"" + line + "\""
                    + System.lineSeparator()
                    + e.getMessage(),
                    e
                )
            );

            return Optional.empty();
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

    public void validatePath(String[] pathNode) {
        Arrays.stream(pathNode)
            .forEach(this::validatePathNode);
    }

    private void validatePathNode(String text) {
        char[] chars = text.toCharArray();
        CharBuffer.wrap(chars).chars()
            .forEach(this::validateCharacter);
    }

    private void validateCharacter(int character) {
        if(isInvalidChar(character)) {
            throw new IllegalArgumentException("Incorrect path node name");
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

    private URL getMusicUrl(String line) {
        String url = Arrays.stream(line.split(LINE_ELEMENTS_SEPARATOR))
            .reduce((a, b) -> b)
            .orElseThrow(() -> new IllegalArgumentException("String is null"));

        validateURL(url);

        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Playlist element contain incorrect link on mp3 file");
        }
    }

    private void validateURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("Playlist element contain incorrect link on mp3 file");
        }
    }
    private String getFileName(Path pathToFile) {
        return pathToFile.getFileName().toString();
    }
}
