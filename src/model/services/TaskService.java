package model.services;

import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;

import model.entities.Task;

public class TaskService implements TaskDisplay, StatisticsService {
	
	List<Task> tasks;
	
	public TaskService(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void searchTask(String title) {
		
		Optional<Task> task = tasks.stream().filter(t -> t.getTitle().equalsIgnoreCase(title)).findFirst();
		task.ifPresentOrElse(t -> System.out.println(task.get()), () -> System.out.println("Esta tarefa não existe na lista"));
		
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	private int getQuantity(String priorityText) {
		return (int)tasks.stream().map(task -> task.getPriority()).filter(task -> task.name().contentEquals(priorityText)).count();
	}

	@Override
	public void quantityPerPriority() {
		
		int lowPriorityQuantity = getQuantity("BAIXA");
		int mediumPriorityQuantity = getQuantity("MEDIA");
		int highPriorityQuantity = getQuantity("ALTA");
		
		System.out.println("Quantidade de tarefas de baixa prioridade: " + lowPriorityQuantity);
		System.out.println("Quantidade de tarefas de média prioridade: " + mediumPriorityQuantity);
		System.out.println("Quantidade de tarefas de alta prioridade: " + highPriorityQuantity);
		
	}

	@Override
	public void mostRecentTask() {
		
		
		
	}

	@Override
	public void oldestTask() {
		// TODO Auto-generated method stub
		
	}
	
}
