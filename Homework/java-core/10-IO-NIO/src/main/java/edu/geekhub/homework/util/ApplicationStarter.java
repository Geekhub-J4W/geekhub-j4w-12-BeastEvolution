package edu.geekhub.homework.util;

import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.PlaylistConvertor;
import edu.geekhub.homework.playlist.PlaylistService;

public class ApplicationStarter {
    public static void main(String[] args) {
        PlaylistService playlistService = new PlaylistService(new PlaylistConvertor());
        playlistService.saveSongsOnDrive();
        Logger logger = new Logger();
        logger.getLogs().stream().forEach(System.out::println);

//        System.out.println("1. ");
//        Scanner scanner = new Scanner(System.in);
//        int userResponse = scanner.nextInt();
//        switch (userResponse) {
//
//        }
    }
}
