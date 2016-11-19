<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="row">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Add Resource</p>
			</div>
			<div class="col-xs-8 col-centered formPanel">
				<form action="add" method="POST">
					<div class="row">
						<div class="col-xs-12">
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
			</div>
		</div> 
	</div>
	</body>
</html>