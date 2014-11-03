package toDo;

import java.io.FileReader;

import com.google.gson.Gson;

public class ListTasks {

	public final static String DEFAULT_FILE_NAME = "tasks.json";

	static void Print(TasksList tasksList) {
		
		for (Task task : tasksList.getTasksList()) {
			System.out.println("\nTask name: " + task.getName());
			System.out.println("     context: " + task.getContext());
			System.out.println("     project: " +  task.getProject());
			System.out.println("     priority(0-10): " + task.getPriority() + "\n");
		}
	}

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}
		TasksList tasksList = new TasksList();

		tasksList = gson.fromJson(new FileReader(filename),
				TasksList.class);

		Print(tasksList);
	}
}
