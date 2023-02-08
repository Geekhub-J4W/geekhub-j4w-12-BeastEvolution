package edu.geekhub.homework;

import edu.geekhub.homework.inject.InjectProcessor;


public class ApplicationStarter {
    public static void main(String[] args){
        GeekHubCourse geekHubCourse = new GeekHubCourse();

        InjectProcessor injectProcessor = new InjectProcessor("application.properties");
        injectProcessor.process(geekHubCourse);

        System.out.println(geekHubCourse);
    }
}
