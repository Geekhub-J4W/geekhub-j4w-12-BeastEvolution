package edu.geekhub.homework.util;

import edu.geekhub.homework.log.LogRecord;
import edu.geekhub.homework.log.LogType;
import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.log.analytic.LogsAnalyticService;
import edu.geekhub.homework.menu.*;
import edu.geekhub.homework.playlist.PlaylistConvertor;
import edu.geekhub.homework.playlist.PlaylistService;

import java.util.ArrayList;
import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {
        PlaylistService playlistService = new PlaylistService(new PlaylistConvertor());
        LogsAnalyticService logsAnalyticService = new LogsAnalyticService(new Logger());

        List<MenuNode> menuNodes = new ArrayList<>();
        menuNodes.add(new ActionMenuNode(
            "0. Save playlist",
            new int[]{0},
            () -> playlistService.saveSongsOnDrive()
            ));
        menuNodes.add(new PassthroughMenuNode("1. Show logs", new int[]{1}));

        menuNodes.add(new ActionMenuNode(
            "0. Show all logs",
            new int[]{1, 0},
            () -> logsAnalyticService.getAll().stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "1. Show info logs",
            new int[]{1, 1},
            () -> logsAnalyticService.filterLogRecords(
                logRecord -> logRecord.type().equals(LogType.INFO)
            ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new ActionMenuNode(
            "2. Show error logs",
            new int[]{1, 2},
            () -> logsAnalyticService.filterLogRecords(
                logRecord -> logRecord.type().equals(LogType.ERROR)
            ).stream()
                .forEach(System.out::println)
        ));
        menuNodes.add(new BackMenuNode("3. Beck", new int[]{1, 3}));


        Menu menu = MenuBuilder.build(
            menuNodes
        );

        while (true) {
            menu.run();
        }

//        System.out.println("1. ");
//        Scanner scanner = new Scanner(System.in);
//        int userResponse = scanner.nextInt();
//        switch (userResponse) {
//
//        }
    }
}
