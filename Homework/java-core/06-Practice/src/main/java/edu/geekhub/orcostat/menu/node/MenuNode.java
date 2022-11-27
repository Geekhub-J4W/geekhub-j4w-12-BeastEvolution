package edu.geekhub.orcostat.menu.node;

public class MenuNode {
    private MenuNode parent;
    private final MenuNodeCollection children;

    private final String id;
    private final int number;
    private final String name;



    public MenuNode(String id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
        children = new MenuNodeCollection();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public MenuNode getParent() {
        return parent;
    }

    public MenuNodeCollection getChildren() {
        return children;
    }

    public void setParent(MenuNode parent) {
        this.parent = parent;
    }

    public void addChild(MenuNode child) {
        children.add(child);
        child.setParent(this);
    }
}
