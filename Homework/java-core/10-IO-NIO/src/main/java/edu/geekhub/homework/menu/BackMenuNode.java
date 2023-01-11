package edu.geekhub.homework.menu;

public class BackMenuNode extends MenuNode {
    public BackMenuNode(String name, int[] location) {
        super(name, location);
    }

    @Override
    public MenuNode action() {
        return this.getParent().getParent();
    }

    @Override
    public MenuNode clone() {
        return new BackMenuNode(
            this.name,
            this.location
        );
    }
}
