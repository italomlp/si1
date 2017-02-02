var app = angular.module('todoList', []);
app.controller('TasksController', function() {
	this.actualPercent = 0;
	this.actualTasksDone = 0;
	this.actualTasksNotDone = 3;
	this.tasksArray = [
						{name : "Cortar a grama", done : false}, 
						{name : "Ir ao supermercado", done : false}, 
						{name : "Fazer o jantar", done : false}
					];
	
	this.add = function (taskName) {
		this.tasksArray.push({name : taskName, done : false});
		this.calculateDones();
	};
	
	this.remove = function (task) {
		this.tasksArray.splice(this.tasksArray.indexOf(task), 1);
		this.calculateDones();
	};
	
	this.clear = function () {
		this.tasksArray = [];
		this.calculateDones();
		// this.actualPercent = 0;
		// this.actualTasksDone = 0;
		// this.actualTasksNotDone = 0;
	};
	
	this.calculateDones = function () {
		var tasksDone = 0;
		for (var i = 0; i < this.tasksArray.length; i++) {
			if (this.tasksArray[i].done == true) {
				tasksDone = tasksDone + 1;
			}
		}
		this.actualPercent = ((100 * tasksDone) / this.tasksArray.length).toFixed(2);
		this.actualTasksDone = tasksDone;
		this.actualTasksNotDone = this.tasksArray.length - tasksDone;
	};
});
