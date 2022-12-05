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
    @Tag("correct work")
    @Tag("addTaskToTheEnd")
    void add_null_to_end_of_list() {
        boolean resultOFAdding = taskToDoList.addTaskToTheEnd(null);

        assertEquals(null, taskToDoList.getAllTasks().get(0));
        assertTrue(resultOFAdding);
    }


}
