package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import model.entities.Task;
import model.enums.Priority;
import model.enums.Status;
import model.services.TaskService;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		List<Task> tasks = new ArrayList<>();
		
		Task task1 = new Task("Estudar", "Estudar Java", Priority.HIGH, Status.COMPLETED, LocalDate.parse("02/07/2025", formatter));
		Task task2 = new Task("Ler", "Ler estrutura de dados e algoritmos", Priority.MEDIUM, Status.PENDING, LocalDate.parse("10/07/2025", formatter));
		Task task3 = new Task("Treinar", "Treinar Boxe", Priority.LOW, Status.COMPLETED, LocalDate.parse("05/07/2025", formatter));
		Task task4 = new Task("Entretenimento", "Assistir anime", Priority.LOW, Status.PENDING, LocalDate.parse("02/08/2025", formatter));
		Task task5 = new Task("Malhar", "Malhar braço", Priority.MEDIUM, Status.COMPLETED, LocalDate.parse("01/07/2025", formatter));
		
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		
		TaskService taskService = new TaskService(tasks);
	
		taskService.displayTasks();
		taskService.quantityPerStatus();
		

	}

}
