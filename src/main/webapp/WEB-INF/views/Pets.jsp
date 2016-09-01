<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../favicon.ico">
	
	<title>Pet Management System</title>
	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value="/resources/css/bootstrap.min.css" />"rel="stylesheet">
</head>
<body ng-app="myApp" class="ng-cloak">
      <div class="container" ng-controller="PetController as ctrl">
      	<h2> Pets Management</h2>
		<p>Create new Pet/Update Pet/Delete Pet</p>		
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Pets </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>CategoryId</th>
                              <th>CategoryName</th>
                              <th>Photo Urls</th>
                              <th>Status</th>
                              <th>TagId</th>
                              <th>Tagname</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="p in ctrl.pets">
                              <td><span ng-bind="p.id"></span></td>
                              <td><span ng-bind="p.name"></span></td>
                              <td><span ng-bind="p.category.id"></span></td>
                              <td><span ng-bind="p.category.name"></span></td>
                              <td><span ng-bind="p.photoUrls"></span></td>
                              <td><span ng-bind="p.status"></span></td>
                              <td><span ng-bind="p.tags[0].id"></span></td>
                              <td><span ng-bind="p.tags[0].name"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(p.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(p.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
          
           <form name="petForm" ng-submit ="ctrl.submit()">
				<div class="form-group">
					<input type="hidden" ng-model="ctrl.pet.id" />
					<label for="name"> Name : </label>
					<input type="text" ng-model="ctrl.pet.name" name="name" id="name" placeholder="Name" class="form-control" required ng-minlength="2"/>					
				<div class="form-group">
					<label for="status">Status :</label>
					<input type="text" name="status" ng-model="ctrl.pet.status" id="status" class ="form-control" placeholder="Status">
				</div>
				<div class="form-group">
					 <div class="form-actions"> 
					 	<input type="submit"  value="Update Pet" class="btn btn-primary btn-md" >
					  </div> 
				</div> 
				</div>
			</form>

		</div>    
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
      <script src="<c:url value='/resources/js/app.js' />"></script>
      <script src="<c:url value='/resources/js/authInterceptor.js' />"></script>
      <script src="<c:url value='/resources/js/service/pet_service.js' />"></script>
      <script src="<c:url value='/resources/js/controller/pet_controller.js' />"></script>
  </body>
</html>