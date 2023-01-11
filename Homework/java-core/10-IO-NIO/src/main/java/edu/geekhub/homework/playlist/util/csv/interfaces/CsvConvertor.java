package edu.geekhub.homework.playlist.util.csv.interfaces;

import java.io.File;

public interface CsvConvertor {
    String[] getRecordFields(File inputFile, int recordIndex);
}
