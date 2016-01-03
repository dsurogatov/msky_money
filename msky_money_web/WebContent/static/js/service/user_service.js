'use strict';

App.factory('UserService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllUsers : function() {
					return $http.get(
							'http://localhost:8080/msky_money_web/api/user/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				
				fetchUsersByFields : function(value) {
					return $http.get(
							'http://localhost:8080/msky_money_web/api/user/find/' + value)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				
				fetchUser : function(id) {
					return $http.get(
							'http://localhost:8080/msky_money_web/api/user/' + id)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},

				createUser : function(user) {
					return $http.post(
							'http://localhost:8080/msky_money_web/api/user/',
							user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating user');
						return $q.reject(errResponse);
					});
				},

				updateUser : function(user) {
					return $http.put(
							'http://localhost:8080/msky_money_web/api/user/',
							user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating user');
						return $q.reject(errResponse);
					});
				},

				deleteUser : function(id) {
					return $http.delete(
							'http://localhost:8080/msky_money_web/api/user/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while deleting user');
						return $q.reject(errResponse);
					});
				}

			};

		} ]);