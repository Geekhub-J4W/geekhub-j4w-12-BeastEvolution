package edu.geekhub.homework.menu;

public class ActionMenuNode extends MenuNode {
    Runnable runnable;

    public ActionMenuNode(String name, int[] location, Runnable runnable) {
        super(name, location);
        this.runnable = runnable;
    }

    @Override
    public MenuNode action() {
        runnable.run();

        return this.parent;
    }
}
