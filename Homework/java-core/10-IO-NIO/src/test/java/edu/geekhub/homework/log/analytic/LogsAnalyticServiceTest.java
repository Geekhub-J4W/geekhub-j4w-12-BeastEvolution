package edu.geekhub.homework.log.analytic;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LogsAnalyticServiceTest {
    LogsAnalyticService logsAnalyticService;

    @TempDir
    Path saveDirectory;

    Logger logger;

    @BeforeEach
    void setUp() throws IOException {
        File logFile = Files.createFile(saveDirectory.resolve("logFile.txt")).toFile();
        logger = new Logger(logFile);

        logsAnalyticService = new LogsAnalyticService(logger);
    }

    @Test
    @Tag("Correct")
    void Get_all_logs() {
        LogRecord newRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");
        List<LogRecord> expectedLogRecords = List.of(newRecord);

        logger.log(newRecord);

        assertThat(logsAnalyticService.getAll())
            .isEqualTo(expectedLogRecords);
    }

    @Test
    @Tag("Correct")
    void Get_filtered_logs() {
        //Arrange
        LogRecord firstRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");
        LogRecord secondRecord = new LogRecord(LogType.INFO, LocalDateTime.now(), "text");

        logger.log(firstRecord);
        logger.log(secondRecord);

        List<LogRecord> expectedRecords = List.of(firstRecord);

        //Act
        List<LogRecord> actualRecords = logsAnalyticService.getFilteredLogRecords(
            logRecord -> logRecord.type().equals(LogType.ERROR)
        );
        //Assert
        assertThat(expectedRecords)
            .isEqualTo(actualRecords);
    }

    @Test
    @Tag("Correct")
    void Get_sorted_logs() {
        //Arrange
        LogRecord firstRecord = new LogRecord(LogType.ERROR, LocalDateTime.now(), "text");
        LogRecord secondRecord = new LogRecord(LogType.INFO, LocalDateTime.now(), "text");

        logger.log(firstRecord);
        logger.log(secondRecord);

        List<LogRecord> expectedRecords = List.of(
            secondRecord,
            firstRecord
        );

        //Act
        List<LogRecord> actualRecords = logsAnalyticService.getSortedLogRecords(
            logsAnalyticService.getAll(),
            Comparator.comparing(LogRecord::type).reversed()
        );

        //Assert
        assertThat(expectedRecords)
            .isEqualTo(actualRecords);
    }


}
