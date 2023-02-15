package com.web;

import com.web.menu.ApplicationMenuBuilder;
import com.web.menu.Menu;

public class ApplicationStarter {

    public static void main(String[] args) {
        Menu menu = ApplicationMenuBuilder.buildMenu();

        while (true) {
            menu.run();
        }
    }
}
