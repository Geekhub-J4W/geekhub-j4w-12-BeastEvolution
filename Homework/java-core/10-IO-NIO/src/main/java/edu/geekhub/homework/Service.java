package edu.geekhub.homework;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Service {
    Path homePath = Paths.get(System.getProperty("user.home"));
    Path fullPath = homePath.resolve("");

}
