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
    };

    $scope.exports = function (taskList) {
        var doc = new jsPDF();
        var tasks = taskList.tasks.length ? getTasksString(taskList.tasks) : 'Não há tarefas nessa lista.';
        var finalResult = 'Lista: ' + taskList.name + '\n\n' + '~Tarefas:' + '\n' + tasks;
        doc.text(finalResult, 25, 30);
        var date = new Date();
        var dateFormated = date.getDate() + '_' + date.getMonth() + '_' + date.getFullYear();
        dateFormated += '_' + date.getHours() + date.getMinutes();
        doc.save(taskList.name + '_' + dateFormated + '.pdf');
    };

    function getTasksString(tasks) {
        var result = '';
        for (var i = 0; i < tasks.length; i++) {
            var temp = '    => Tarefa: ' + tasks[i].name + '\n';
            var description = tasks[i].description && tasks[i].description != '' ? tasks[i].description : 'n/a';
            temp += '        - Descrição: ' + description + '\n';
            var priority = tasks[i].priority ? tasks[i].priority : 'n/a';
            temp += '        - Prioridade: ' + priority;
            var done = tasks[i].done ? 'sim' : 'não';
            temp += '        Feita: ' + done;
            result += temp + '\n\n';
        }
        return result;
    }

});