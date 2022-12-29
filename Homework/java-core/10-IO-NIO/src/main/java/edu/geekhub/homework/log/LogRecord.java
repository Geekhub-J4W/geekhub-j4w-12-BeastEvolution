package edu.geekhub.homework.log;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public record LogRecord(
    LogType type,
    LocalDateTime creationTime,
    String massage
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public LogRecord(LogType type, String massage) {
        this(
            type,
            LocalDateTime.now(),
            massage
        );
    }

    public LogRecord(LogType type, String massage, Exception e) {
        this(
            type,
            LocalDateTime.now(),
            e.getClass().getSimpleName()
                + ": "
                + massage
        );
    }

    public LogRecord(LogType type, String massage, StackTraceElement[] stackTrace) {
        this(
            type,
            LocalDateTime.now(),
            massage
                + System.lineSeparator()
                + System.lineSeparator()
                + Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(System.lineSeparator()))
        );
    }

    public LogRecord(LogType type, String massage, Exception e, StackTraceElement[] stackTrace) {
        this(
            type,
            LocalDateTime.now(),
            e.getClass().getSimpleName()
                + ": "
                + massage
                + System.lineSeparator()
                + System.lineSeparator()
                + Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(System.lineSeparator()))
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
