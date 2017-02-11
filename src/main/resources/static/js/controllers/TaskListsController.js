angular.module('todoList').controller('TaskListsController', function ($scope, TaskList, toastr) {

    $scope.taskLists = [];

    function getTaskLists() {
        TaskList.query(
            function (taskLists) {
                $scope.taskLists = taskLists;
            },
            function (error) {
                console.log(error);
                toastr.error('Não foi possível obter listas.', 'Erro');
            }
        )
    }

    getTaskLists();

    $scope.removeList = function (taskList) {
        TaskList.delete({listId: taskList.id},
            getTaskLists,
            function (error) {
                toastr.error('Não foi possível remover lista.', 'Erro');
                console.log(error);
            });
    };

    $scope.removeAllLists = function () {
        TaskList.delete({},
            getTaskLists,
            function (error) {
                toastr.error('Não foi possível remover listas');
                console.log(error);
            });
    }

});