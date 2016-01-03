'use strict';

App.controller('UserListController', [
		'$scope',
		'$location',
		'UserService',
		function($scope, $location, UserService) {
			var self = this;
			self.users = [];
			self.searchingValue = '';

			// -- Service data functions
			self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
			
			self.fetchUsersByFileds = function(value) {
				UserService.fetchUsersByFields(value).then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};

			self.deleteUser = function(id) {
				UserService.deleteUser(id).then(self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User.');
						});
			};

			self.fetchAllUsers();
			
			// -- BUTTONS handlers
			self.refresh = function() {
				if (self.searchingValue == null || self.searchingValue == '') {
					console.log('refresh user list');
					self.fetchAllUsers();
				} else {
					console.log('refresh user list by searching value %s..', self.searchingValue);
					self.fetchUsersByFileds(self.searchingValue);
				}
			};
			
			self.add = function() {
				console.log('add new user');
				$location.path('/userCreate');
			};
			
			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.users.length; i++) {
					if (self.users[i].id === id) {
						$location.path('/userEdit/' + id);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				self.deleteUser(id);
			};

		} ]);

App.controller('UserEditController', [
		'$scope',
		'$location',
		'$routeParams',
		'UserService',
		function($scope, $location, $routeParams, UserService) {

			var self = this;
			self.user_prototype = {
				id : null,
				name : '',
				login : '',
				password : ''
			};
			self.user = self.user_prototype;
			
			self.setUser = function(user) {
				self.user_prototype = user;
				self.user = angular.copy(user);
			}

			// -- Service ok answers
			self.fetchOk = function(d) {
				console.log('fetch Ok ');
				self.setUser(d);
			};
			
			self.saveOk = function(d) {
				console.log('save Ok ');
				self.setUser(d);
				self.exit();
			};
			
			self.deleteOk = function() {
				console.log('delete Ok ');
				self.exit();
			};
			
			// -- Service methods
			self.fetchUser = function(id) {
				UserService.fetchUser(id).then(self.fetchOk,
						function(errResponse) {
							console.error('Error while fetching User');
						});
			};

			self.createUser = function(user) {
				UserService.createUser(user).then(self.saveOk,
						function(errResponse) {
							console.error('Error while creating User.');
						});
			};

			self.updateUser = function(user) {
				UserService.updateUser(user).then(self.saveOk,
						function(errResponse) {
							console.error('Error while updating User.');
						});
			};
			
			self.deleteUser = function(id) {
				UserService.deleteUser(id).then(self.deleteOk,
						function(errResponse) {
							console.error('Error while deleting User.');
						});
			};

			console.log('User id is', $routeParams.userId);
			if ($routeParams.userId != null) {
				self.fetchUser($routeParams.userId);
			}

			self.submit = function() {
				var userId = self.user.id;
				if (self.user.id === null) {
					console.log('Saving New User', self.user);
					self.createUser(self.user);
				} else {
					self.updateUser(self.user);
					console.log('User updated with id ', self.user.id);
				}
				// self.reset();
			};

			self.reset = function() {
				self.user = angular.copy(self.user_prototype);
				$scope.userForm.$setPristine(); // reset Form
			};
			
			self.remove = function() {
				console.log('id to be deleted', self.user.id);
				self.deleteUser(self.user.id);
			};
			
			self.exit = function() {
				$location.path('/users');
			};

		} ]);