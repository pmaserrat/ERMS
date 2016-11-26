<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
	<jsp:include page="header.jsp" />
	<div class="row header">
		<div class="col-xs-12">
			<div class="col-xs-2">
				<form action="../mainMenu" method="POST">
					<a href="#" onclick="$(this).closest('form').submit()">Back to
						Main Menu</a>
				</form>
			</div>
			<div class="col-xs-8 text-center">Emergency Resource Management
				System</div>
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
				<p class="font30 text-center">
					Requests Status for <b>${username}!</b>
				</p>
			</div>
			<br>
			<br>
			<p>Requests submitted by me</p>
			<table class="table">
				<tr>
					
					<th>Resource</th>
					<th>Incident</th>
					<th>requestDate</th>
					<th>returnDate</th>
					<th>Owner</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${requests}" var="request">
					 <c:if test="${request.submitter  == username  && request.status == 'PENDING'}"> 
						<tr>
							
							<td>${request.resourceName}</td>
							<td>${request.incidentDescription}</td>
							<td>${request.requestDate}</td>
							<td>${request.returnDate}</td>
							<td>${request.resourceOwner}</td>
							<td>${request.status}</td>
							<td>
							<form class="form-group" action="cancel" method="POST">
								<div class="form-group row">
									<input type="hidden" id="resource" name="resource" value="${request.resourceID}">
									<input type="hidden" id="incident" name="incident" value="${request.incidentID}">
									<button type="submit" class="btn btn-default pull-right">Cancel</button>
								</div>
							</form>
							</td>
						</tr>
					 </c:if> 
				</c:forEach>
			</table>

			<p>Requested Resources</p>
			<table class="table">
				<tr>
					
					<th>Resource</th>
					<th>Incident</th>
					<th>requestDate</th>
					<th>returnDate</th>
					<th>submitter</th>
					<th>Status</th>
					<th>Action</th>

				</tr>
				<c:forEach items="${requests}" var="request">
					 <c:if test="${request.resourceOwner == username && request.status == 'PENDING'}"> 
						<tr>
							<td>${request.resourceName}</td>
							<td>${request.incidentDescription}</td>
							<td>${request.requestDate}</td>
							<td>${request.returnDate}</td>
							<td>${request.submitter}</td>
							<td>${request.resourceStatus}</td>
							<td>
							<c:if test="${request.resourceStatus == 'Ready'}">
							<form class="form-group" action="accept" method="POST">
								<div class="form-group row">
									<input type="hidden" id="resource" name="resource" value="${request.resourceID}">
									<input type="hidden" id="incident" name="incident" value="${request.incidentID}">
									<button type="submit" class="btn btn-default pull-right">Accept</button>
								</div>
							</form>
							</c:if>
							<form class="form-group" action="reject" method="POST">
								<div class="form-group row">
									<input type="hidden" id="resource" name="resource" value="${request.resourceID}">
									<input type="hidden" id="incident" name="incident" value="${request.incidentID}">
									<button type="submit" class="btn btn-default pull-right">Reject</button>
								</div>
							</form>
							</td>
						</tr>
				 	</c:if> 
				</c:forEach>
			</table>
			
			<p>Past Requsts Resources</p>
			<table class="table">
				<tr>
					
					<th>Resource</th>
					<th>Incident</th>
					<th>requestDate</th>
					<th>returnDate</th>
					<th>Owner</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${requests}" var="request">
					 <c:if test="${request.submitter  == username  && request.status != 'PENDING'}"> 
						<tr>
							
							<td>${request.resourceName}</td>
							<td>${request.incidentDescription}</td>
							<td>${request.requestDate}</td>
							<td>${request.returnDate}</td>
							<td>${request.resourceOwner}</td>
							<td>${request.status}</td>

						</tr>
					 </c:if> 
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>