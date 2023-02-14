package com.web.menu;

import java.util.Scanner;

public class PassthroughMenuNode extends MenuNode {

    public PassthroughMenuNode(String name, int[] location) {
        super(name, location);
    }

    @Override
    public MenuNode action() {
        printItem();

        int indexOfNextNode = getItemIndex();
        while (isInvalidateItemIndex(indexOfNextNode)) {
            System.out.println("You enter wrong menu item number.\nTry again:");
            indexOfNextNode = getItemIndex();
        }
        return this.children.get(indexOfNextNode);
    }

    private void printItem() {
        this.children.stream()
            .map(MenuNode::getName)
            .forEach(System.out::println);
    }

    private int getItemIndex() {
        Scanner scanner = new Scanner(System.in);
        while (!(scanner.hasNextInt())) {
            System.out.println("You enter not a menu item number.\nTry again:");
            scanner.nextLine();
        }

        return scanner.nextInt();
    }

    private boolean isInvalidateItemIndex(int itemIndex) {
        return itemIndex < 0 || itemIndex > this.children.size() - 1;
    }

    @Override
    public MenuNode clone() {
        return new PassthroughMenuNode(
            this.name,
            this.location
        );
    }
}
