package edu.geekhub.homework.menu;

import java.util.Scanner;

public class PassthroughMenuNode extends MenuNode {

    public PassthroughMenuNode(String name, int[] location) {
        super(name, location);
    }

    @Override
    public MenuNode action() {
        printItem();
        Scanner scanner = new Scanner(System.in);
        int indexOfNextNode = scanner.nextInt();
        return this.children.get(indexOfNextNode);
    }

    private void printItem() {
        this.children.stream()
            .map(MenuNode::getName)
            .forEach(System.out::println);
    }
}
