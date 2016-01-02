<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>MskyMoney</title>
<style>
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app='myApp'>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#/">MskyMoney</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#/">Transmissions</a></li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Lists <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#/users">Users</a></li>
						</ul>
					</li>
					<li><a href="#">Reports</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div ng-view></div>

	<jsp:include page="${request.contextPath}/about" />

	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>