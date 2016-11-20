<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
		<jsp:include page="header.jsp" />
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
				<p>Add Incident</p>
			</div>
			<div class="row">
				<div class="col-xs-6 formPanel col-centered">
					<form action="add" method="POST">
						<div class="form-group ">
							<label class="control-label" for="Date">Date:</label>
	        				<input class="form-control" id="Date" name="Date" placeholder="MM/DD/YYY" type="date"/>
						</div>
						<hr>
						<div class="form-group ">
							<label for="description">Description:</label> 
							<input type="text" name="description" class="form-control" id="description">
						</div>
						<hr>
						<label class="form-group ">Location </label>
						<div class="form-group ">
							<label for="Lat">Lat:</label> 
							<input type="text" name="Lat" class="form-control" id="Lat">
						</div>
						<div class="form-group ">
							<label for="Long">Long:</label> 
							<input type="text" name="Long" class="form-control" id="Long">
						</div>
						<hr>
						<div class="submitDiv">
							<button type="submit" class="btn btn-default pull-right">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>