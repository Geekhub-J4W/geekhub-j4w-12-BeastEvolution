package edu.geekhub.homework.hw2;

import edu.geekhub.homework.hw2.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

}
