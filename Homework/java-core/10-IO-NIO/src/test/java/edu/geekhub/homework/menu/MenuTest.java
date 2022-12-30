package edu.geekhub.homework.menu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void test() {
        List<MenuNode> menuNodes = new ArrayList<>();
        menuNodes.add(new PassthroughMenuNode("0", new int[]{0}));
        menuNodes.add( new PassthroughMenuNode("1", new int[]{1}));
        menuNodes.add(new PassthroughMenuNode("2", new int[]{2}));
        menuNodes.add(new ActionMenuNode("0", new int[]{0, 0}, () -> System.out.println("run")));
        menuNodes.add(new ActionMenuNode("0", new int[]{0, 0}, () -> System.out.println("run")));
        Menu menu = MenuBuilder.build(
            menuNodes
        );
//        System.out.println(MenuBuilder.isNodeFromLevel(1, new PassthroughMenuNode("1", new int[]{1})));
    }
//new ActionMenuNode("0", new int[]{0, 0}, () -> System.out.println("run")
}
