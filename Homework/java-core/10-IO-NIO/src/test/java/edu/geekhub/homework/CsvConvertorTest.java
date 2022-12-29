package edu.geekhub.homework;

import edu.geekhub.homework.playlist.util.CsvConvertor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

class CsvConvertorTest {

    @Test
    void test() {
        File file = new File("./src/main/resources/SymbolsForPathNaming.csv");
        System.out.println(file.getAbsolutePath());
        int[] values = CsvConvertor.convert(file);
        Arrays.stream(values)
            .forEach(System.out::println);
    }
}
