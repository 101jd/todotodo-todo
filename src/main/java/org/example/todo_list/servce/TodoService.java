package org.example.todo_list.servce;

import org.example.todo_list.exc.TaskNotFoundException;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.example.todo_list.repo.Repo;
import org.example.todo_list.repo.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoList todoList = new TodoList();

    @Autowired
    private Repo repo;

    public Task createNewTask(Task task){
        todoList.add(task);
        repo.addTask(task);
        return task;

    }

//    public Task changePriority(Task task, Priority priority){
//        task.setPriority(priority);
//        return task;
//    }
//
//    public Task changeStatus(Task task, Status status){
//        task.setStatus(status);
//        return task;
//    }

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

    public List<Task> load() throws UndefinedEnumValueRTException {
        todoList.load(repo.getTasks());
        return todoList.getTodoList();
    }

    public List<Task> getAllTasks(){
        return todoList.getTodoList();
    }

    public Task edit(Task task, String status, String priority) throws TaskNotFoundException {
        repo.edit(task.getId(), task.getName(), status, priority);
        return todoList.edit(task.getId(), task.getName(), status, priority);
    }

    public Task getBy(UUID id){
        return todoList.getBy(id);
    }
}
