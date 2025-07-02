package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.entities.Task;
import model.enums.Priority;
import model.services.TaskService;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		List<Task> tasks = new ArrayList<>();
		
		Task task1 = new Task("Estudar", "Estudar Java", Priority.ALTA, LocalDate.parse("02/07/2025", formatter));
		Task task2 = new Task("Ler", "Ler estrutura de dados e algoritmos", Priority.MEDIA, LocalDate.parse("05/07/2025", formatter));
		
		tasks.add(task1);
		tasks.add(task2);
		TaskService taskService = new TaskService(tasks);
		
		taskService.quantityPerPriority();
		

	}

}
