package org.example.todo_list.controller;

import org.example.todo_list.exc.TaskNotFoundException;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.example.todo_list.servce.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/tasks")
    public String getAll(Model modelMap){
        modelMap.addAttribute("task", new Task());
        modelMap.addAttribute("tasks", service.getAllTasks());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("task", service.createNewTask(task));
        model.addAttribute("tasks", List.of());
        return "redirect:/tasks";
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam String id,
                       @RequestParam String name,
                       @RequestParam String status,
                       @RequestParam String priority,
                       Model model){

        Task t;
        try {
            t = service.edit(service.getBy(UUID.fromString(id)), status, priority);
        }catch (TaskNotFoundException e){
            return "errorpage";
        }


        model.addAttribute("task", t);
        return "redirect:/tasks";
    }

    @GetMapping("/sortst")
    public String sortByStatus(Model model){
        model.addAttribute("task", new Task());
        try {
            model.addAttribute("tasks", service.sortByStatus());
        }catch (UndefinedEnumValueException e){
            return "valueerror";
        }

        return "tasks";
    }

    @GetMapping("/sortpr")
    public String sortByPriority(Model model){
        model.addAttribute("task", new Task());
        try {
            model.addAttribute("tasks", service.sortByPriority());
        } catch (UndefinedEnumValueException e) {
            return "valueerror";
        }
        return "tasks";
    }

    @GetMapping("/load")
    public String load(Model model){
        model.addAttribute("task", new Task());
        try {
            model.addAttribute("tasks", service.loadHistory());
        } catch (UndefinedEnumValueRTException e) {
            e.printStackTrace();
            return "valueerror";
        }
        return "tasks";
    }

    @GetMapping("/clear")
    public String clear(){
        service.clear();
        return "redirect:/tasks";
    }
}

