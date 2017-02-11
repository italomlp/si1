angular.module('todoList').controller('TasksController', function ($scope, $route, $routeParams, Task, toastr) {
    $scope.tasks = [];

    var taskListId = $routeParams.listId;

    function getTasks() {
        Task.query({listId: taskListId},
            function (tasks) {
                $scope.tasks = tasks;
            },
            function (error) {
                toastr.error('Não foi possível obter tarefas.', 'Erro');
                console.log(error);
            }
        )
    };

    getTasks();

    $scope.removeTask = function (task) {
        Task.delete({listId: taskListId, taskId: task.id},
            getTasks,
            function (error) {
                toastr.error('Não foi possível remover tarefa.', 'Erro');
                console.log(error);
            });
    };

    $scope.removeAllTasks = function () {
        Task.delete({listId: taskListId},
            getTasks,
            function (error) {
                toastr.error('Não foi possível remover todas as tarefas.', 'Erro');
                console.log(error);
            }
        );
    };
});