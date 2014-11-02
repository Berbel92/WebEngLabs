package toDo;

import java.util.ArrayList;
import java.util.List;

public class TasksList {

	private List<Task> personList = new ArrayList<Task>();

	public List<Task> getTasksList() {
		return personList;
	}

	public void setPersonList(List<Task> tasks) {
		this.personList = tasks;
	}

	public void addTask(Task task) {
		personList.add(task);
	}
}