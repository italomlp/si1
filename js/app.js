var app = angular.module('todoList', []);
app.controller('TasksController', function() {
	this.actualPercent = 0;
	this.tasksArray = [
						{name : "Cortar a grama", done : false}, 
						{name : "Ir ao supermercado", done : false}, 
						{name : "Fazer o jantar", done : false}
					];
	
	this.add = function (taskName) {
		this.tasksArray.push({name : taskName, done : false});
		this.calculatePercent();
	};
	
	this.clear = function () {
		this.tasksArray = [];
		this.actualPercent = 0;
	};
	
	this.calculatePercent = function () {
		var tasksDone = 0;
		for (var i = 0; i < this.tasksArray.length; i++) {
			if (this.tasksArray[i].done == true) {
				tasksDone = tasksDone + 1;
			}
		}
		if (tasksDone != 0) {
			this.actualPercent = (100 * tasksDone) / this.tasksArray.length;
		}
	};
});
