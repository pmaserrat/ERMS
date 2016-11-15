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
		<div class="row">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Add Resource</b> Enjoy!</p>
				<br><br>
				<form action="add" method="POST">
					<div class="row">
						<div class="col-xs-11 col-centered">
							<div class="form-group">
								<label for="resourceName">Resource Name:</label>
								<input type="text" name="resourceName" class="form-control" id="resourceName">
							</div>
							<div class="form-group">
								<label for="resourceOwner">Resource Owner: </label>
								<p>${username}</p>
							</div>
							<div class="form-group">
								<label for="PrimaryESF">Primary ESF:</label>
								<select name="PrimaryESF" class="form-control" id="resourceName">
    									<c:forEach items="${esfs}" var="esf">
    										<option value="${esf.number}">${esf.description}</option>
										</c:forEach>
  								</select>
								<div class="form-group">
									<label for="model">model:</label>
									<input type="text" name="model" class="form-control" id="model">
								</div>
							</div>
							
							<button type="submit" class="btn btn-default pull-right">Submit</button>
						 </div>
					</div>
				</form>
				<form action="index.jsp">
   					<input type="submit" value="Logout" />
				</form>
			</div>
		</div> 
	
	</body>
</html>