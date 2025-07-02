package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import model.enums.Priority;
import model.enums.Status;

public class Task implements Comparable<Task> {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String title;
	private String description;
	private Priority priority;
	private Status status;
	private LocalDate createdAt;
	
	public Task(String title, String description, Priority priority, Status status, LocalDate createdAt) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, description, priority, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& priority == other.priority && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Título: " + title + "\n");
		sb.append("Descrição: " + description + "\n");
		sb.append("Prioridade: " + priority.name().toLowerCase() + "\n");
		sb.append("Status: " + status.name().toLowerCase() + "\n");
		sb.append("Criado em: " + createdAt.format(formatter) + "\n");
		return sb.toString();
	}

	@Override
	public int compareTo(Task otherTask) {
		return -priority.compareTo(otherTask.getPriority());
	}
	
}
