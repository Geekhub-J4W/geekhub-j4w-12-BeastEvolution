package edu.geekhub.orcostat.menu;

import edu.geekhub.orcostat.menu.options.MenuNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {
    Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void can_find_node_by_id() {
        MenuNode result = menu.dfs("1", menu.treeRoot);
        System.out.println(result.getName());

    }

    @Test
    void can_print_options() {
        menu.printCurrentOptions();
    }

}
