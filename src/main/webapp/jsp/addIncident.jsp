<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
	

		<div class="col-xs-8 col-centered formPanel">
			<form action="add" method="POST">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Add Incident</p>
			</div>
			
			
				<div class="row">
					<label class="control-label" for="Date">Date</label>
        			<input class="form-control" id="Date" name="Date" placeholder="MM/DD/YYY" type="date"/>
				</div>
				
				<div class="row">
					<label for="description">Description:</label> 
					<input type="text" name="description" class="form-control" id="description">
				</div>
				<label>Location </label>
				<div class="row">
					<label for="Lat">Lat:</label> 
					<input type="text" name="Lat" class="form-control" id="Lat">
				</div>
				
				<div class="row">
					<label for="Long">Long:</label> 
					<input type="text" name="Long" class="form-control" id="Long">
				</div>
				
					<div class="row">
						<div class="col-xs-12">
							<button type="submit" class="btn btn-default pull-right">Submit</button>
						</div>
					</div>
				</form>
			</div>
		</div>
</body>
</html>