'use strict';

App.factory('UserService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllUsers : function() {
					return $http.get(
							'api/user/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users', errResponse);
								return $q.reject(errResponse);
							});
				},
				
				fetchUsersByFields : function(value) {
					return $http.get(
							'api/user/find/' + value)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				
				fetchUser : function(id) {
					return $http.get(
							'api/user/' + id)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},

				createUser : function(user) {
					return $http.post(
							'api/user/',
							user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating user');
						return $q.reject(errResponse);
					});
				},

				updateUser : function(user) {
					return $http.put(
							'api/user/',
							user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating user');
						return $q.reject(errResponse);
					});
				},

				deleteUser : function(id) {
					return $http.delete(
							'api/user/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while deleting user');
						return $q.reject(errResponse);
					});
				}

			};

		} ]);