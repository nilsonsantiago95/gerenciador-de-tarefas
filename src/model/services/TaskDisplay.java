package model.services;

import java.util.Collections;
import java.util.List;

import model.entities.Task;

public interface TaskDisplay {
	
	List<Task> getTasks();
	
	default void displayTasks() {
		
		Collections.sort(getTasks());
		for(Task task : getTasks()) {
			System.out.println(task);
		}
	}
	
}
