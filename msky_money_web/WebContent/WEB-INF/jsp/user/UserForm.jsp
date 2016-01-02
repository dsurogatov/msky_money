<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="generic-container">

	<!-- User edit panel -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">User Registration Form </span>
		</div>
		<div class="formcontainer">
			<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
				<input type="hidden" ng-model="ctrl.user.id" />

				<!-- User name row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="uname">Name</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.name" id="uname"
								class="field form-control input-sm"
								placeholder="Enter user name" required ng-minlength="3" />
							<div class="has-error" ng-show="myForm.$dirty">
								<span>TEST TEST</span>
								<span ng-show="myForm.uname.$error.required">This is a
									required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
									length required is 3</span> <span ng-show="myForm.uname.$invalid">This
									field is invalid </span>
							</div>
						</div>
					</div>
				</div>

				<!-- User login row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="login">Login</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.login" id="login"
								class="field form-control input-sm"
								placeholder="Enter user login" required ng-minlength="3" />
							<div class="has-error" ng-show="myForm.$dirty">
								<span ng-show="myForm.login.$error.required">This is a
									required field</span> <span ng-show="myForm.login.$error.minlength">Minimum
									length required is 3</span> <span ng-show="myForm.login.$invalid">This
									field is invalid </span>
							</div>
						</div>
					</div>
				</div>


				<!-- User password row -->
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="password">Password</label>
						<div class="col-md-7">
							<input type="text" ng-model="ctrl.user.password" id="password"
								class="form-control input-sm"
								placeholder="Enter your password. [This field is validation free]" />
						</div>
					</div>
				</div>

				<!-- buttons panel -->
				<div class="row">
					<div class="form-actions floatRight">
						<input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
							class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
						<button type="button" ng-click="ctrl.reset()"
							class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
							Form</button>
					</div>
				</div>

			</form>
		</div>
	</div>

	<!-- User list panel -->
	<div class="panel panel-default">

		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Users </span>
		</div>

		<!-- user table -->
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID.</th>
						<th>Name</th>
						<th>Login</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="u in ctrl.users">
						<td><span ng-bind="u.id"></span></td>
						<td><span ng-bind="u.name"></span></td>
						<td><span ng-bind="u.login"></span></td>
						<td>
							<button type="button" ng-click="ctrl.edit(u.id)"
								class="btn btn-success custom-width">Edit</button>
							<button type="button" ng-click="ctrl.remove(u.id)"
								class="btn btn-danger custom-width">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
