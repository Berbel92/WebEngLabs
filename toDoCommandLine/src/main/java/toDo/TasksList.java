package toDo;

import java.util.ArrayList;
import java.util.List;

public class TasksList {

	private List<Task> tasksList = new ArrayList<Task>();

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Task> tasks) {
		this.tasksList = tasks;
	}

	public void addTask(Task task) {
		tasksList.add(task);
	}
}