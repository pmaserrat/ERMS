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
					<p class="font30 text-center">Incidents for <b>${username}!</b></p>
				</div>	
				<br><br>
				<table class="table">
				    <tr>
				        <th>ID</th>
				        <th>Date</th>
				        <th>Location</th>
				        <th>Description</th>
				        <th>Action</th>
				    </tr>	
        			<c:forEach items="${incidents}" var="incident">
				        <tr>
				            <td>${incident.ID}</td>
				            <td>${incident.date}</td>
				            <td>(${incident.latitude}, ${incident.longitude})</td>
				            <td>${incident.description}</td>
				             <td> 
				             <form class="form-group" action="repair" method="POST">
				             <div class="form-control row">
				              <input type="hidden" id="incident" name="incident" value="${incident.ID}">
								 <select name="ResourceID" id="ResourceID" multiple>
									<c:forEach items="${resources}" var="resource">
										<option value="${resource.ID}">${resource.name}</option>
									</c:forEach>
								</select>
								<button type="submit" class="btn btn-default pull-right">Send Resource</button>
								</div>
				             </form>
				             </td>
				        </tr>
					</c:forEach>
				</table>
       		</div>
       		<div class="row margin-b10">
        		<a class="font20" href="<spring:url value="addIncident" />" > Add new Incident for ${username}</a>
        	</div>
        </div>	
	</body>
</html>