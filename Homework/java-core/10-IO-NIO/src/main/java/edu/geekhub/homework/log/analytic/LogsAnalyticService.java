package edu.geekhub.homework.log.analytic;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LogsAnalyticService {
    private final Logger logger;

    public LogsAnalyticService(Logger logger) {
        this.logger = logger;
    }

    public List<LogRecord> getAll() {
        return logger.getLogs();
    }

    public List<LogRecord> filterLogRecords(Predicate<LogRecord> predicate) {
        return logger.getLogs().stream()
            .filter(predicate)
            .toList();
    }

    public List<LogRecord> filterLogRecords(
        List<LogRecord> logRecords,
        Comparator<LogRecord> comparator
    ) {
        return logRecords.stream()
            .sorted(comparator)
            .toList();
    }
}
