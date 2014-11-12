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
 * A service that manipulates contacts in an address book.
 *
 */
@Path("/toDoList")
public class ToDoRestService {

	@Inject
	TasksList tasksList;

	/**
	 * A GET /contacts request should return the address book in JSON.
	 * @return a JSON representation of the address book.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TasksList tasksList() {
		return tasksList;
	}

	/**
	 * A POST /contacts request should add a new entry to the address book.
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(@Context UriInfo info, Task task) {
		tasksList.getTasksList().add(task);
		task.setId(tasksList.nextId());
		task.setHref(info.getAbsolutePathBuilder().path("task/{id}").build(task.getId()));
		return Response.created(task.getHref()).entity(task).build();
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
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

	/**
	 * A PUT /contacts/person/{id} should update a entry if exists
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new updated entry or 400 if the id is not a key
	 */
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

	/**
	 * A DELETE /contacts/person/{id} should delete a entry if exists
	 * @param id the unique identifier of a person
	 * @return 204 if the request is successful, 404 if the id is not a key
	 */
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
