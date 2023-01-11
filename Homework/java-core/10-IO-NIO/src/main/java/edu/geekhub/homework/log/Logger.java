package edu.geekhub.homework.log;

import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import edu.geekhub.homework.playlist.exceptions.SaveException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Logger {
    private static File logFile;

    static {
        URL localPackage = Logger.class.getResource("");
        assert localPackage != null;
        File file = new File(localPackage.getPath());
        while (!file.getName().equals("10-IO-NIO")) {
            file = new File(file.getParent());
        }
        logFile = file.toPath().resolve("src/main/resources/log.txt").toFile();
    }

    public Logger() {
    }

    public Logger(File logFile) {
        Logger.logFile = logFile;
    }

    public void log(LogRecord newLog) {
        Objects.requireNonNull(newLog, "LogRecord is null");
        List<LogRecord> allLogs = getOldRecords();

        allLogs.add(newLog);


        try (FileOutputStream fileOutputStream = new FileOutputStream(logFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (LogRecord log : allLogs) {
                objectOutputStream.writeObject(log);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(
                "Log file is not found by path \"" + logFile + "\" not found", e);
        } catch (IOException e) {
            throw new SaveException("Failed saving LogRecord to log.txt file", e);
        }
    }

    public List<LogRecord> getLogs() {
        List<LogRecord> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(logFile)) {
            if (fis.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Object data = ois.readObject();
                    if (data instanceof LogRecord logRecord) {
                        logs.add(logRecord);
                    } else {
                        throw new ReadFromFileException(
                            "Failed read LogRecord from log.txt file",
                            new ClassCastException("Invalid data in log file"));
                    }
                }
            }
        } catch (IOException e) {
            throw new ReadFromFileException("Failed read LogRecord from log.txt file", e);
        } catch (ClassNotFoundException e) {
            throw new ReadFromFileException(
                "Failed read LogRecord from log.txt file",
                new ClassCastException("Failed to find out serializable LogRecord class")
            );
        }

        return logs;
    }

    private List<LogRecord> getOldRecords() {
        if (logFile.exists() && logFile.length() > 0) {
            return getLogs();
        }

        return new ArrayList<>();
    }
}
