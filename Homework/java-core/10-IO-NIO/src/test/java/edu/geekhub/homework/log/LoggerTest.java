package edu.geekhub.homework.log;

import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import edu.geekhub.homework.playlist.exceptions.SaveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LoggerTest {
    @TempDir
    Path saveDirectory;

    Logger logger;

    @BeforeEach
    void setUp() throws IOException {
        File logFile = Files.createFile(saveDirectory.resolve("logFile.txt")).toFile();
        logger = new Logger(logFile);
    }

    @Test
    @Tag("Correct")
    void Save_log_record() {
        LogRecord newRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");
        List<LogRecord> expectedLogRecords = List.of(newRecord);

        logger.log(newRecord);

        assertThat(logger.getLogs())
            .isEqualTo(expectedLogRecords);
    }

    @Test
    @Tag("Correct")
    void Get_all_logs() {
        LogRecord newRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");
        List<LogRecord> expectedLogRecords = List.of(newRecord);

        logger.log(newRecord);

        assertThat(logger.getLogs())
            .isEqualTo(expectedLogRecords);
    }

    @Test
    @Tag("Null")
    void Save_log_record_equal_null() {
        assertThatThrownBy(() -> logger.log(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("LogRecord is null");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_save_record_if_log_file_not_exist() {
        File notExistentFile = new File("");
        logger = new Logger(notExistentFile);

        LogRecord newRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");

        assertThatThrownBy(() -> logger.log(newRecord))
            .isInstanceOf(SaveException.class)
            .hasMessage("Log file is not found by path \"" + notExistentFile + "\" not found");
    }

    @Test
    @Tag("Exception")
    void Invalid_to_read_logs_if_file_contain_incorrect_data() {
        File ligFile = new File(this.getClass().getResource("/logger/incorrect_data.txt").getPath());
        logger = new Logger(ligFile);

        assertThatThrownBy(() -> logger.getLogs())
            .isInstanceOf(ReadFromFileException.class)
            .hasMessage("Failed read LogRecord from log.txt file");
    }
}
