package edu.geekhub.homework.playlist;

import static java.util.stream.Collectors.*;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.util.CsvConvertor;
import edu.geekhub.homework.playlist.util.TxtConvertor;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlaylistConvertor {
    private static final String LINE_ELEMENTS_SEPARATOR = " \\| ";

    private static final int[] ALLOWED_PATH_NAME_SYMBOLS = CsvConvertor.convert(
        new File(Objects.requireNonNull(
            PlaylistConvertor.class.getResource("/SymbolsForPathNaming.csv"))
            .getPath()
        )
    );

    private static final Logger logger = new Logger();


    public List<PlaylistElement> convertToPlaylist(File inputFile) {
        String fileContent = TxtConvertor.convertToString(inputFile);
        String[] elements = getElement(fileContent);

        return Arrays.stream(elements)
            .map(this::convertToPlaylistElement)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    private String[] getElement(String playlist) {
        return playlist.split(System.lineSeparator());
    }

    private Optional<PlaylistElement> convertToPlaylistElement(String line) {
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

    private Path getPathToFile(String line) {
        String[] lineElements = line.split(LINE_ELEMENTS_SEPARATOR);
        String[] pathElements = getPathElements(lineElements);

        validatePath(pathElements);
        pathElements = makeFileNameCorrect(pathElements);

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
        if (isInvalidChar(character)) {
            throw new IllegalArgumentException("Incorrect path node name");
        }
    }

    private boolean isInvalidChar(int inputCharacter) {
        for (int allowedCharacter : ALLOWED_PATH_NAME_SYMBOLS) {
            if (inputCharacter == allowedCharacter) {
                return false;
            }
        }

        return true;
    }

    private String[] makeFileNameCorrect(String[] path) {
        String[] correctPath = path.clone();
        int songNameIndex = path.length - 1;
        String fileName = path[songNameIndex] + ".mp3";
        correctPath[songNameIndex] = fileName;

        return correctPath;
    }

    private String joinPath(String[] pathElements) {
        return Arrays.stream(pathElements)
            .collect(joining(FileSystems.getDefault().getSeparator()));
    }

    private URL getMusicUrl(String line) {
        String url = Arrays.stream(line.split(LINE_ELEMENTS_SEPARATOR))
            .reduce((a, b) -> b)
            .orElseThrow(() -> new IllegalArgumentException("String is null"));

        validateUrl(url);

        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(
                "Playlist element contain incorrect link on mp3 file"
            );
        }
    }

    private void validateUrl(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException(
                "Playlist element contain incorrect link on mp3 file"
            );
        }
    }

    private String getFileName(Path pathToFile) {
        return pathToFile.getFileName().toString();
    }
}
