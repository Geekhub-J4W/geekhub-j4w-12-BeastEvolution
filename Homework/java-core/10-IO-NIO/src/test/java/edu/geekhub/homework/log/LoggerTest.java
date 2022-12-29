package edu.geekhub.homework.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class LoggerTest {
    Logger logger;

    @BeforeEach
    void setUp() {
        logger = new Logger();
    }

    @Test
    void test() {
        logger.getLogs().stream()
            .forEach(System.out::println);
    }

    @Test
    void test2() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            logger.log(
                new LogRecord(
                    LogType.ERROR, "Failed to save song file:"
                )
            );
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        }
    }
}
