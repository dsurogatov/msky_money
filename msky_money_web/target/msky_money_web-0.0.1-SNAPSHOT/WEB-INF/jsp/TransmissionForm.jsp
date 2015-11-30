<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Transmissions</title>  
    
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="TransmissionController as ctrl">
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Transmissions </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Date</th>
                              <th>Value</th>
                              <th>Description</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.transmissions">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.date | date:'medium'"></span></td>
                              <td><span ng-bind="u.value"></span></td>
                              <td><span ng-bind="u.description"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/transmission_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/transmission_controller.js' />"></script>
  </body>
</html>