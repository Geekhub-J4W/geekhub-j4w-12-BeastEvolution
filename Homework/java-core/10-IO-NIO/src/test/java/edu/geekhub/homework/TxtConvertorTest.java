package edu.geekhub.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TxtConvertorTest {
    TxtConvertor txtConvertor;

    @BeforeEach
    void setUp() {
        txtConvertor = new TxtConvertor();
    }

    @Test
    void test() {
        String path = "Heavy Metal|Metallica|And Justice for All|Master of Puppets";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        txtConvertor.validatePath(path.split("\\|"));
    }

    @Test
    void test1() {
        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        System.out.println(txtConvertor.getPathToFile(path));
        System.out.println();
    }

    @Test
    void test2() {
        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        System.out.println(txtConvertor.convertToPlaylistElement(path));
        System.out.println();
    }

    @Test
    void test3() {
//        String path = "укрґ | Metallica | And Justice for All | Master of Puppets | https://ia800300.us.archive.org/35/items/MetallicaMasterOfPuppets_0/02__Master_Of_Puppets_64kb.mp3";
//        String[] paths = path.split("\\|");
//        System.out.println(paths[0]);
//        Arrays.stream(paths).forEach(System.out::println);
        File path = new File("./src/main/resources/playlist.txt");
        System.out.println(txtConvertor.convertToMusic(path));
        System.out.println();
    }
}
