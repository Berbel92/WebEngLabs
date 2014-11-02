package toDo;

public class Task {
	
	public String name;
	public String context;
	public String project;
	public String priority;
	
	public Task(String name, String context, String project, String priority){
		this.name = name;
		this.context = context;
		this.project = project;
		this.priority = priority;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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