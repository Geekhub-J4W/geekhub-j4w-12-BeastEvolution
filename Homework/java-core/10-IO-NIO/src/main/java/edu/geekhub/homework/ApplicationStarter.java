package edu.geekhub.homework;

public class ApplicationStarter {
    public static void main(String[] args) {
        Service service = new Service(new TxtConvertor());
        service.saveSongsOnDrive();
    }
}
