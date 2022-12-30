package edu.geekhub.homework.util;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.log.analytic.LogsAnalyticService;
import edu.geekhub.homework.menu.*;
import edu.geekhub.homework.playlist.PlaylistConvertor;
import edu.geekhub.homework.playlist.PlaylistService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApplicationStarter {

    private static PlaylistService playlistService = new PlaylistService(new PlaylistConvertor());
    private static LogsAnalyticService logsAnalyticService = new LogsAnalyticService(new Logger());

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
            () -> playlistService.saveSongsOnDrive()
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
            () -> logsAnalyticService.getAll().stream()
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
            () -> logsAnalyticService.filterLogRecords(
                    logRecord -> logRecord.type().equals(LogType.INFO)
                ).stream()
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
            () -> logsAnalyticService.filterLogRecords(
                    logRecord -> logRecord.type().equals(LogType.ERROR)
                ).stream()
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
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::creationTime)
                    ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 0, 1, 1},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 0, 1, 2}));

        // item 1, 0, 2
        menuNodes.add(new ActionMenuNode(
            "0. Starting with error",
            new int[]{1, 0, 2, 0},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::type)
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with info",
            new int[]{1, 0, 2, 1},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.getAll(),
                    Comparator.comparing(LogRecord::type).reversed()
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 0, 2, 2}));

        // item 1, 1, 1
        menuNodes.add(new ActionMenuNode(
            "0. Starting with old records",
            new int[]{1, 1, 1, 0},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.filterLogRecords(
                        logRecord -> logRecord.type().equals(LogType.INFO)
                    ),
                    Comparator.comparing(LogRecord::creationTime)
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 1, 1, 1},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.filterLogRecords(
                        logRecord -> logRecord.type().equals(LogType.INFO)
                    ),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 1, 1, 2}));

        // item 1, 2, 1
        menuNodes.add(new ActionMenuNode(
            "0. Starting with old records",
            new int[]{1, 2, 1, 0},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.filterLogRecords(
                        logRecord -> logRecord.type().equals(LogType.ERROR)
                    ),
                    Comparator.comparing(LogRecord::creationTime)
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Starting with new records",
            new int[]{1, 2, 1, 1},
            () -> logsAnalyticService.sortedLogRecords(
                    logsAnalyticService.filterLogRecords(
                        logRecord -> logRecord.type().equals(LogType.ERROR)
                    ),
                    Comparator.comparing(LogRecord::creationTime).reversed()
                ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("2. Beck", new int[]{1, 2, 1, 2}));

        // build
        return MenuBuilder.build(
            menuNodes
        );
    }
}
