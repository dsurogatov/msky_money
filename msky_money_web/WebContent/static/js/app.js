'use strict';

var App = angular.module('myApp', [ 'ngRoute' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				template : 'This is the default Route'
			})
			.when('/computers', {
				template : 'This is the computers Route--------------'
			})
			.when('/users', {
				templateUrl : 'UserForm',
				controller : 'UserController',
				controllerAs : 'ctrl'
			})
			.otherwise({
				redirectTo : '/'
			});
		} ]);