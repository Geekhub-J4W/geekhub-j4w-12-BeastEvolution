package edu.geekhub.homework.menu;

public class Menu {
    MenuNode current;
    MenuNode menuRoot;

    protected Menu(MenuNode menuRoot) {
        this.current = menuRoot;
        this.menuRoot = menuRoot;
    }

    public void run() {
        current = current.action();
    }

}

