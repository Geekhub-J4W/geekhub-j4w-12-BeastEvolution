package edu.geekhub.homework.user.input;

import java.util.Scanner;

public class TakerInputFromUser {

    private TakerInputFromUser() {}

    public static String getInputFormUser(String massageToUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(massageToUser);

        return scanner.nextLine();
    }
}
