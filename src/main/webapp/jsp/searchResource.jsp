<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<title>Welcome!</title>
		<link rel="stylesheet" href="../css/global.css"/>
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
		<jsp:include page="header.jsp"/>
		<div class="row header">
			<div class="col-xs-12">
				<div class="col-xs-2">
		 			<form action="../mainMenu" method="POST">
		   				<a href="#" onclick="$(this).closest('form').submit()">Back to Main Menu</a>
					</form>
				</div>
				<div class="col-xs-8 text-center">
					Emergency Resource Management System
				</div>
				<div class="col-xs-2 pull-right">
					<form action="../Logout" method="POST">
		   				<a href="#" onclick="$(this).closest('form').submit()">Logout</a>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Search Resources</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-4 formPanel col-centered padding10">
				<form action="../searchResults/searchResults" method="GET">
					<div class="form-group">
						<label for="keyword">Keyword:</label> 
						<input type="text" name="keyword" class="form-control"	id="keyword">
					</div>
					<hr>
					<div class="form-group">
						<label for="PrimaryESF">Primary ESF:</label>
						<select	name="PrimaryESF" id="primaryEsf">
							<option value="">--Select ESF--</option>
							<c:forEach items="${esfs}" var="esf">
								<option value="${esf.number}">${esf.description}</option>
							</c:forEach>
						</select>
					</div>
					<hr>
						<div class="row ">
							<label class="form-group col-xs-1 margin-r10">Location:</label>
							<label class="col-xs-1 font-normal" for="distance">Within </label>
							<input type="text" name="distance" class="col-xs-3" id="distance" />
							<label class="col-xs-3 font-normal" for="distance">Kilometers of incident </label> 
						</div>
					<hr>
					<div class="form-group">
						<label for="incident">Incident:</label>
						<select	name="incident" id="incident">
							<option value="">--Select Incident--</option>
							<c:forEach items="${incidents}" var="incident">
								<option value="${incident.ID}=${incident.description}">(${incident.ID}) ${incident.description}</option>
							</c:forEach>
						</select>
					</div>
					<hr>
					<div class="submitDiv">
						<button type="submit" class="btn btn-default pull-right">Search</button>
					</div>
				</form>
				<form action="../mainMenu" method="POST">
					<div class="submitDiv">
						<button type="submit" class="btn btn-default pull-right" onclick="$(this).closest('form').submit()">Cancel</button>
					</div>
				</form>
			</div>
		</div> 
	</body>
</html>