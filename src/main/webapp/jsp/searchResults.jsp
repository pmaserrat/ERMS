<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
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
		<div class="container">
       		<div class="row">
       			<div class="col-xs-12 col-centered welcomeMessage">
				<p class="font30 text-center">Search Results!</p>
			</div>
			<div class="row">
				<div class="col-xs-12 col-left incidentMessage">
				<p class="font18 text-left">Search Results for Incident: (${incidentID}) ${incidentDes}
			</div>
				<br><br>
				<table class="table">
				    <tr>
				        <th>ID</th>
				        <th>Name</th>
				        <th>Owner</th>
				        <th>Status</th>
				        <th>Cost</th>
				        <th>Distance</th>
				        <th>Action</th>
				    </tr>	
        			<c:forEach items="${resources}" var="resource">
				        <tr>
				            <td>${resource.resourceID}</td>
				            <td>${resource.name}</td>
				            <td>${resource.username}</td>
				            <td>${resource.amount} / ${resource.costTimeUnit}</td>
				            <td>${resource.status}</td>
				              <td>${resource.amount} / ${resource.costTimeUnit}</td>
				            <td>${resource.model}</td>
				            <td> <c:if test="${resource.status != 'In Repair' && resource.username != username}">
								<form class="form-group" action="request" method="POST">
								<div class="form-group row">

									<input class="form-control" id="Date" name="Date" placeholder="MM/DD/YYY" type="date"/>
										<input type="hidden" id="resource" name="resource" value="${resource.resourceID}">
										<input type="hidden"id="incident" name="incident" value="${incidentID}">
										<input type="hidden"id="owner" name="owner" value="${resource.username}">
										<button type="submit" class="btn btn-default pull-right">Send
											Request</button>
								</div>
								</form>
							</c:if>

				             </td>
				        </tr>
					</c:forEach>
				</table>

       		<div class="row margin-b10">
        		<a class="font20" href="<spring:url value="/searchResource/" />" >Close</a>
        	</div>
        </div>
	</body>
</html>