angular.module('todoList').factory('Priority', function ($resource) {
    return $resource('/priorities');
});