package edu.geekhub.homework.hw2;

import edu.geekhub.homework.hw2.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListImplTest {

    ToDoListImpl<Task> taskToDoList;

    @BeforeEach
    void setUp() {
        taskToDoList = new ToDoListImpl<>();
    }

    @Test
    @Tag("correct work")
    @Tag("addTaskToTheEnd")
    void add_task_to_end_of_list() {
        Task lastTaskInList = new Task("Task2", "Text", 2);
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 1));
        boolean resultOFAdding = taskToDoList.addTaskToTheEnd(lastTaskInList);

        assertEquals(lastTaskInList, taskToDoList.getAllTasks().get(1));
        assertTrue(resultOFAdding);
    }

    @Test
    @Tag("null")
    @Tag("addTaskToTheEnd")
    void add_null_to_end_of_list() {
        boolean resultOFAdding = taskToDoList.addTaskToTheEnd(null);

        assertEquals(null, taskToDoList.getAllTasks().get(0));
        assertTrue(resultOFAdding);
    }

    @Test
    @Tag("correct work")
    @Tag("equals")
    void comparison_two_equivalent_list() {
        ToDoListImpl<Task> taskToDoList1 = new ToDoListImpl<>();
        ToDoListImpl<Task> taskToDoList2 = new ToDoListImpl<>();

        taskToDoList1.addTaskToTheEnd(new Task("Task", "Text", 1));
        taskToDoList2.addTaskToTheEnd(new Task("Task", "Text", 1));

        assertTrue(taskToDoList1.equals(taskToDoList2));
    }

    @Test
    @Tag("correct work")
    @Tag("hashCode")
    void comparison_hash_code_of_two_equivalent_list() {
        ToDoListImpl<Task> taskToDoList1 = new ToDoListImpl<>();
        ToDoListImpl<Task> taskToDoList2 = new ToDoListImpl<>();

        taskToDoList1.addTaskToTheEnd(new Task("Task", "Text", 1));
        taskToDoList2.addTaskToTheEnd(new Task("Task", "Text", 1));

        assertEquals(taskToDoList1.hashCode(), taskToDoList2.hashCode());
    }

    @Test
    @Tag("correct work")
    @Tag("getAllTasks")
    void get_all_tasks() {
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 1));

        List<Task> tasksStorage = new ArrayList<>();
        tasksStorage.add(new Task("Task", "Text", 1));

        assertEquals(taskToDoList.getAllTasks(), tasksStorage);
    }

    @Test
    @Tag("null")
    @Tag("getAllTasks")
    void get_list_with_null_elements() {
        taskToDoList.addTaskToTheEnd(null);

        List<Task> tasksStorage = new ArrayList<>();
        tasksStorage.add(null);

        assertEquals(taskToDoList.getAllTasks(), tasksStorage);
    }

    @Test
    @Tag("correct work")
    @Tag("getSortedPriorityTasks")
    void get_sorted_tasks_by_priority() {
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", -7));
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 3));
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 0));

        List<Task> expectedSortedPriorityTasks = new ArrayList<>();
        expectedSortedPriorityTasks.add(new Task("Task", "Text", -7));
        expectedSortedPriorityTasks.add(new Task("Task", "Text", 0));
        expectedSortedPriorityTasks.add(new Task("Task", "Text", 3));


        List<Task> actualSortedPriorityTasks = taskToDoList.getSortedPriorityTasks();

        assertEquals(expectedSortedPriorityTasks, actualSortedPriorityTasks);
    }

    @Test
    @Tag("correct work")
    @Tag("getSortedPriorityTasks")
    void get_sorted_tasks_by_priority_with_negative_number() {
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", -7));
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", -3));

        List<Task> expectedSortedPriorityTasks = new ArrayList<>();
        expectedSortedPriorityTasks.add(new Task("Task", "Text", -7));
        expectedSortedPriorityTasks.add(new Task("Task", "Text", -3));


        List<Task> actualSortedPriorityTasks = taskToDoList.getSortedPriorityTasks();

        assertEquals(expectedSortedPriorityTasks, actualSortedPriorityTasks);
    }

    @Test
    @Tag("correct work")
    @Tag("getSortedPriorityTasks")
    void get_sorted_tasks_by_priority_with_positive_number() {
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 7));
        taskToDoList.addTaskToTheEnd(new Task("Task", "Text", 3));

        List<Task> expectedSortedPriorityTasks = new ArrayList<>();
        expectedSortedPriorityTasks.add(new Task("Task", "Text", 3));
        expectedSortedPriorityTasks.add(new Task("Task", "Text", 7));


        List<Task> actualSortedPriorityTasks = taskToDoList.getSortedPriorityTasks();

        assertEquals(expectedSortedPriorityTasks, actualSortedPriorityTasks);
    }

    @Test
    @Tag("correct work")
    @Tag("getSortedPriorityTasks")
    void get_sorted_tasks_without_changing_positions_of_equivalent_tasks() {
        taskToDoList.addTaskToTheEnd(new Task("Task1", "Text1", 3));
        taskToDoList.addTaskToTheEnd(new Task("Task2", "Text2", 3));
        taskToDoList.addTaskToTheEnd(new Task("Task3", "Text3", 3));

        List<Task> expectedSortedPriorityTasks = new ArrayList<>();
        expectedSortedPriorityTasks.add(new Task("Task1", "Text1", 3));
        expectedSortedPriorityTasks.add(new Task("Task2", "Text2", 3));
        expectedSortedPriorityTasks.add(new Task("Task3", "Text3", 3));


        List<Task> actualSortedPriorityTasks = taskToDoList.getSortedPriorityTasks();

        assertEquals(expectedSortedPriorityTasks, actualSortedPriorityTasks);
    }

    @Test
    @Tag("correct work")
    @Tag("getSortedPriorityTasks")
    void get_top_priority_task() {
        taskToDoList.addTaskToTheEnd(new Task("Task1", "Text1", -3));

        Task expectedFirstTopPriorityTask = new Task("Task2", "Text2", 7);
        taskToDoList.addTaskToTheEnd(expectedFirstTopPriorityTask);

        taskToDoList.addTaskToTheEnd(new Task("Task3", "Text3", 7));
        taskToDoList.addTaskToTheEnd(new Task("Task4", "Text4", 0));


        Task actualFirstTopPriorityTask = taskToDoList.getTopPriorityTask();

        assertEquals(expectedFirstTopPriorityTask, actualFirstTopPriorityTask);
    }

    @Test
    @Tag("correct work")
    @Tag("getTaskByIndex")
    void get_task_by_index() {
        taskToDoList.addTaskToTheEnd(new Task("Task1", "Text1", -3));

        Task expectedTask = new Task("Task2", "Text2", 7);
        taskToDoList.addTaskToTheEnd(expectedTask);

        Task actualTask = taskToDoList.getTaskByIndex(1);

        assertEquals(expectedTask, actualTask);
    }

    @Test
    @Tag("error")
    @Tag("getTaskByIndex")
    void false_get_task_incorrect_index() {
        taskToDoList.addTaskToTheEnd(new Task("Task1", "Text1", -3));

        assertThrows(IndexOutOfBoundsException.class, () -> taskToDoList.getTaskByIndex(-1));
    }

}
