package edu.geekhub.homework;

import edu.geekhub.homework.playlist.CsvConvertorImp;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

class CsvConvertorImpTest {

    @Test
    void test() {
        File file = new File("./src/main/resources/SymbolsForPathNaming.csv");
        System.out.println(file.getAbsolutePath());
        int[] values = CsvConvertorImp.convert(file);
        Arrays.stream(values)
            .forEach(System.out::println);
    }
}
