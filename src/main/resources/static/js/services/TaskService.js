angular.module('todoList').factory('Task', function ($resource) {
    return $resource('/task_lists/tasks/:listId/:taskId');
});