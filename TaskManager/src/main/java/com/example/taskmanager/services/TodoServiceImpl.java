package com.example.taskmanager.services;

import com.example.taskmanager.dtos.TodoResponseDTO;
import com.example.taskmanager.exceptions.TodoAlreadyExistsException;
import com.example.taskmanager.exceptions.TodoNotFoundException;
import com.example.taskmanager.models.Assignee;
import com.example.taskmanager.models.Todo;
import com.example.taskmanager.repositories.AssigneeRepository;
import com.example.taskmanager.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing todo tasks.
 */
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final AssigneeRepository assigneeRepository;

    /**
     * Constructs a TodoServiceImpl with the specified repositories.
     *
     * @param todoRepository     the repository for todo tasks
     * @param assigneeRepository the repository for assignees
     */
    public TodoServiceImpl(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }

    /**
     * Retrieves all todo tasks.
     *
     * @return a list of all todo tasks
     */
    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
    /**
     * Saves a new todo task with the specified details.
     *
     * @param title         the title of the todo task
     * @param description   a detailed description of the todo task
     * @param assigneeInput a comma-separated string of assignee names for the todo task
     * @param urgent        indicates if the todo task is urgent
     * @param done          indicates if the todo task has been completed
     * @param deadline      the deadline by which the todo task should be completed
     * @throws TodoAlreadyExistsException if a todo task with the same title already exists
     */
    @Override
    public void saveTodo(String title, String description, String assigneeInput, boolean urgent, boolean done, LocalDate deadline) {
        Optional <Todo> existingTodo= todoRepository.findByTitle(title);

        if(existingTodo.isEmpty()) {
            Todo newTodo = new Todo();
            newTodo.setTitle(title);
            newTodo.setDescription(description);
            newTodo.setUrgent(urgent);
            newTodo.setDone(done);
            newTodo.setCreationDate(LocalDate.now());
            newTodo.setDeadline(deadline);

            List<Assignee> assignees = parseAssignees(assigneeInput);
            newTodo.setAssignees(assignees);

            todoRepository.save(newTodo);
        } else throw new TodoAlreadyExistsException("Todo title already exists");
    }
    /**
     * Updates an existing todo task with the specified details.
     *
     * @param id             the ID of the todo task to update
     * @param updatedTodo    the updated details of the todo task
     * @param assigneeIds    a list of IDs of assignees to be associated with the todo task
     * @param newAssigneeName the name of a new assignee to be associated with the todo task
     * @throws TodoNotFoundException if the todo task with the specified ID is not found
     */
    @Override
    public void updateTodo(Long id, Todo updatedTodo, List<Long> assigneeIds, String newAssigneeName) {
        Optional<Todo> existingTodoOpt = todoRepository.findById(id);

        if (existingTodoOpt.isPresent()) {
            Todo existingTodo = existingTodoOpt.get();

            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setDone(updatedTodo.isDone());
            existingTodo.setUrgent(updatedTodo.isUrgent());

            List<Assignee> currentAssignees = existingTodo.getAssignees();
            List<Assignee> assigneesToKeep = new ArrayList<>();

            for (Assignee assignee : currentAssignees) {
                if (assigneeIds != null && assigneeIds.contains(assignee.getId())) {
                    assigneesToKeep.add(assignee);
                }
            }

            List<Assignee> newAssignees = parseAssignees(newAssigneeName);
            for (Assignee newAssignee : newAssignees) {
                if (!assigneesToKeep.contains(newAssignee)) {
                    assigneesToKeep.add(newAssignee);
                }
            }

            existingTodo.setAssignees(assigneesToKeep);
            existingTodo.setCreationDate(updatedTodo.getCreationDate());
            existingTodo.setDeadline(updatedTodo.getDeadline());

            todoRepository.save(existingTodo);
        } else {
            throw new TodoNotFoundException("Task was not found");
        }
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(todoRepository.findById(id).get().getId());
    }

    private List<Assignee> parseAssignees(String assigneeInput) {
        List<Assignee> assignees = new ArrayList<>();
        if (assigneeInput != null && !assigneeInput.trim().isEmpty()) {
            String[] assigneeNames = assigneeInput.split(",\\s*");
            for (String name : assigneeNames) {
                Optional<Assignee> existingAssignee = assigneeRepository.findByUsername(name);
                if (existingAssignee.isPresent()) {
                    assignees.add(existingAssignee.get());
                } else {
                    Assignee newAssignee = new Assignee();
                    newAssignee.setUsername(name);
                    assigneeRepository.save(newAssignee);
                    assignees.add(newAssignee);
                }
            }
        }
        return assignees;
    }

    /**
     * Retrieves a list of todo tasks that are marked as done and converts them into DTOs.
     *
     * @return a list of TodoResponseDTO representing completed todo tasks
     */
    public List<TodoResponseDTO> getCompletedTodoDTOs() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDTO> completedTodoDTOs = new ArrayList<>();

        for (Todo todo : todos) {
            if (todo.isDone()) {
                TodoResponseDTO todoDTO = new TodoResponseDTO(todo.getId(), todo.getTitle(), todo.getDescription(),
                        todo.isUrgent(), todo.isDone(), todo.getCreationDate(), todo.getDeadline());
                completedTodoDTOs.add(todoDTO);
            }
        }

        return completedTodoDTOs;
    }

    @Override
    public Optional<Todo> findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }


}
