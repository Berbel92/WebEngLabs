package rest.toDoRest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


/**
 * A service that manipulates a toDo list.
 *
 */
@Path("/toDoList")
public class ToDoRestService {

	@Inject
	TasksList tasksList;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TasksList tasksList() {
		return tasksList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(@Context UriInfo info, Task task) {
		tasksList.getTasksList().add(task);
		task.setId(tasksList.nextId());
		task.setHref(info.getAbsolutePathBuilder().path("task/{id}").build(task.getId()));
		return Response.created(task.getHref()).entity(task).build();
	}

	@GET
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTask(@PathParam("id") int id) {
		for (Task task : tasksList.getTasksList()) {
			if (task.getId() == id) {
				return Response.ok(task).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@Context UriInfo info,
			@PathParam("id") int id, Task task) {
		for (int i = 0; i < tasksList.getTasksList().size(); i++) {
			if (tasksList.getTasksList().get(i).getId() == id) {
				task.setId(id);
				task.setHref(info.getAbsolutePath());
				tasksList.getTasksList().set(i, task);
				return Response.ok(task).build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@PathParam("id") int id) {
		for (int i = 0; i < tasksList.getTasksList().size(); i++) {
			if (tasksList.getTasksList().get(i).getId() == id) {
				tasksList.getTasksList().remove(i);
				return Response.noContent().build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
