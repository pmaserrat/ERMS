<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="row">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Add Incident</p>
			</div>
			<div class="col-xs-8 col-centered formPanel">
				<form action="add" method="POST">
					<div class="row">
						<div class="col-xs-12">

							
							<button type="submit" class="btn btn-default pull-right">Submit</button>
						 </div>
					</div>
				</form>
			</div>
		</div> 
	</div>
	</body>
</html>