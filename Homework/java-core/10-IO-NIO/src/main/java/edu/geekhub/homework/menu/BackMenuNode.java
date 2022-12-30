package edu.geekhub.homework.menu;

public class BackMenuNode extends MenuNode {
    public BackMenuNode(String name, int[] location) {
        super(name, location);
    }

    @Override
    public MenuNode action() {
        return this.getParent().getParent();
    }
}
