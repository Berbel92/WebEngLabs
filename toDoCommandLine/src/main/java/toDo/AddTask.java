package toDo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;

public class AddTask {
	
	public final static String DEFAULT_FILE_NAME = "tasksList.json";

	static Task PromptForTask(BufferedReader stdin, PrintStream stdout){
		
		try {
			Task newTask = new Task();

			stdout.print("Enter the task name: ");
			newTask.setName(stdin.readLine());

			stdout.print("Enter the task context: ");
			newTask.setContext(stdin.readLine());
			
			stdout.print("Enter the task project: ");
			newTask.setProject(stdin.readLine());
		
			int priority = -1;

			while (priority < 0 || priority > 10) {
				stdout.print("Enter the task priority (0-10): ");
				try{
					priority = Integer.valueOf(stdin.readLine());
					if (priority>=0 && priority<=10) {
						newTask.setPriority(priority);
					} else {
						stdout.print("\n\n\nPlease, enter a valid priority {0,1,2,...,10}\n");
					}
				} 
				catch (NumberFormatException e){
					stdout.print("\n\n\nPlease, enter a valid priority {0,1,2,...,10}\n");
				}
			}		
			return newTask;
		}
		catch (Exception e){
			stdout.print("\n\n\nError creating the new task.\n");
			return new Task();
		}
	}
	
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename=args[0];
		}

		TasksList tasksList = new TasksList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			tasksList = gson.fromJson(new FileReader(filename), TasksList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}

		// Add an address.
		tasksList.addItem(PromptForToDo(new BufferedReader(
				new InputStreamReader(System.in)), System.out));

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(tasksList));
		output.close();
	}

}