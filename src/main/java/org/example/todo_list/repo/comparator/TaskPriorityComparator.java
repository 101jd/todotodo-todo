package org.example.todo_list.repo.comparator;

import org.example.todo_list.domain.Priority;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueRTException;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) throws UndefinedEnumValueRTException {
        return Priority.getByName(o1.getPriority()).getValue() - Priority.getByName(o2.getPriority()).getValue();

    }
}
