package com.web.menu;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(current, menu.current) && Objects.equals(menuRoot, menu.menuRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, menuRoot);
    }
}

