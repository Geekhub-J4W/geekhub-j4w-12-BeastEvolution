package com.web.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public void setParent(MenuNode parent) {
        this.parent = parent;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public abstract MenuNode action();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuNode menuNode = (MenuNode) o;
        return Objects.equals(name, menuNode.name)
            && Objects.equals(children, menuNode.children)
            && Arrays.equals(location, menuNode.location);
    }


    public abstract MenuNode clone();
}
