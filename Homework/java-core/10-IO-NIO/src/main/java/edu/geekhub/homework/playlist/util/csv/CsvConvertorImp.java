package edu.geekhub.homework.playlist.util.csv;

import edu.geekhub.homework.playlist.util.csv.interfaces.CsvConvertor;
import edu.geekhub.homework.playlist.util.csv.interfaces.CsvRecordParser;
import edu.geekhub.homework.playlist.util.file.FileValidatorImp;
import edu.geekhub.homework.playlist.util.file.interfaces.FileReader;
import edu.geekhub.homework.playlist.util.file.interfaces.FileValidator;

import java.io.File;
import java.io.IOException;

public class CsvConvertorImp implements CsvConvertor {

    private static final String SCV_EXTENSION = ".csv";
    private static final String RECORD_SEPARATOR = System.lineSeparator();
    private final FileValidator fileValidator = new FileValidatorImp();
    private final CsvRecordParser csvRecordParser = new CsvRecordParserImp();
    private final FileReader fileReader;

    public CsvConvertorImp(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public String[] getRecordFields(File inputFile, int recordIndex) {
        File csvFile = fileValidator.validateFile(inputFile, SCV_EXTENSION);
        String recordFromCsvFile = getRecordFromCsvFile(csvFile, recordIndex);
        return csvRecordParser.parseRecord(recordFromCsvFile);
    }

    private String getRecordFromCsvFile(File csvFile, int recordIndex) {
        String[] recordsFromCsvFile = getRecordsFromCsvFile(csvFile);
        try {
            return recordsFromCsvFile[recordIndex];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                String.format(
                    "Record index \"%d\" out of bound of records array(0, %d)",
                    recordIndex,
                    recordsFromCsvFile.length
                ),
                e
            );
        }
    }

    private String[] getRecordsFromCsvFile(File csvFile) {
        return convertCsvFileToString(csvFile).split(RECORD_SEPARATOR);
    }

    private String convertCsvFileToString(File csvFile) {
        try {
            return fileReader.readString(csvFile.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("File is to large");
        }
    }
}
