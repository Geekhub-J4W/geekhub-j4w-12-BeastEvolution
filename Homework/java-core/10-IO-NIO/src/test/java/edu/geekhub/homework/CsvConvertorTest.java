package edu.geekhub.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

class CsvConvertorTest {

    @Test
    void test() {
        File file = new File("./src/main/resources/SymbolsForPathNaming.csv");
        System.out.println(file.getAbsolutePath());
        int[] values = CSVConvertor.convert(file);
        Arrays.stream(values)
            .forEach(System.out::println);
    }
}
