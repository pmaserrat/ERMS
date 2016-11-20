<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<title>Welcome!</title>
	<!--  <link rel="stylesheet" href="../css/global.css" /> -->
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<!--<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
		-->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" /></script> 
		<script>
			$(document).ready(function() {
				$('#btn').click(function() {
					var val = $("#capabilities").val();
					var htm = '';
					htm += '<option selected = "selected" >' + val + '</option>';
					$('.multiselect-container').append(htm);
					$('#capability').append(htm);
				});
				$('#capability').multiselect({
					includeSelectAllOption : true
				});
				$('#capability').prop('selected', true);
		
			});
		</script>
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
				<p>Add Resource</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 formPanel col-centered">
				<form action="add" method="POST">
					<div class="form-group">
						<label for="resourceName">Resource Name:</label> 
						<input type="text" name="resourceName" class="form-control"	id="resourceName">
					</div>
					<div class="form-group">
						<label for="resourceOwner">Resource Owner: ${username}</label>
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
					<div class="form-group">
						<label for="AdditionalESF">Additional ESF:</label>
						<select	name="AdditionalESF" id="AdditionalESF" multiple>
							<c:forEach items="${esfs}" var="esf">
								<option value="${esf.number}">${esf.description}</option>
							</c:forEach>
						</select>
					</div>
					<hr>
					<div class="form-group">
						<label for="capabilities">Capabilities:</label> 
						<input type="text" id="capabilities" /> 
						<input type="button" id="btn" value="Add" /> 
						<br> <br>
						<select  id="capability"  name="capability" multiple></select>
					</div>
					<hr>
					<div class="form-group">
						<label for="model">Model:</label> 
						<input type="text" name="model" class="form-control" id="model">
					</div>
					<hr>
					<label class="form-group ">Home Location </label>
						<div class="form-group ">
							<label for="Lat">Lat:</label> 
							<input type="text" name="Lat" class="form-control" id="lat">
						</div>
						<div class="form-group ">
							<label for="Long">Long:</label> 
							<input type="text" name="Long" class="form-control" id="long">
						</div>
					<hr>
					<div class="form-group">
						<label for="Cost">Cost:</label> 
						<input type="text" name="cost" id="cost" placeholder="cost"> <label for="Cost">Per</label>
						<select name="Unit" id="Unit">
							<c:forEach items="${units}" var="unit">
								<option value="${unit.unit}">${unit.unit}</option>
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