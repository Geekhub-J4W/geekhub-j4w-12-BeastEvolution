package com.web.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MenuBuilderTest {

    @Test
    @Tag("Correct")
    void Build_menu_tree_with_list_of_nods_in_random_order() {
        //Arrange
        List<MenuNode> menuNodes = new ArrayList<>();
        final MenuNode menuRoot = new PassthroughMenuNode(
            "Root",
            new int[0]
        );
        MenuNode node0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );

        MenuNode node00 = new PassthroughMenuNode(
            "0",
            new int[]{0, 0}
        );

        MenuNode buildNode0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );

        MenuNode buildNode00 = new PassthroughMenuNode(
            "0",
            new int[]{0, 0}
        );

        // lvl 2
        menuNodes.add(buildNode00);
        // lvl 1
        menuNodes.add(buildNode0);

        node0.setChildren(
            List.of(node00)
        );
        menuRoot.setChildren(
            List.of(node0)
        );
        Menu expectedMenu = new Menu(menuRoot);

        //Act
        Menu actualMenu = MenuBuilder.build(menuNodes);

        //Assert
        assertThat(actualMenu)
            .isEqualTo(expectedMenu);
    }

    @Test
    @Tag("Correct")
    void Build_menu_tree_with_list_of_nods_in_correct_order() {
        //Arrange
        List<MenuNode> menuNodes = new ArrayList<>();
        final MenuNode menuRoot = new PassthroughMenuNode(
            "Root",
            new int[0]
        );
        MenuNode node0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );

        MenuNode node00 = new PassthroughMenuNode(
            "0",
            new int[]{0, 0}
        );

        MenuNode buildNode0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );

        final MenuNode buildNode00 = new PassthroughMenuNode(
            "0",
            new int[]{0, 0}
        );

        // lvl 2
        menuNodes.add(buildNode0);
        // lvl 1
        menuNodes.add(buildNode00);

        node0.setChildren(
            List.of(node00)
        );
        menuRoot.setChildren(
            List.of(node0)
        );
        Menu expectedMenu = new Menu(menuRoot);

        //Act
        Menu actualMenu = MenuBuilder.build(menuNodes);

        //Assert
        assertThat(actualMenu)
            .isEqualTo(expectedMenu);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_build_menu_if_lvl_nods_have_gap() {
        //Arrange
        List<MenuNode> menuNodes = new ArrayList<>();
        MenuNode buildNode0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );
        MenuNode buildNode2 = new PassthroughMenuNode(
            "2",
            new int[]{2}
        );
        menuNodes.add(buildNode0);
        menuNodes.add(buildNode2);

        //Act
        //Assert
        Assertions.assertThatThrownBy(() -> MenuBuilder.build(menuNodes))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Passed nodes that have a gap between menu items on level " + 0
                + System.lineSeparator()
                + "Gap between " + 0
                + " and " + 2 + " items"
            );

    }

    @Test
    @Tag("Exception")
    void Invalid_to_build_menu_if_some_nods_have_same_location() {
        //Arrange
        List<MenuNode> menuNodes = new ArrayList<>();
        MenuNode buildNode0 = new ActionMenuNode(
            "0",
            new int[]{0},
            () -> {
            }
        );
        MenuNode buildNode0Copy = new PassthroughMenuNode(
            "0",
            new int[]{0}
        );
        menuNodes.add(buildNode0);
        menuNodes.add(buildNode0Copy);

        //Act
        //Assert
        Assertions.assertThatThrownBy(() -> MenuBuilder.build(menuNodes))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Passed nodes that have same index with some item,"
                + " equals - " + 0
                + ", on level " + 0
            );

    }
}
