package com.example.taskmanager.controllers;

import com.example.taskmanager.models.Todo;
import com.example.taskmanager.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * Controller class responsible for handling HTTP requests related to Todo tasks.
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    /**
     * Constructs a new TodoController with the specified TodoService.
     *
     * @param todoService the TodoService to be used by the controller
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Displays the home page with a list of all Todo tasks.
     *
     * @param model the Spring MVC model
     * @return the name of the view template to render (in this case, "home")
     */
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "home";
    }

    /**
     * Displays the form for adding a new Todo task.
     *
     * @param model the Spring MVC model
     * @return the name of the view template to render (in this case, "addTask")
     */
    @GetMapping("/add")
    public String addTask(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "addTask";
    }

    /**
     * Submits a new Todo task to be saved.
     *
     * @param title     the title of the Todo task
     * @param description   the description of the Todo task
     * @param assignee  the assignee of the Todo task
     * @param urgent    indicates if the Todo task is urgent
     * @param done  indicates if the Todo task is done
     * @param deadline  the deadline of the Todo task
     * @return a redirection to the home page after adding the task
     */
    @PostMapping("/createNewTask")
    public String submitTask(String title, String description, String assignee, boolean urgent, boolean done, LocalDate deadline) {
        todoService.saveTodo(title, description, assignee, urgent, done, deadline);
        return "redirect:/todo/home";
    }

    /**
     * Displays details of a specific Todo task.
     *
     * @param id    the ID of the Todo task
     * @param model the Spring MVC model
     * @return the name of the view template to render (in this case, "todoDetails")
     */
    @GetMapping("/details/{id}")
    public String taskDetails(@PathVariable (required = true) Long id,
                              Model model) {
        model.addAttribute("todo", todoService.findById(id));
        return "todoDetails";
    }

    /**
     * Displays the form for editing a Todo task.
     *
     * @param id    the ID of the Todo task to edit
     * @param model the Spring MVC model
     * @return the name of the view template to render (in this case, "updateTask")
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(required = true) Long id,
                               Model model) {
        Optional<Todo> todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "updateTask";
    }

    /**
     * Updates an existing Todo task.
     *
     * @param id            the ID of the Todo task to update
     * @param newTodo       the updated Todo task
     * @param assigneeIds   the IDs of the assignees associated with the Todo task
     * @param newAssignee   the name of the new assignee (if any)
     * @return a redirection to the home page after updating the task
     */
    @PostMapping("/updateTask/{id}")
    public String updateTodo(@PathVariable(name="id") Long id,
                             @ModelAttribute Todo newTodo,
                             @RequestParam(required = false) List<Long> assigneeIds,
                             @RequestParam String newAssignee) {
        todoService.updateTodo(id, newTodo, assigneeIds, newAssignee);
        return "redirect:/todo/home";
    }

    /**
     * Deletes a Todo task by its ID.
     *
     * @param id    the ID of the Todo task to delete
     * @return a redirection to the home page after deleting the task
     */
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable(name="id") Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todo/home";
    }
}
