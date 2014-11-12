package rest.toDoRest;

import java.util.ArrayList;
import java.util.List;

public class TasksList {

	private List<Task> tasksList = new ArrayList<Task>();
	private int nextId = 1;
	

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Task> tasks) {
		this.tasksList = tasks;
	}

	public void addTask(Task task) {
		tasksList.add(task);
	}
	
	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}
	
	public int nextId() {
		int oldValue = nextId;
		nextId++;
		return oldValue;
	}
}