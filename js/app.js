var app = angular.module('todoList', []);
app.controller('TasksController', function() {
	this.tasksArray = ["Cortar a grama", "Ir ao supermercado", "Fazer o jantar"];
	
	this.add = function (taskName) {
		this.tasksArray.push(taskName);
	};
	
	this.clear = function () {
		this.tasksArray = [];
	};
});
