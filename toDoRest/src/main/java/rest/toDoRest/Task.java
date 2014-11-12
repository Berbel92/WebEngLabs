package rest.toDoRest;

import java.net.URI;

public class Task {
	
	private String name;
	private String context;
	private String project;
	private int priority;
	private URI href;
	private int id;
	
	public Task(String name, String context, String project, int priority){
		this.name = name;
		this.context = context;
		this.project = project;
		this.priority = priority;
	}
	
	public Task(){
		
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
	
	public int getPriority(){
		return this.priority;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public URI getHref(){
		return href;
	}
	
	public void setHref(URI href){
		this.href = href;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}