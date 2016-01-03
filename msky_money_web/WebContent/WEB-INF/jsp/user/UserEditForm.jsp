<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="generic-container">

	<!-- User edit panel -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">User Registration Form </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit()" name="userForm" class="form-horizontal">
				<input type="hidden" ng-model="ctrl.user.id" />

				<!-- User name row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="uname">Name</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.name" name="uname" class="field form-control input-sm" placeholder="Enter user name" required
								ng-maxlength="1000" />
						</div>
						<div class="has-error col-md-2" ng-show="userForm.uname.$dirty && userForm.uname.$invalid">
							<span ng-show="userForm.uname.$error.required">This is a required field.</span> 
							<span ng-show="userForm.uname.$error.maxlength">Maximum length required is 1000</span> 
						</div>
					</div>
					
				</div>

				<!-- User login row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="login">Login</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.login" name="login" class="field form-control input-sm" placeholder="Enter user login" required
								ng-maxlength="100" />
						</div>
						<div class="has-error col-md-2" ng-show="userForm.login.$dirty && userForm.login.$invalid">
								<span ng-show="userForm.login.$error.required">This is a required field.</span> 
								<span ng-show="userForm.login.$error.maxlength">Maximum length required is 100</span> 
						</div>
					</div>
				</div>


				<!-- User password row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="password">Password</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.password" name="password" class="field form-control input-sm"
								placeholder="Enter your password" ng-maxlength="100" />
						</div>
						<div class="has-error col-md-2" ng-show="userForm.password.$dirty && userForm.password.$invalid">
								<span ng-show="userForm.password.$error.maxlength">Maximum length required is 100</span> 
						</div>
					</div>
				</div>

				<!-- buttons panel -->
				<div class="row">
					<div class="form-actions floatRight">
						<input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="userForm.$invalid">
						<button type="button" ng-click="ctrl.exit()" class="btn btn-primary btn-sm custom-width-60">Exit</button>
						<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="userForm.$pristine">Reset Form</button>
						<button type="button" ng-click="ctrl.remove()" class="btn btn-danger btn-sm" ng-disabled="!ctrl.user.id">Remove</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
