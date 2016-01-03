<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="generic-container">

	<!-- User list panel -->
	<div class="panel panel-default">

		<!-- Default panel contents -->
		<div class="panel-heading">
			<div class="row">
				<div class="col-sm-2">
					<span class="lead">List of Users </span>
				</div>
				<div class="col-sm-4">
					<button type="button" ng-click="ctrl.add()" class="btn btn-primary custom-width">Add</button>
					<button type="button" ng-click="ctrl.refresh()" class="btn btn-default custom-width">Refresh</button>
				</div>
			</div>
			<div style="min-height: 10px;"></div>
			<div class="row">
				<div class="col-sm-2">
					<span class="lead">Search</span>
				</div>
				<div class="col-md-7">
					<input type="text" ng-model="ctrl.searchingValue" msky-enter="ctrl.refresh()" id="usearch" class="form-control" placeholder="Enter searching value" />
				</div>
			</div>
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
							<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>
							<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
