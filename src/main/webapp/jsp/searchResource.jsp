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
			<div class="col-xs-6 formPanel col-centered padding10">
				<form action="add" method="POST">
					<div class="form-group">
						<label for="keyword">Keyword:</label> 
						<input type="text" name="keyword" class="form-control"	id="keyword">
					</div>
					<hr>
					<div class="form-group">
						<label for="PrimaryESF">Primary ESF:</label> 
						<select	name="PrimaryESF" id="primaryEsf">
							<c:forEach items="${esfs}" var="esf">
								<option value="${esf.number}">${esf.description}</option>
							</c:forEach>
						</select>
					</div>
					<hr>
					<label class="form-group ">Location </label>
						<div class="form-group ">
							<label for="distance">Within 
							<input type="text" name="distance" class="form-control" id="distance">
							 Kilometers of incident </label> 
						</div>
					<hr>
					<div class="form-group">
						<label for="incident">Incident:</label>
						<select	name="incident" id="incident" multiple>
							<c:forEach items="${incidents}" var="incident">
								<option value="${incident.number}">${incident.description}</option>
							</c:forEach>
						</select>
					</div>
					<hr>
					<div class="submitDiv">
						<button type="submit" class="btn btn-default pull-right">Submit</button>
					</div>
				</form>
			</div>
		</div> 
	</body>
</html>