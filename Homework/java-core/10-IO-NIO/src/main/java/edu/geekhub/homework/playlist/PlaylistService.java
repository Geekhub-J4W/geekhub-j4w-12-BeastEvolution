package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.playlist.exceptions.SaveException;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PlaylistService {
    private  static final Path HOME_PATH = Paths.get(System.getProperty("user.home"));
    private  static final Path FOLDER_TO_SAVE_PLAYLIST = HOME_PATH.resolve("Music Library");
    private static final File PLAYLIST_FILE = new File(PlaylistService.class.getResource("/playlist.txt").getPath());
    private static final int FILE_MAX_SIZE = 1_048_576 * 10;
    private static final Logger logger = new Logger();
    private final PlaylistConvertor playlistConvertor;

    public PlaylistService(PlaylistConvertor playlistConvertor) {
        this.playlistConvertor = playlistConvertor;
    }

    public void saveSongsOnDrive() {
        List<PlaylistElement> playlistElements = playlistConvertor.convertToMusic(PLAYLIST_FILE);

        playlistElements.stream()
            .forEach(this::saveSong);
    }

    private void saveSong(PlaylistElement playlistElement) {
        Path savingPath = FOLDER_TO_SAVE_PLAYLIST.resolve(playlistElement.pathToFile());
        URL songLink = playlistElement.fileUrl();

        byte[] songData;
        try {
            songData = downloadFromServer(songLink);
            createSavingPath(savingPath);
            saveOnDrive(songData, savingPath);
        } catch (SaveException e) {
            logger.log(
                new LogRecord(
                    LogType.ERROR,
                    "Failed to save " + playlistElement.name() + " file."
                        + System.lineSeparator()
                        + e.getMessage(),
                    e
                ));
        }
    }

    private void createSavingPath(Path savingPath) {
        try {
            Files.createDirectories(savingPath.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] downloadFromServer(URL songLink) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            int bytesReadInSum = 0;
            InputStream stream = songLink.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                bytesReadInSum += bytesRead;
                if (bytesReadInSum > FILE_MAX_SIZE) {
                        throw new SaveException("MP3 file can't be bigger then 10MB");
                    }
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return outputStream.toByteArray();
    }

    private void saveOnDrive(byte[] songData, Path savingPath) {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(savingPath.toFile())) {
                fileOutputStream.write(songData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
