package edu.geekhub.homework.log;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record LogRecord(
    LogType type,
    LocalDateTime creationTime,
    String massage
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public LogRecord(LogType type, LocalDateTime creationTime, String massage, Exception e) {
        this(
            type,
            creationTime,
            e.getClass().getSimpleName()
                + ": "
                + massage
        );
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(creationTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
            .append(System.lineSeparator())
            .append(type)
            .append(System.lineSeparator())
            .append(massage).toString();
    }
}
