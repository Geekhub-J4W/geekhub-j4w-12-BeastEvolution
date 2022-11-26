package edu.geekhub.orcostat.menu.options;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MenuNodeCollection {
    MenuNode[] menuNodes;

    public MenuNodeCollection() {
        menuNodes = new MenuNode[0];
    }

    public void add(MenuNode menuNode) {
        if (Objects.isNull(menuNode)) {
            throw new IllegalArgumentException("MenuNode cannot be null");
        }

        menuNodes = Arrays.copyOf(menuNodes, menuNodes.length + 1);
        menuNodes[menuNodes.length - 1] = menuNode;
    }

    public int length() {
        return menuNodes.length;
    }

    public MenuNode get(int index) {
        return menuNodes[index];
    }

    public MenuNode getByNumber(int number) {
        for (MenuNode node : menuNodes) {
            if (node.getNumber() == number) {
                return node;
            }
        }

        throw new NoSuchElementException();
    }

    public MenuNode[] getAll() {
        return menuNodes;
    }
}
