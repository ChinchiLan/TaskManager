package com.example.taskmanager;

import com.example.taskmanager.models.Assignee;
import com.example.taskmanager.models.Todo;
import com.example.taskmanager.repositories.AssigneeRepository;
import com.example.taskmanager.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class TaskManagerApplication implements CommandLineRunner {

	private final TodoRepository todoRepository;

	private final AssigneeRepository assigneeRepository;

	public TaskManagerApplication(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
		this.todoRepository = todoRepository;
		this.assigneeRepository = assigneeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Assignee testUser = new Assignee("Tony Stark", "tonyStark@gmail.com", "testpassword", new ArrayList<>());
		Assignee testUser2 = new Assignee("Steve Rogers", "steveRogers@gmail.com", "testpassword2", new ArrayList<>());
		Assignee savedAssignees = assigneeRepository.save(testUser);
		Assignee savedAssignees2 = assigneeRepository.save(testUser2);

		Todo testTodo = new Todo("Grocery Shopping", "Purchase weekly groceries including fruits, vegetables, and household supplies", false, false,
									LocalDate.now(), LocalDate.now().plusDays(2), new ArrayList<>());
		testTodo.getAssignees().add(savedAssignees);
		todoRepository.save(testTodo);

		Todo testTodo2 = new Todo("Finalize Project Proposal", "Compile all sections of the project proposal and submit to the team lead", false, false,
				LocalDate.now(), LocalDate.now().plusDays(7), new ArrayList<>());
		testTodo2.getAssignees().add(savedAssignees);
		todoRepository.save(testTodo2);

		Todo testTodo3 = new Todo("Plan Family Dinner", "Decide on dinner menu for family get-together and send out invitations", false, false,
				LocalDate.now(), LocalDate.now().plusDays(1), new ArrayList<>());
		testTodo3.getAssignees().add(savedAssignees2);
		testTodo3.getAssignees().add(savedAssignees);
		todoRepository.save(testTodo3);
	}
}
