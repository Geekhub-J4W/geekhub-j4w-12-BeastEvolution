package edu.geekhub.homework.playlist;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import edu.geekhub.homework.playlist.interfaces.PlaylistConvertor;
import edu.geekhub.homework.playlist.util.txt.TxtConvertorImp;
import edu.geekhub.homework.playlist.util.txt.interfaces.TxtConvertor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistConvertorTest {

    static String fileSystemPathSeparator = System.getProperty("path.separator");

    PlaylistConvertor sut;
    @Mock
    Logger logger;

    Clock clock = Clock.fixed(
        Instant.parse("2018-08-22T10:00:00Z"),
        ZoneOffset.UTC
    );

    @BeforeEach
    void setUp() {
        sut = new PlaylistConvertorImp(logger, clock, new TxtConvertorImp());
    }

    @Test
    @Tag("Correct")
    void Convert_file_records_to_playlist_elements() throws MalformedURLException {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_convertor/playlist.txt").getPath());

        List<PlaylistElement> expectedResult = List.of(
            new PlaylistElement(
                "The Unforgiven.mp3",
                Path.of("Heavy Metal", "Metallica", "The Unforgiven", "The Unforgiven.mp3"),
                new URL("https://ia903003.us.archive.org/34/items/metallicatheunforgiven/Metallica%20-%20The%20Unforgiven.mp3")
            )
        );

        //Act
        List<PlaylistElement> actualResult = sut.convert(playlist);

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);

        verifyNoInteractions(logger);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_convert_if_record_not_have_all_characteristics_of_song() {
        //Arrange
        File playlist = new File(this.getClass()
            .getResource(
                "/playlist_convertor/record_without_all_characteristics.txt"
            )
            .getPath());

        List<PlaylistElement> expectedResult = new ArrayList<>();

        IllegalArgumentException e = new IllegalArgumentException(String.format(
            "Playlist record must contain %d song characteristics, but contains %d",
            PlaylistElement.NUMBERS_OF_SONG_CHARACTERISTICS,
            3
        ));

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Can't save \"" + "Metallica | The Unforgiven | The Unforgiven | https://ia903003.us.archive.org/34/items/metallicatheunforgiven/Metallica%20-%20The%20Unforgiven.mp3" + "\""
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<PlaylistElement> actualResult = sut.convert(playlist);

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_convert_if_record_fields_separated_incorrectly() {
        //Arrange
        File playlist = new File(this.getClass()
            .getResource(
                "/playlist_convertor/incorrect_fields_separation.txt"
            )
            .getPath());

        List<PlaylistElement> expectedResult = new ArrayList<>();

        IllegalArgumentException e = new IllegalArgumentException(String.format(
            "Playlist record must contain %d song characteristics, but contains %d",
            PlaylistElement.NUMBERS_OF_SONG_CHARACTERISTICS,
            3
        ));

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Can't save \"" + "Heavy Metal| Metallica | The Unforgiven | The Unforgiven | https://ia903003.us.archive.org/34/items/metallicatheunforgiven/Metallica%20-%20The%20Unforgiven.mp3" + "\""
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<PlaylistElement> actualResult = sut.convert(playlist);

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_convert_if_characteristics_of_song_contain_illegal_characters() {
        //Arrange
        File playlist = new File(this.getClass()
            .getResource(
                "/playlist_convertor/record_with_illegal_chars.txt"
            )
            .getPath());

        List<PlaylistElement> expectedResult = new ArrayList<>();

        IllegalArgumentException e = new IllegalArgumentException(
            String.format("Incorrect path node name: \"%s\"", "Heavy Metalы")
        );

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Can't save \"" + "Heavy Metalы | Metallica | The Unforgiven | The Unforgiven | https://ia903003.us.archive.org/34/items/metallicatheunforgiven/Metallica%20-%20The%20Unforgiven.mp3" + "\""
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<PlaylistElement> actualResult = sut.convert(playlist);

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_convert_if_record_url_is_incorrect() {
        //Arrange
        File playlist = new File(this.getClass()
            .getResource(
                "/playlist_convertor/incorrect_url.txt"
            )
            .getPath());

        List<PlaylistElement> expectedResult = new ArrayList<>();

        IllegalArgumentException e = new IllegalArgumentException(
            String.format("Playlist record contain incorrect link on mp3 file: \"%s\"", "https://ia903003.us.archive.o")
        );

        LogRecord expectedArgument = new LogRecord(
            LogType.ERROR,
            LocalDateTime.now(clock),
            "Can't save \"" + "Heavy Metal | Metallica | The Unforgiven | The Unforgiven | https://ia903003.us.archive.o" + "\""
                + System.lineSeparator()
                + e.getMessage(),
            e
        );

        //Act
        List<PlaylistElement> actualResult = sut.convert(playlist);

        //Assert
        assertThat(actualResult).isEqualTo(expectedResult);

        verify(logger).log(expectedArgument);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_convert_if_fail_to_read_data_from_input_file() throws IOException {
        //Arrange
        File playlist = new File(this.getClass().getResource("/playlist_convertor/playlist.txt").getPath());

        TxtConvertor txtConvertor = mock(TxtConvertor.class);
        when(txtConvertor.convertToString(playlist)).thenThrow(IOException.class);
        sut = new PlaylistConvertorImp(logger, clock, txtConvertor);

        //Act
        //Assert
        assertThatThrownBy(() -> sut.convert(playlist))
            .isInstanceOf(ReadFromFileException.class)
            .hasMessage("Failed to read input file data");

    }

}
