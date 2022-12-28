package edu.geekhub.homework.log;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final File LOG_FILE;

    static {
        URL localPackage = Logger.class.getResource("");
        File file = new File(localPackage.getPath());
        while (!file.getName().equals("10-IO-NIO")) {
            file = new File(file.getParent());
        }
        LOG_FILE = file.toPath().resolve("src/main/resources/log.txt").toFile();

//            URL localPackage = Logger.class.getResource("");
//            Path path = Paths.get(localPackage.toURI());
//            LOG_FILE = path.subpath(0, path.toAbsolutePath().getNameCount() - 8).resolve("src/main/resources/log.txt").toFile();
    }

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public void log(LogRecord newLog) {
        List<LogRecord> existLogs = getLogs();
        existLogs.add(newLog);

        try (FileOutputStream fileOutputStream = new FileOutputStream(LOG_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (LogRecord existLog : existLogs) {
                objectOutputStream.writeObject(existLog);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Log file is not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed saving LogRecord to log.txt file", e);
        }
    }

    public List<LogRecord> getLogs() {
        List<LogRecord> logs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(LOG_FILE)) {
            if (fis.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Object data = ois.readObject();
                    if(data instanceof LogRecord) {
                        logs.add((LogRecord) data);
                    } else {
                        throw new RuntimeException(
                            "Invalid data in log file",
                            new ClassCastException()
                        );
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed read LogRecord from log.txt file", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find out serializable LogRecord class", e);
        }

        return logs;
    }

//    private LogRecord getLog() {
//        try (FileInputStream fis = new FileInputStream(LOG_FILE)) {
//            if (fis.available() != 0) {
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                while (fis.available() != 0) {
//                    Object data = ois.readObject();
//                    if(data instanceof LogRecord) {
//                        return (LogRecord) data;
//                    } else {
//                        throw new RuntimeException(
//                            "Invalid data in log file",
//                            new ClassCastException()
//                        );
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed read LogRecord from log.txt file", e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Failed to find out serializable LogRecord class", e);
//        }
//    }
}
