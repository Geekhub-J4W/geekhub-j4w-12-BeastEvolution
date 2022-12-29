package edu.geekhub.homework.log;

import edu.geekhub.homework.playlist.exceptions.ReadFromFileException;
import edu.geekhub.homework.playlist.exceptions.SaveException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final File LOG_FILE;

    static {
        URL localPackage = Logger.class.getResource("");
        assert localPackage != null;
        File file = new File(localPackage.getPath());
        while (!file.getName().equals("10-IO-NIO")) {
            file = new File(file.getParent());
        }
        LOG_FILE = file.toPath().resolve("src/main/resources/log.txt").toFile();
    }

    public void log(LogRecord newLog) {
        List<LogRecord> existLogs = getLogs();
        existLogs.add(newLog);

        try (FileOutputStream fileOutputStream = new FileOutputStream(LOG_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (LogRecord existLog : existLogs) {
                objectOutputStream.writeObject(existLog);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(
                "Log file is not found by path \"" + LOG_FILE + "\" not found", e);
        } catch (IOException e) {
            throw new SaveException("Failed saving LogRecord to log.txt file", e);
        }
    }

    public List<LogRecord> getLogs() {
        List<LogRecord> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(LOG_FILE)) {
            if (fis.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Object data = ois.readObject();
                    if (data instanceof LogRecord logRecord) {
                        logs.add(logRecord);
                    } else {
                        throw new ClassCastException("Invalid data in log file");
                    }
                }
            }
        } catch (IOException e) {
            throw new ReadFromFileException("Failed read LogRecord from log.txt file", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find out serializable LogRecord class", e);
        }

        return logs;
    }
}
