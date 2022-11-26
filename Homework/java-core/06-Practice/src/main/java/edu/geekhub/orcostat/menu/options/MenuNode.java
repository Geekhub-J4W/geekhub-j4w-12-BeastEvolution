package edu.geekhub.orcostat.menu.options;

public class MenuNode {
    private MenuNode parent;
    private MenuNodeCollection children;

    private String id;
    private int number;
    private String name;



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
