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

//        ClassLoader cl = ApplicationStarter.class.getClassLoader();
//        System.out.println(cl.getResource("./"));
//        System.out.println(ApplicationStarter.class.getResource("/"));

//        String path = String.format("%s/%s", System.getProperty("user.dir"), ApplicationStarter.class.getPackage().getName().replace(".", "/"));
//        System.out.println(path);
//        System.out.println(System.getProperty("user.dir"));
    }
}
