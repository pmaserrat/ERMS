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
				<p class="font30 text-center">Resources for <b>${username}!</b></p>
			</div>
				<br><br>	
				<table class="table">
				    <tr>
				        <th>ID</th>
				        <th>Name</th>
				        <th>Location</th>
				        <th>Status</th>
				        <th>Next Available Date</th>
				        <th>Amount</th>
				        <th>Model</th>
				    </tr>	
        			<c:forEach items="${resources}" var="resource">
				        <tr>
				            <td>${resource.ID}</td>
				            <td>${resource.name}</td>
				            <td>(${resource.latitude}, ${resource.longitude})</td>
				            <td>${resource.status}</td>
				            <td>${resource.nextAvailableDate}</td>
				            <td>${resource.amount} / ${resource.costTimeUnit}</td>
				            <td>${resource.model}</td>
				        </tr>
					</c:forEach>
				</table>
       		</div>
       		<div class="row margin-b10">
        		<a class="font20" href="<spring:url value="/addResource/" />" >Add new Resources for ${username}</a>
        	</div>
        </div>
	</body>
</html>