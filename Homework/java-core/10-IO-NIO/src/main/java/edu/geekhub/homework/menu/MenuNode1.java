package edu.geekhub.homework.menu;

import java.util.List;

public interface MenuNode1 {
    public String getName();

    public MenuNode getParent();

    public List<MenuNode> getChildren();

    public int[] getLocation();

    public int getIndex();

    public int getLevel();

    public void action();
}
