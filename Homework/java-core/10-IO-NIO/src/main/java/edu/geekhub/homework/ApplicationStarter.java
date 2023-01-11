package edu.geekhub.homework;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.log.analytic.LogsAnalyticService;
import edu.geekhub.homework.menu.*;
import edu.geekhub.homework.playlist.PlaylistServiceImp;
import edu.geekhub.homework.playlist.interfaces.PlaylistService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ApplicationStarter {
    private static final Path HOME_PATH = Paths.get(System.getProperty("user.home"));
    private static final Path FOLDER_TO_SAVE_PLAYLIST = HOME_PATH.resolve("Music Library");
    private static final File PLAYLIST_FILE = new File(
        Objects.requireNonNull(
            PlaylistServiceImp.class.getResource("/playlist.txt")
        ).getPath()
    );
    private static final PlaylistService playlistService = new PlaylistServiceImp(
        FOLDER_TO_SAVE_PLAYLIST,
        PLAYLIST_FILE,
        new Logger(),
        Clock.systemDefaultZone()
    );
    private static final LogsAnalyticService logsAnalyticService = new LogsAnalyticService(
        new Logger()
    );

    public static void main(String[] args) {
        Menu menu = buildMenu();

        while (true) {
            menu.run();
        }
    }

    private static Menu buildMenu() {
        List<MenuNode> menuNodes = new ArrayList<>();

        // lvl 1
        menuNodes.add(new ActionMenuNode(
            "0. Save playlist",
            new int[]{0},
            () -> {
                playlistService.saveSongsOnDrive();
                System.out.println("Success");
            }
        ));
        menuNodes.add(new PassthroughMenuNode("1. Show logs", new int[]{1}));
        menuNodes.add(new ActionMenuNode(
            "2. Exit",
            new int[]{2},
            () -> System.exit(0)
        ));

        // lvl 2
        // item 1
        menuNodes.add(new PassthroughMenuNode(
            "0. Show all logs",
            new int[]{1, 0}
        ));

        // item 1
        menuNodes.add(new PassthroughMenuNode(
            "1. Show info logs",
            new int[]{1, 1}
        ));
        menuNodes.add(new PassthroughMenuNode(
            "2. Show error logs",
            new int[]{1, 2}
        ));
        menuNodes.add(new BackMenuNode("3. Beck", new int[]{1, 3}));

        // lvl 3
        // item 1, 0
        menuNodes.add(new ActionMenuNode(
            "0. In natural order",
            new int[]{1, 0, 0},
            () -> logsAnalyticService.getAll()
                .forEach(System.out::println)
        ));
        menuNodes.add(new PassthroughMenuNode(
            "1. Sort by data",
            new int[]{1, 0, 1}
        ));

        menuNodes.add(new PassthroughMenuNode(
            "2. Sort by type",
            new int[]{1, 0, 2}
        ));
        menuNodes.add(new BackMenuNode("3. Beck", new int[]{1, 0, 3}));

        // item 1, 1
        menuNodes.add(new ActionMenuNode(
            "0. In natural order",
            new int[]{1, 1, 0},
            () -> logsAnalyticService.getFilteredLogRecords(
                    logRecord -> logRecord.type().equals(LogType.INFO)
                )
                .forEach(System.out::println)
        ));

        menuNodes.add(new PassthroughMenuNode(
            "1. Sort by data",
            new int[]{1, 1, 1}
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 1, 2}));

        // item 1, 2
        menuNodes.add(new ActionMenuNode(
            "0. In natural order",
            new int[]{1, 2, 0},
            () -> logsAnalyticService.getFilteredLogRecords(
                    logRecord -> logRecord.type().equals(LogType.ERROR)
                )
                .forEach(System.out::println)
        ));

        menuNodes.add(new PassthroughMenuNode(
            "1. Sort by data",
            new int[]{1, 2, 1}
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 2, 2}));

        // lvl 4
        // item 1, 0, 1
        menuNodes.add(new ActionMenuNode(
            "0. Starting with old records",
            new int[]{1, 0, 1, 0},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::creationTime)
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 0, 1, 1},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 0, 1, 2}));

        // item 1, 0, 2
        menuNodes.add(new ActionMenuNode(
            "0. Starting with error",
            new int[]{1, 0, 2, 0},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::type)
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with info",
            new int[]{1, 0, 2, 1},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::type).reversed()
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 0, 2, 2}));

        // item 1, 1, 1
        menuNodes.add(new ActionMenuNode(
            "0. Starting with old records",
            new int[]{1, 1, 1, 0},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getFilteredLogRecords(
                        logRecord -> logRecord.type().equals(LogType.INFO)
                    ),
                    Comparator.comparing(LogRecord::creationTime)
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 1, 1, 1},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getFilteredLogRecords(
                        logRecord -> logRecord.type().equals(LogType.INFO)
                    ),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 1, 1, 2}));

        // item 1, 2, 1
        menuNodes.add(new ActionMenuNode(
            "0. Starting with old records",
            new int[]{1, 2, 1, 0},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getFilteredLogRecords(
                        logRecord -> logRecord.type().equals(LogType.ERROR)
                    ),
                    Comparator.comparing(LogRecord::creationTime)
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 2, 1, 1},
            () -> logsAnalyticService.getSortedLogRecords(
                    logsAnalyticService.getFilteredLogRecords(
                        logRecord -> logRecord.type().equals(LogType.ERROR)
                    ),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                )
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 2, 1, 2}));

        // build
        return MenuBuilder.build(menuNodes);
    }
}
