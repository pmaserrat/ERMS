<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp"/>
<html>
	<head>
		<title>Welcome!</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/></script>
		
		<style type="text/css">
		body {
			background-color:#e6e6e6;
		}
		</style>
	</head>
	<body>
		<div class="row header">
			<div class="col-xs-12">
				<div class="col-xs-2">
		 			<form action="mainMenu" method="POST">
		   				<a href="#" onclick="$(this).closest('form').submit()">Back to Main Menu</a>
					</form>
				</div>
				<div class="col-xs-8 text-center">
					Emergency Resource Management System
				</div>
				<div class="col-xs-2 pull-right">
					<form action="Logout" method="POST">
		   				<a href="#" onclick="$(this).closest('form').submit()">Logout</a>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-centered welcomeMessage">
				<p>Welcome <b>${userName}!</b></p>
			</div>
			<div class="row">
				<div class="col-xs-4 formPanel col-centered">
					<p class="font30 text-center"><b>Main Menu</b></p>
					<hr>
					<div class="form-group ">
						<p class="font20 text-center"><a href="<spring:url value="/resource/" />" >View, Manage and Add Resources</a></p>
					</div>
					<div class="form-group ">
						<p class="font20 text-center"><a href="<spring:url value="resource/manage" />" >View Requests Status</a></p>
					</div>
					<div class="form-group ">
						<p class="font20 text-center"><a href="<spring:url value="/incidents/" />" >View and Add Incidents</a></p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>