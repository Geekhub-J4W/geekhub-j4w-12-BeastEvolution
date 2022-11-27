package edu.geekhub.orcostat.menu;

import edu.geekhub.orcostat.exceptions.IllegalOptionException;
import edu.geekhub.orcostat.menu.node.ActionMenuNode;
import edu.geekhub.orcostat.menu.node.BeckMenuNode;
import edu.geekhub.orcostat.menu.node.InterimMenuNode;
import edu.geekhub.orcostat.menu.node.MenuNode;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Menu {
    MenuNode treeRoot;
    private MenuNode currentNode;

    public Menu() {
        treeRoot = new InterimMenuNode("0", 0, "");
        currentNode = treeRoot;
        buildMenu();
    }

    public void printCurrentOptions() {
        for (MenuNode node : currentNode.getChildren().getAll()) {
            System.out.println(node.getName());
        }
    }

    public String moveThroughMenuNodes(int nodeNumber) {
        MenuNode nextNode;
        try {
            nextNode = currentNode.getChildren().getByNumber(nodeNumber);
        } catch (NoSuchElementException e) {
            throw new IllegalOptionException(
                "You have selected a menu option that is not allowed",
                e
            );
        }

        if (nextNode instanceof InterimMenuNode) {
            nextNode(nodeNumber);
            return "";
        }

        if (nextNode instanceof BeckMenuNode) {
            previousNode();
            return "";
        }

        if (nextNode instanceof ActionMenuNode) {
            return executeCommand(nextNode);
        }
        return "";
    }

    private void buildMenu() {
        buildFirstBrunch();
        buildSecondBrunch();
        buildFourthBrunch();
        buildFifthBrunch();
        buildSixthBrunch();
    }

    private void buildFirstBrunch() {
        treeRoot.addChild(
            new InterimMenuNode("1", 1, "1. Add orc")
        );

        dfs("1", treeRoot).addChild(
            new ActionMenuNode("1.1", 1, "1. Add ordinal orc")
        );
        dfs("1", treeRoot).addChild(
            new ActionMenuNode("1.2", 2, "2. Add officer orc")
        );
        dfs("1", treeRoot).addChild(
            new BeckMenuNode("1.3", 3, "3. Go beck")
        );
    }

    private void buildSecondBrunch() {
        treeRoot.addChild(
            new InterimMenuNode("2", 2, "2. Add tank")
        );

        dfs("2", treeRoot).addChild(
            new ActionMenuNode("2.1", 1, "1. Add tank without equipage")
        );
        dfs("2", treeRoot).addChild(
            new ActionMenuNode("2.2", 2, "2. Add tank with equipage")
        );
        dfs("2", treeRoot).addChild(
            new BeckMenuNode("2.3", 3, "3. Go beck")
        );
    }

    private void buildFourthBrunch() {
        treeRoot.addChild(
            new ActionMenuNode("4", 4, "4. Show money damage")
        );

    }

    private void buildFifthBrunch() {
        treeRoot.addChild(
            new ActionMenuNode("5", 5, "5. Show statistic")
        );

    }

    private void buildSixthBrunch() {
        treeRoot.addChild(
            new ActionMenuNode("6", 6, "6. Exit")
        );

    }

    private void nextNode(int nodeNumber) {
        currentNode = currentNode.getChildren().getByNumber(nodeNumber);

    }

    private void previousNode() {
        currentNode = currentNode.getParent();
    }

    private String executeCommand(MenuNode nextNode) {
        currentNode = treeRoot;

        return nextNode.getId();
    }

    public MenuNode dfs(String id, MenuNode menuNode) {
        if (Objects.isNull(menuNode)) {
            throw new NoSuchElementException();
        } else {
            if (menuNode.getId().equals(id)) {
                return menuNode;
            }

            MenuNode result = null;
            for (MenuNode node : menuNode.getChildren().getAll()) {
                try {
                    return result = dfs(id, node);
                } catch (NoSuchElementException e) {

                }
            }
            if(result == null) {
                throw new NoSuchElementException();
            }

            return result;
        }
    }
}


