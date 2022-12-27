package edu.geekhub.homework;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Service {
    private  static final Path HOME_PATH = Paths.get(System.getProperty("user.home"));
    private  static final Path FOLDER_TO_SAVE_PLAYLIST = HOME_PATH.resolve("Music Library");
    private static final File PLAYLIST_FILE = new File("./Homework/java-core/10-IO-NIO/src/main/resources/playlist.txt");
    private static final int FILE_MAX_SIZE = 1_048_576 * 10;
    private final TxtConvertor txtConvertor;

    public Service(TxtConvertor txtConvertor) {
        this.txtConvertor = txtConvertor;
    }

    public void saveSongsOnDrive() {
        List<PlaylistElement> playlistElements = txtConvertor.convertToMusic(PLAYLIST_FILE);

        playlistElements.stream()
            .forEach(this::saveSong);
    }

    private void saveSong(PlaylistElement playlistElement) {
        Path savingPath = FOLDER_TO_SAVE_PLAYLIST.resolve(playlistElement.pathToFile());
        URL songLink = playlistElement.fileUrl();
        createSavingPath(savingPath);

        byte[] songData;
        try {
            songData = downloadFromServer(songLink);
            saveOnDrive(songData, savingPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                        throw new IllegalArgumentException("MP3 file can't be bigger then 10MB");
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
