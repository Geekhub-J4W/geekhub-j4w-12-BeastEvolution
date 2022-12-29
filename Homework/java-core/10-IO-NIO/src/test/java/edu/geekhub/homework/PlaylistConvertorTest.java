package edu.geekhub.homework;

import edu.geekhub.homework.log.Logger;
import edu.geekhub.homework.playlist.PlaylistConvertor;
import edu.geekhub.homework.util.ApplicationStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;

class PlaylistConvertorTest {
    PlaylistConvertor playlistConvertor;

    @BeforeEach
    void setUp() {
        playlistConvertor = new PlaylistConvertor();
    }

    @Test
    void test() {
        String path = "Heavy Metal|Metallica|And Justice for All|Master of Puppets";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        playlistConvertor.validatePath(path.split("\\|"));
    }

    @Test
    void test1() {
        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
//        System.out.println(playlistConvertor.getPathToFile(path));
        System.out.println();
    }

    @Test
    void test2() {
        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
//        System.out.println(txtConvertor.convertToPlaylistElement(path));
//        System.out.println();
//        ClassLoader cl = ApplicationStarter.class.getClassLoader();
//        System.out.println(cl.getResource("SymbolsForPathNaming.csv"));
        System.out.println(ApplicationStarter.class.getResource("/log.txt"));
    }

    @Test
    void test3() {
//        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        File path = new File("./src/main/resources/playlist.txt");
        System.out.println(playlistConvertor.convertToPlaylist(path));
        System.out.println();
    }

    @Test
    void test4() throws URISyntaxException {
//        String path = String.format("%s/%s", System.getProperty("user.dir"), this.getClass().getPackage().getName().replace(".", "/"));
//        System.out.println(path);
//        System.out.println(System.getProperty("user.dir"));
//        URL localPackage = ApplicationStarter.class.getResource("");
////        URL urlLoader = ApplicationStarter.class.getProtectionDomain().getCodeSource().getLocation();
//////        String localDir = localPackage.getFile();
//////        String loaderDir = urlLoader.getFile();
//        Path path = Paths.get(localPackage.toURI());
////        path = path.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
//////        System.out.printf("loaderDir = %s\n localDir = %s\n", loaderDir, localDir);
//        System.out.println(path.toFile());
//        Path path1 = path.subpath(0, path.toAbsolutePath().getNameCount() - 7);
//        System.out.println(path1.toFile());
////        System.out.println(System.getProperty(path);
//        System.out.println(System.getProperty("user.home"));
//        URL localPackage = Logger.class.getResource("");
//        Path path = Paths.get(localPackage.toURI());
//        Path path1 = path.subpath(0, path.toAbsolutePath().getNameCount() - 8).resolve("src/main/resources/log.txt");
//        System.out.println(path1);
        URL localPackage = Logger.class.getResource("");
        File file = new File(localPackage.getPath());
        while (!file.getName().equals("10-IO-NIO")) {
            file = new File(file.getParent());
        }
        System.out.println(file.toPath().resolve("src/main/resources/log.txt").toFile());

        try (FileInputStream fis = new FileInputStream(file.toPath().resolve("src/main/resources/log.txt").toFile())) {
            if (fis.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    System.out.println(ois.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}
