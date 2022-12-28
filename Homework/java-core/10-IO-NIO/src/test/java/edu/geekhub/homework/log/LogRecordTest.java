package edu.geekhub.homework.log;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogRecordTest {
    LogRecord logRecord;

    @Test
    void test() {
        System.out.println(
            new LogRecord(
                LogType.ERROR,
                "There is no file for this path \""
                    + "path" + "\"",
                new IllegalArgumentException(),
                Thread.currentThread().getStackTrace()
            )
        );
    }

}
