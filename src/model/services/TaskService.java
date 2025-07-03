package model.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.entities.Task;
import model.enums.Priority;
import model.enums.Status;

public class TaskService implements TaskDisplay, StatisticsService {
	
	List<Task> tasks;
	
	public TaskService(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public Task registerTask(Scanner sc)  throws IllegalArgumentException {
		
		System.out.print("Título da tarefa: ");
		String title = sc.nextLine();
		
		System.out.print("Descrição da tarefa: ");
		String description = sc.nextLine();
		
		System.out.print("Nível de prioridade da tarefa [baixo | medio | alto]: ");
		Priority priority = Priority.valueOf(sc.nextLine().toUpperCase());
		
		return new Task(title, description, priority, LocalDateTime.now());
		
	}
	
	public void addTask(Task task) {
		getTasks().add(task);
	}
	
	public void removeTask(String title) {
		Optional<Task> task = tasks.stream().filter(t -> t.getTitle().equalsIgnoreCase(title)).findFirst();
		task.ifPresentOrElse(taskOld -> getTasks().remove(taskOld), () -> System.out.println("Essa tarefa não existe na lista"));
	}
	
 	public Optional<Task> searchTask(String title) {
		
		return tasks.stream().filter(t -> t.getTitle().equalsIgnoreCase(title)).findFirst();
		
	}
 	
 	public void markTaskAsComplete(String title) {
 		Optional<Task> result = searchTask(title);
 		result.ifPresentOrElse(task -> task.setStatus(Status.COMPLETED), () -> System.out.println("Essa tarefa não existe na lista"));
 	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	private int getQuantityPriority(String priorityText) {
		return (int)tasks.stream().map(task -> task.getPriority()).filter(priority -> priority.name().contentEquals(priorityText)).count();
	}
	
	private int getQuantityStatus(String statusText) {
		return (int)tasks.stream().map(task -> task.getStatus()).filter(status -> status.name().contentEquals(statusText)).count();
	}
	
	@Override
	public void quantityPerPriority() {
		
		int lowPriorityQuantity = getQuantityPriority("BAIXO");
		int mediumPriorityQuantity = getQuantityPriority("MEDIO");
		int highPriorityQuantity = getQuantityPriority("ALTO");
		
		System.out.println("Quantidade de tarefas de baixa prioridade: " + lowPriorityQuantity);
		System.out.println("Quantidade de tarefas de média prioridade: " + mediumPriorityQuantity);
		System.out.println("Quantidade de tarefas de alta prioridade: " + highPriorityQuantity + "\n");
		
	}
	
	@Override
	public void quantityPerStatus() {
		int pendingQuantity = getQuantityStatus("PENDING");
		int completedQuantity = getQuantityStatus("COMPLETED");
		
		System.out.println("Quantidade de tarefas pendentes: " + pendingQuantity);
		System.out.println("Quantidade de tarefas concluidas: " + completedQuantity + "\n");
		
	}
	
	@Override
	public void mostRecentTask() {
		
		Task mostRecent = getTasks().get(0);
		for(int i = 1; i < getTasks().size(); i++) {
			if(getTasks().get(i).getCreatedAt().isAfter(mostRecent.getCreatedAt())) {
				mostRecent = getTasks().get(i);
			}
		}
		
		System.out.println("A tarefa mais recente:\n" + mostRecent + "\n");
	
	}

	@Override
	public void oldestTask() {
		
		Task oldest = getTasks().get(0);
		for(int i = 1; i < getTasks().size(); i++) {
			
			if(getTasks().get(i).getCreatedAt().isBefore(oldest.getCreatedAt())) {
				oldest = getTasks().get(i);
			}
		}
		
		System.out.println("A tarefa mais antiga:\n" + oldest);
		
	}
	
}
