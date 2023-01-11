package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.exceptions.SaveException;
import edu.geekhub.homework.playlist.interfaces.PlaylistConvertor;
import edu.geekhub.homework.playlist.interfaces.PlaylistService;
import edu.geekhub.homework.playlist.util.txt.TxtConvertorImp;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PlaylistServiceImp implements PlaylistService {
    private static final int FILE_MAX_SIZE = 1_048_576 * 10;
    private final Path saveFolder;
    private final File playlistFile;
    private final Logger logger;
    private final Clock clock;
    private final PlaylistConvertor playlistConvertor = new PlaylistConvertorImp(
        new Logger(),
        Clock.systemDefaultZone(),
        new TxtConvertorImp()
    );

    public PlaylistServiceImp(Path saveFolder, File playlistFile, Logger logger, Clock clock) {
        this.saveFolder = saveFolder;
        this.playlistFile = playlistFile;
        this.logger = logger;
        this.clock = clock;
    }

    @Override
    public List<File> saveSongsOnDrive() {
        List<PlaylistElement> playlistElements = playlistConvertor.convert(playlistFile);

        return playlistElements.stream()
            .map(this::saveSong)
            .flatMap(Optional::stream)
            .toList();
    }

    private Optional<File> saveSong(PlaylistElement playlistElement) {
        Path savingPath = saveFolder.resolve(playlistElement.pathToSave());
        try {
            checkForAbsenceFile(savingPath);
            URL songLink = playlistElement.linkToSongFile();

            byte[] songData;

            songData = downloadFromServer(songLink);
            createSavingPath(savingPath);
            saveOnDrive(songData, savingPath);

            logger.log(
                new LogRecord(
                    LogType.INFO,
                    LocalDateTime.now(clock),
                    "File \"" + playlistElement.name() + "\" was saved by path: "
                        + System.lineSeparator()
                        + savingPath
                )
            );

            return Optional.of(savingPath.toFile());
        } catch (SaveException e) {
            logger.log(
                new LogRecord(
                    LogType.ERROR,
                    LocalDateTime.now(clock),
                    "Failed to save " + playlistElement.name() + " file"
                        + System.lineSeparator()
                        + e.getMessage(),
                    e
                ));
        }

        return Optional.empty();
    }

    private void checkForAbsenceFile(Path savingPath) {
        if (savingPath.toFile().exists()) {
            throw new SaveException("Because it already exist");
        }
    }

    private void createSavingPath(Path savingPath) {
        try {
            Files.createDirectories(savingPath.getParent());
        } catch (IOException e) {
            throw new SaveException("Failed to create file path", e);
        }
    }

    private byte[] downloadFromServer(URL songLink) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        byte[] chunk = new byte[4096];
        int bytesRead;
        int bytesReadInSum = 0;
        try (InputStream stream = songLink.openStream()) {
            while ((bytesRead = stream.read(chunk)) > 0) {
                bytesReadInSum += bytesRead;

                if (bytesReadInSum > FILE_MAX_SIZE) {
                    throw new SaveException("MP3 file can't be bigger then 10MB");
                }

                outputStream.write(chunk, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new SaveException("Failed to get data from URL", e);
        }
        return outputStream.toByteArray();
    }

    private void saveOnDrive(byte[] songData, Path savingPath) {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(savingPath.toFile())) {
                fileOutputStream.write(songData);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(
                "Failed to create file for \"" + savingPath + "\" path",
                e
            );
        } catch (IOException e) {
            throw new SaveException(
                "Failed to write data in " + savingPath.getFileName() + "file",
                e
            );
        }
    }
}
