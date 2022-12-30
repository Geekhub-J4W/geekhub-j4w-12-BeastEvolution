package edu.geekhub.homework.menu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MenuBuilder {
    public static final MenuNode menuRoot = new PassthroughMenuNode(
        "Root",
        new int[0]
    );
    private static final int ROOT_LEVEL = 0;

    public static Menu build(List<MenuNode> menuNodes) {
        int currentLevel = ROOT_LEVEL + 1;

        while (isLevelExist(currentLevel, menuNodes)) {
            buildLevel(getLevelNodesInRightOrder(currentLevel, menuNodes));
            currentLevel++;
        }

        return new Menu(menuRoot);
    }

    private static boolean isLevelExist(int level, List<MenuNode> allNodes) {
        return allNodes.stream()
            .filter(node -> isNodeFromLevel(level, node))
            .toList()
            .size() > 0;
    }
    private static List<MenuNode> getLevelNodesInRightOrder(int level, List<MenuNode> allNodes) {
        return allNodes.stream()
            .filter(node -> isNodeFromLevel(level, node))
            .sorted(Comparator.comparingInt(MenuNode::getIndex))
            .toList();
    }

    public static boolean isNodeFromLevel(int level, MenuNode node) {
        return node.getLocation().length == level;
    }

    private static void buildLevel(List<MenuNode> levelNodes) {
        levelNodes.stream()
            .forEach(MenuBuilder::addNodeToTree);
    }
//    private static void addBeckNodeToLevel() {
//
//    }

    private static void addNodeToTree(MenuNode menuNode) {
        MenuNode parentNode = getParentNode(menuNode);

        if (parentNode.getChildren().size() < menuNode.getIndex()) {
            throw new IllegalArgumentException(
                "Passed nodes that have a gap between menu items on level " + parentNode.getLevel()
                    + System.lineSeparator()
                    + "Gap between " + parentNode.getChildren().size()
                    + " and " + menuNode.getIndex() + " items"
            );
        } else if (parentNode.getChildren().size() > menuNode.getIndex()) {
            throw new IllegalArgumentException(
                "Passed nodes that have same index with some item,"
                    + " equals - " + menuNode.getIndex()
                    + ", on level " + menuNode.getLevel()
            );
        }

        parentNode.getChildren().add(menuNode);
        menuNode.setParent(parentNode);
    }

    private static MenuNode getParentNode(MenuNode menuNode) {
        MenuNode currentNode = menuRoot;
        int[] pathToParentNode = getPathToParentNode(menuNode.getLocation());

        for (int pathIndex : pathToParentNode) {
            currentNode = currentNode.getChildren().get(pathIndex);
        }

        return currentNode;
    }

    private static int[] getPathToParentNode(int[] pathToNode) {
        return Arrays.copyOf(pathToNode, pathToNode.length - 1);
    }
}
