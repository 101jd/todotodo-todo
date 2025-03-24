package org.example.todo_list.repo.comparator;

import org.example.todo_list.domain.Status;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueRTException;

import java.util.Comparator;

public class TaskStatusComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) throws UndefinedEnumValueRTException {

        return Status.getByName(o1.getStatus()).getValue() - Status.getByName(o2.getStatus()).getValue();

    }
}
