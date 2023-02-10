package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.exceptions.SaveException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceImpTest {
    PlaylistServiceImp sut;

    @TempDir
    Path saveDirectory;

    @Mock
    Logger logger;

    Clock clock = Clock.fixed(
        Instant.parse("2018-08-22T10:00:00Z"),
        ZoneOffset.UTC
    );

    @Test
    @Tag("Correct")
    void Save_songs_on_drive() {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_service/playlist.txt").getPath());

        sut = new PlaylistServiceImp(saveDirectory, playlist, logger, clock);
        Path pathToSong = Path.of(
            "Українська музика",
            "Шабля",
            "Гімн Оборони України",
            "Браття українці.mp3"
        );
        Path fullPath = saveDirectory.resolve(pathToSong);

        List<File> expectedResult = List.of(fullPath.toFile());

        LogRecord expectedArgument = new LogRecord(
            LogType.INFO,
            LocalDateTime.now(clock),
            "File \"" + "Браття українці.mp3" + "\" was saved by path: "
                + System.lineSeparator()
                + fullPath
        );

        //Act
        List<File> actualResult = sut.saveSongsOnDrive();

        //Assert
        assertThat(actualResult)
            .isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Tag("Correct")
    void Invalid_to_save_song_if_it_already_exist_by_this_path() throws IOException {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_service/playlist.txt").getPath());

        sut = new PlaylistServiceImp(saveDirectory, playlist, logger, clock);
        Path pathToSong = Path.of(
            "Українська музика",
            "Шабля",
            "Гімн Оборони України",
            "Браття українці.mp3"
        );
        Path fullPath = saveDirectory.resolve(pathToSong);

        createFile(fullPath);

        List<File> expectedResult = List.of();

        SaveException e = new SaveException("Because it already exist");

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Failed to save " + "Браття українці.mp3" + " file"
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<File> actualResult = sut.saveSongsOnDrive();

        //Assert
        assertThat(actualResult)
            .isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    void createFile(Path pathToFile) throws IOException {
        Files.createDirectories(pathToFile.getParent());
        Files.createFile(pathToFile);
    }

    @Test
    @Disabled
    @Tag("Correct")
    void Invalid_to_save_song_if_it_file_bigger_then_10MB() {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_service/big_file.txt").getPath());

        sut = new PlaylistServiceImp(saveDirectory, playlist, logger, clock);

        List<File> expectedResult = List.of();

        SaveException e = new SaveException("MP3 file can't be bigger then 10MB");

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Failed to save " + "The Unforgiven.mp3" + " file"
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<File> actualResult = sut.saveSongsOnDrive();

        //Assert
        assertThat(actualResult)
            .isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Disabled
    @Tag("Correct")
    void Invalid_to_save_song_if_it_file_not_mp3() {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_service/url_to_not_mp3.txt").getPath());

        sut = new PlaylistServiceImp(saveDirectory, playlist, logger, clock);

        List<File> expectedResult = List.of();

        SaveException e = new SaveException("Failed to get data from URL");

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Failed to save " + "Браття українці.mp3" + " file"
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<File> actualResult = sut.saveSongsOnDrive();

        //Assert
        assertThat(actualResult)
            .isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

}
