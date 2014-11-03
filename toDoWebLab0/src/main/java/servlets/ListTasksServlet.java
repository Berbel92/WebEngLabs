package servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import toDo.Task;
import toDo.TasksList;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/ListTasksServlet" })
public class ListTasksServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String fileName = req.getParameter("fileName");
		String condition = req.getParameter("condition");
		String text = req.getParameter("text");
		String list = "";
		Gson gson = new Gson();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		try{
			TasksList tasksList = gson.fromJson(new FileReader(
					fileName), TasksList.class);
			
			resp.setStatus(HttpServletResponse.SC_OK);
			
			for (Task task : tasksList.getTasksList()) {
				if (condition.compareTo("name") == 0){
					if (task.getName().contains(text)){
						list = list + convertTaskToHtml(task);
					}
				} 
				else if (condition.compareTo("context") == 0){
					if (task.getContext().contains(text)){
						list = list + convertTaskToHtml(task);
					}
				} 
				else if (condition.compareTo("project") == 0){
					if (task.getProject().contains(text)){
						list = list + convertTaskToHtml(task);
					}
				} 
				else {
					if (task.getPriority() == Integer.valueOf(text)) {
						list = list + convertTaskToHtml(task);
					}
				}
			}
			if (list.length() == 0){
				list = "<br/><b>Error: No results for your query.</b><br/>";
			}
			
			out.println("<html><head><title>ToDo tasks list</title></head>"
					+ "<body><h1>Results for your query in file " + fileName + "</h1>"
					+ "<br/><p>" + list + "</p>"
					+ "</body></html>");
			
		} catch (FileNotFoundException e){
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out.println("<html><head><title>Error 404</title></head>"
					+ "<body><h1>Error 404</h1><br/><br/>"
					+ "<p>File not found.</p>"
					+ "</body></html>");
		}
	}

	private String convertTaskToHtml(Task task) {
		String html = "";
		html += "<br/>Task name: <b>" + task.getName()
				+ "</b><br/>Task context: " + task.getContext()
				+ "<br/>Task project: " +  task.getProject()
				+ "<br/>Task priority(0-10): " + task.getPriority() + "<br/>";
		return html;
	}
}