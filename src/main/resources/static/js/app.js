angular.module('todoList', ['ngRoute', 'ngResource', 'toastr'])
	.config(function ($routeProvider, $locationProvider) {
		$routeProvider
            .when('/', {
                redirectTo: '/task_lists'
            })
            .when('/create_list', { // create Task List
                templateUrl: 'partials/create_task_list.html',
                controller: 'TaskListController'
            })
            .when('/task_lists', { // get all Task Lists
                templateUrl: 'partials/list_task_lists.html',
                controller: 'TaskListsController'
            })
            .when('/task_lists/:listId', { // get tasks of a specificated Task List
                templateUrl: 'partials/list_tasks.html',
                controller: 'TasksController'
            })
            .when('/task_lists/create_task/:listId', { // create Task into the specificated Task List
                templateUrl: 'partials/create_task.html',
                controller: 'TaskController'
            })
            .when('/task_lists/create_task/:listId/:taskId', { // edit Task of the specificated Task List
                templateUrl: 'partials/create_task.html',
                controller: 'TaskController'
            })
            .when('/contact', {
                templateUrl: 'partials/contact.html'
            })
            .otherwise({redirectTo: '/'});
		// $locationProvider.html5Mode(false).hashPrefix('');
        $locationProvider.hashPrefix('');

	});