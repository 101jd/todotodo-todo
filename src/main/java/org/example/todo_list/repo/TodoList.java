package org.example.todo_list.repo;

import lombok.Getter;
import lombok.SneakyThrows;
import org.example.todo_list.exc.TaskNotFoundException;
import org.example.todo_list.domain.Priority;
import org.example.todo_list.domain.Status;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.example.todo_list.repo.comparator.TaskPriorityComparator;
import org.example.todo_list.repo.comparator.TaskStatusComparator;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
public class TodoList {
    private List<Task> todoList;
    private List<Task> history;

    public TodoList(){
        this.todoList = new LinkedList<>();
        this.history = new LinkedList<>();
    }

    public List<Task> load(List<Task> todoList){
        for (Task task : todoList){
            if (!this.history.contains(task))
                this.history.add(task);
        }
        return this.history;
    }

    public boolean add(Task task){
        return todoList.add(task);
    }


    @SneakyThrows
    public List<Task> sortByPriority() throws UndefinedEnumValueRTException{
        return todoList.stream().sorted(this::byPriority).toList();
    }

    @SneakyThrows
    public List<Task> sortByStatus() throws UndefinedEnumValueRTException{
        return todoList.stream().sorted(this::byStatus).toList();
    }

    public Task edit(UUID id, String name, String status, String priority) throws TaskNotFoundException {

        Task task = todoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElse(null);
        if (task != null){
            task.setName(name);
            task.setStatus(Status.getByName(status));
            task.setPriority(Priority.getByName(priority));
        } else {
            throw new TaskNotFoundException();
        }

        return task;
    }


    public int byPriority(Task o1, Task o2) throws UndefinedEnumValueRTException {

            return new TaskPriorityComparator().compare(o1, o2);


    }

    public int byStatus(Task o1, Task o2) throws UndefinedEnumValueRTException {

            return new TaskStatusComparator().compare(o1, o2);

    }

    public Task getBy(UUID id){
        return todoList.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public void clear(){
        this.todoList.clear();
    }
}
