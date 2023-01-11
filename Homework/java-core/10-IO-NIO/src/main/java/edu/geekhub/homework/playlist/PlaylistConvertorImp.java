package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import edu.geekhub.homework.playlist.interfaces.PlaylistConvertor;
import edu.geekhub.homework.playlist.util.chars.CharValidatorImp;
import edu.geekhub.homework.playlist.util.chars.interfaces.CharValidator;
import edu.geekhub.homework.playlist.util.txt.interfaces.TxtConvertor;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class PlaylistConvertorImp implements PlaylistConvertor {
    private static final String LINE_ELEMENTS_SEPARATOR = " \\| ";

    private final Logger logger;
    private final Clock clock;
    private final TxtConvertor txtConvertor;
    private final CharValidator charValidator = new CharValidatorImp(
        new File(
            Objects.requireNonNull(
                    CharValidator.class.getResource("/SymbolsForPathNaming.csv"))
                .getPath()
        )


    );

    public PlaylistConvertorImp(Logger logger, Clock clock, TxtConvertor txtConvertor) {
        this.logger = logger;
        this.clock = clock;
        this.txtConvertor = txtConvertor;
    }

    @Override
    public List<PlaylistElement> convert(File playListRecords) {

        String[] records = getRecords(playListRecords);

        return Arrays.stream(records)
            .map(this::convertToPlaylistElement)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    private String[] getRecords(File inputFile) {
        String fileContent;
        try {
            fileContent = txtConvertor.convertToString(inputFile);
        } catch (IOException e) {
            throw new ReadFromFileException("Failed to read input file data", e);
        }
        return fileContent.split(System.lineSeparator());
    }

    private Optional<PlaylistElement> convertToPlaylistElement(String playlistRecord) {
        try {
            Path pathToFile = getPathToFile(playlistRecord);
            URL fileUrl = getMusicUrl(playlistRecord);
            String fileName = getFileName(pathToFile);

            return Optional.of(new PlaylistElement(fileName, pathToFile, fileUrl));
        } catch (IllegalArgumentException e) {
            logger.log(
                new LogRecord(LogType.ERROR,
                    LocalDateTime.now(clock),
                    "Can't save \"" + playlistRecord + "\""
                        + System.lineSeparator()
                        + e.getMessage(),
                    e
                )
            );

            return Optional.empty();
        }
    }

    private Path getPathToFile(String playlistRecord) {
        String[] recordElements = playlistRecord.split(LINE_ELEMENTS_SEPARATOR);
        String[] elementsOfSavePath = getPathElements(recordElements);

        validateSavePathElements(elementsOfSavePath);
        elementsOfSavePath = makeFileNameCorrect(elementsOfSavePath);

        return Paths.get(joinPath(elementsOfSavePath));
    }

    private String[] getPathElements(String[] lineElements) {
        int urlIndex = lineElements.length - 1;
        return Arrays.copyOf(lineElements, urlIndex);
    }

    private void validateSavePathElements(String[] elementsOfSavePath) {
        boolean isIncorrectNumberOfSongCharacteristics =
            (elementsOfSavePath.length != PlaylistElement.NUMBERS_OF_SONG_CHARACTERISTICS);
        if (isIncorrectNumberOfSongCharacteristics) {
            throw new IllegalArgumentException(String.format(
                "Playlist record must contain %d song characteristics, but contains %d",
                PlaylistElement.NUMBERS_OF_SONG_CHARACTERISTICS,
                elementsOfSavePath.length
            ));
        }
        Arrays.stream(elementsOfSavePath)
            .forEach(this::validatePathElement);
    }

    private void validatePathElement(String text) {
        char[] chars = text.toCharArray();
        boolean elementContainIllegalCharacter = CharBuffer.wrap(chars).chars()
            .mapToObj(charValidator::isInvalidChar)
            .anyMatch(value -> value.equals(Boolean.TRUE));

        if (elementContainIllegalCharacter) {
            throw new IllegalArgumentException(
                String.format("Incorrect path node name: \"%s\"", text)
            );
        }
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
                String.format("Playlist record contain incorrect link on mp3 file: \"%s\"", url)
            );
        }
    }

    private void validateUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        boolean urlIsInvalid = !(urlValidator.isValid(url));
        if (urlIsInvalid) {
            throw new IllegalArgumentException(
                String.format("Playlist record contain incorrect link on mp3 file: \"%s\"", url)
            );
        }
    }

    private String getFileName(Path pathToFile) {
        return pathToFile.getFileName().toString();
    }
}
