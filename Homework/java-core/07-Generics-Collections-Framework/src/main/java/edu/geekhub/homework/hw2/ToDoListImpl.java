package edu.geekhub.homework.hw2;

import edu.geekhub.homework.hw2.entity.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ToDoListImpl<E extends Task> implements ToDoList<E> {
    public static final int START_INDEX_OF_LIST = 0;
    private List<E> tasksStorage = new ArrayList<>();

    @Override
    public E getTopPriorityTask() {
        List<E> reversPriorityTasks = tasksStorage;
        reversPriorityTasks.sort(new taskPriorityComparator().reversed());
        return reversPriorityTasks.get(0);
    }

    private class taskPriorityComparator implements Comparator<E> {
        @Override
        public int compare(E o1, E o2) {
            return o1.getPriority() - o2.getPriority();
        }
    }

    @Override
    public E getTaskByIndex(int index) {
        return tasksStorage.get(index);
    }

    @Override
    public List<E> getAllTasks() {
        return tasksStorage;
    }

    @Override
    public List<E> getSortedPriorityTasks() {
        List<E> priorityTasks = new ArrayList<>(tasksStorage);

        priorityTasks.sort(new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });
        return priorityTasks;
    }

    @Override
    public List<E> getSortedByAlphabetTasks() {
        return null;
    }

    @Override
    public boolean addTaskToTheEnd(E task) {
        return tasksStorage.add(task);
    }

    @Override
    public boolean addTaskToTheStart(E task) {
        tasksStorage.add(START_INDEX_OF_LIST, task);

        return true;
    }

    @Override
    public boolean deleteTaskByIndex(E task) {
        int index = tasksStorage.indexOf(task);
        try {
            tasksStorage.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListImpl<?> toDoList = (ToDoListImpl<?>) o;
        return Objects.equals(tasksStorage, toDoList.tasksStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasksStorage);
    }
}
