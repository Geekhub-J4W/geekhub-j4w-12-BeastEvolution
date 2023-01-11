package edu.geekhub.homework.playlist.util.txt.interfaces;

import java.io.File;
import java.io.IOException;

public interface TxtConvertor {
String convertToString(File inputFile) throws IOException;
}
