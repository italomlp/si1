angular.module('todoList').controller('TaskController', function ($scope, $routeParams, $window, Task, toastr, Priority, Category, CategoryScope) {
    $scope.operation = '';
    $scope.priorities = [];
    $scope.categories = [];
    $scope.taskListId = $routeParams.listId;

    function getPriorities() {
        Priority.query(
            function (priorities) {
                $scope.priorities = priorities;
            },
            function (error) {
                console.log(error);
                toastr.error('Não foi possível obter prioridades', 'Erro');
            }
        )
    };

    $scope.categories = CategoryScope.getCategories();

    $scope.category = CategoryScope.getOneCategory();

    getPriorities();

    $scope.saveCategory = function () {
        CategoryScope.saveCategory();
    };

    if($routeParams.taskId) {
        $scope.operation = 'Salvar';
        Task.get({listId: $scope.taskListId, taskId: $routeParams.taskId},
            function (task) {
                $scope.task = task;
            },
            function (error) {
                toastr.error('Não foi possível obter tarefa.', 'Erro')
                console.log(error);
            });
    } else {
        $scope.operation = 'Criar';
        $scope.task = new Task();
    }

    $scope.saveTask = function () {
        $scope.task.$save({listId: $scope.taskListId})
            .then(function () {
                toastr.success('Salvo com sucesso.', 'Sucesso');
                if ($scope.operation == 'Criar') {
                    $scope.task = new Task();
                }
            })
            .catch(function (error) {
                toastr.error('Não foi possível ' + $scope.operation, 'Erro');
                console.log(error);
            })
    };
});