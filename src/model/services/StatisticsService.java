package model.services;

import java.util.List;

import model.entities.Task;

public interface StatisticsService {
	
	List<Task> getTasks();
	
	default void totalTasks() {
		System.out.println("Total de tarefas: " + getTasks().size());
	}
	
	void quantityPerPriority();
	
	void mostRecentTask();
	
	void oldestTask();

}
