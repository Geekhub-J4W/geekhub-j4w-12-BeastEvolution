package edu.geekhub.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
