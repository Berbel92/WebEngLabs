package toDo;

public class Task {
	
	public String task;
	public String context;
	public String project;
	public String priority;
	
	public Task(String task, String context, String project, String priority){
		this.task = task;
		this.context = context;
		this.project = project;
		this.priority = priority;
	}
	
	public String getTask(){
		return this.task;
	}
	
	public void setTask(String task){
		this.task = task;
	}
	
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getProject(){
		return this.project;
	}
	
	public void setProject(String project){
		this.project = project;
	}
	
	public String getPriority(){
		return this.priority;
	}
	
	public void setPriority(String priority){
		this.priority = priority;
	}
}