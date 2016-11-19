<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Welcome!</title>
<!--  <link rel="stylesheet" href="../css/global.css" /> -->
   <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!--<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	-->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" /></script> 
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

<style type="text/css">
body {
	background-color: #e6e6e6;
}
</style>
</head>
<body>
	<div>
		<div class="col-xs-11 col-centered welcomeMessage">
			<p>
				Add Resource</b> Enjoy!
			</p>
			<div>
				<form class="form-group" action="add" method="POST">
					<div class="row">
						<div class="col-xs-11 col-centered">
							<div class="form-group row">
								<label for="resourceName">Resource Name:</label> <input
									type="text" name="resourceName" class="form-control"
									id="resourceName">
							</div>
							<div>
								<label for="resourceOwner">Resource Owner: ${username}</label>
							</div>
							<div class="form-control row">
								<label for="PrimaryESF">Primary ESF:</label> <select
									name="PrimaryESF" id="primaryEsf">
									<c:forEach items="${esfs}" var="esf">
										<option value="${esf.number}">${esf.description}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-control row">
								<label for="Addtional">Additional ESF:</label> <select
									name="Addriontal" id="Addriontal" multiple>
									<c:forEach items="${esfs}" var="esf">
										<option value="${esf.number}">${esf.description}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-control row">
								<label for="Capabilitties">Capabilitties</label> <input
									type="text" id="capabilities" /> 
									<input type="button" id="btn" value="Add" /> <br> <br>
									 <select  id="capability"  name="capability" multiple>
								</select>
							</div>
							<div class="form-group row">
								<label for="model">model:</label> <input type="text"
									name="model" class="form-control" id="model">
							</div>

							<div class="form-group row">
								<label for="location">Home Location:</label> <input type="text"
									name="Lat" id="lat" placeholder="lat"> <input
									type="text" name="Long" id="long" placeholder="long">
							</div>

							<div class="form-group row">
								<label for="Cost">Cost$</label> <input type="text" name="cost"
									id="cost" placeholder="cost"> <label for="Cost">Per</label>
								<select name="Unit" id="Unit">
									<c:forEach items="${units}" var="unit">
										<option value="${unit.unit}">${unit.unit}</option>
									</c:forEach>
								</select>

							</div>



							<button type="submit" class="btn btn-default pull-right">Submit</button>
						</div>
					</div>
				</form>
			</div>
			<form action="index.jsp">
				<input type="submit" value="Logout" />
			</form>
		</div>
	</div>

</body>
</html>