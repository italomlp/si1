angular.module('todoList').factory('TaskList', function ($resource) {
    return $resource('/task_lists/:listId');
});