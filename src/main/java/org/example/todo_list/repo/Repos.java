package org.example.todo_list.repo;

import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;

import java.util.List;
import java.util.UUID;

public interface Repos {
    boolean addTask(Task task);
    List<Task> getTasks();
    void edit(Task task);
    Task getBy(UUID id) throws UndefinedEnumValueException;
}
