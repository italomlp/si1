angular.module('todoList').controller('TaskListController', function ($scope, $routeParams, TaskList, Task, toastr) {

    $scope.operation = 0;

    if($routeParams.listId) {
        TaskList.get({listId: $routeParams.listId},
            function (taskList) {
                $scope.taskList = taskList;
            },
            function (error) {
                toastr.error('Não foi possível obter lista.', 'Erro');
                console.log(error);
            }
        )
    } else {
        $scope.operation = 1;
        $scope.taskList = new TaskList();
    }

    $scope.saveList = function () {
        $scope.taskList.$save()
            .then(function () {
                toastr.success('Salvo com sucesso', 'Sucesso');
                if ($scope.operation == 1) {
                    $scope.taskList = new TaskList();
                }
            })
            .catch(function (error) {
                toastr.error('Não foi possível salvar.');
            })
    };

});