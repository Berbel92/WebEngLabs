var URL = "http://localhost:8080/toDoList";


function getTasksList(){
	$.ajax({
		url: URI,
		type: 'GET',
		contentType: 'application/json',
		success: function(json){
			toObject(json);
		}
	});

}

$( document).ready(function(){
	getList();
	addButton(); 
	deleteButton();
});

function toJSON(){
	return JSON.stringify({
				task: $(task).val(),
				project: $(project).val(),
				context: $(context).val(),
				priority: $(priority).val()
			});

}

function toObject(json){
	var tasks = json.toDoList;
	
	for( var i in tasks){
		updateTable(tasks[i]);
	}

}

function fillTable(item){
	var row = $('<tr><td>' + item.task + '</td><td>' + item.project + '</td><td>' + item.context + '</td><td>' + item.priority +'</td></tr>');
	$('#listToDo').find('tbody').append(row);
}
