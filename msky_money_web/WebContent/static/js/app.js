'use strict';

var App = angular.module('myApp', [ 'ngRoute' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				template : 'This is the default Route'
			})
			.when('/users', {
				templateUrl : 'UserListForm',
				controller : 'UserListController',
				controllerAs : 'ctrl'
			})
			.when('/userEdit/:userId', {
				templateUrl : 'UserEditForm',
				controller : 'UserEditController',
				controllerAs : 'ctrl'
			})
			.when('/userCreate', {
				templateUrl : 'UserEditForm',
				controller : 'UserEditController',
				controllerAs : 'ctrl'
			})
			.otherwise({
				redirectTo : '/'
			});
		} ]);

// -- create directive to handle the enter key has been pressed in the input field
App.directive('mskyEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.mskyEnter);
                });

                event.preventDefault();
            }
        });
    };
});