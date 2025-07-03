package application;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import model.entities.Task;
import model.services.TaskService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner scanner = new Scanner(System.in);

		List<Task> tasks = new ArrayList<>();
		TaskService taskService = new TaskService(tasks);

		boolean appOn = true;

		System.out.println("GERENCIADOR DE TAREFAS");
		do {

			System.out.println("Escolha uma das opçoes:" + "\n[ 1 ] Cadastrar nova tarefa" + "\n[ 2 ] Listar tarefas"
					+ "\n[ 3 ] Buscar uma tarefa" + "\n[ 4 ] Remover uma tarefa"
					+ "\n[ 5 ] Marcar uma tarefa como concluida" + "\n[ 6 ] Exibir estatisticas das tarefas" + "\n[ 7 ] Sair");

			String option = scanner.nextLine();
			try {

				switch (option) {

				case "1":
					Task newTask = taskService.registerTask(scanner);
					taskService.addTask(newTask);
					break;

				case "2":
					taskService.displayTasks();
					break;

				case "3":
					System.out.print("Informe o titulo da tarefa que deseja buscar: ");
					String title = scanner.nextLine();
					Optional<Task> task = taskService.searchTask(title);
					task.ifPresentOrElse(t -> System.out.println(t),
							() -> System.out.println("Esta tarefa não existe na lista"));
					break;

				case "4":
					System.out.print("Informe o titulo da tarefa que deseja remover: ");
					String titleTaskToRemove = scanner.nextLine();
					taskService.removeTask(titleTaskToRemove);
					break;
				case "5":
					System.out.print("Informe o titulo da tarefa que deseja marcar como concluida: ");
					String titleTaskChecked = scanner.nextLine();
					taskService.markTaskAsComplete(titleTaskChecked);
					break;
					
				case "6":
					taskService.totalTasks();
					taskService.quantityPerPriority();
					taskService.quantityPerStatus();
					taskService.oldestTask();
					taskService.mostRecentTask();
					break;

				case "7":
					System.out.println("Fim do programa");
					appOn = false;
					break;

				default:
					System.out.println("Opção inválida, tente novamente");
					break;

				}

			} catch (IllegalArgumentException e) {
				System.out.println("ERRO: Dado inválido, tente novamente");
			}

		} while (appOn);

		scanner.close();
	}

}
