package edu.geekhub.homework.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuNode {
    String name;
    MenuNode parent;
    List<MenuNode> children;
    int[] location;

    public MenuNode(String name, int[] location) {
        this.name = name;
        this.location = location;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public MenuNode getParent() {
        return parent;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public int[] getLocation() {
        return location;
    }

    public int getIndex() {
        return location[location.length - 1];
    }

    public int getLevel() {
        return location.length;
    }

    protected void setParent(MenuNode parent) {
        this.parent = parent;
    }

    public abstract MenuNode action();
}
