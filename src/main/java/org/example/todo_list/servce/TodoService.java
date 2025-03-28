package org.example.todo_list.servce;

import org.example.todo_list.exc.TaskNotFoundException;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.example.todo_list.repo.Repos;
import org.example.todo_list.repo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoList todoList = new TodoList();

    @Autowired
    private Repos repo;

    public Task createNewTask(Task task){
        todoList.add(task);
        repo.addTask(task);
        return task;

    }

    public List<Task> sortByPriority() throws UndefinedEnumValueException {
        try {
            return this.todoList.sortByPriority();
        }catch (UndefinedEnumValueRTException e) {
            throw new UndefinedEnumValueException();
        }

    }

    public List<Task> sortByStatus() throws UndefinedEnumValueException {
        try {
            return this.todoList.sortByStatus();
        }catch (UndefinedEnumValueRTException e){
            throw new UndefinedEnumValueException();
        }

    }

    public List<Task> loadHistory() throws UndefinedEnumValueRTException {

        return todoList.load(repo.getTasks());
    }

    public List<Task> getAllTasks(){
        return todoList.getTodoList();
    }

    public Task edit(Task task, String status, String priority) throws TaskNotFoundException {
        Task t = new Task(task.getId(), task.getName(), status, priority);
        repo.edit(t);
        return todoList.edit(task.getId(), task.getName(), status, priority);
    }

    public Task getBy(UUID id){
        return todoList.getBy(id);
    }

    public void clear(){
        todoList.clear();
    }
}
