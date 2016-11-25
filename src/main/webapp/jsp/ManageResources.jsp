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
		<div class="row margin-b10">
		</div>
		<div class="row">
			<p>Requests submitted by me</p>
			<table class="table">
				<tr>
					<th>ID</th>
					<th>incidentID</th>
					<th>resourceID</th>
					<th>requestDate</th>
					<th>requestDate</th>
					<th>returnDate</th>
					<th>submitter</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${resources}" var="request">
					<c:if test="${request.submitter} eq ${username} ">
						<tr>
							<td>${resource.ID}</td>
							<td>${resource.incidentID}</td>
							<td>${resource.resourceID}</td>
							<td>${resource.requestDate}</td>
							<td>${resource.returnDate}</td>
							<td>${resource.resourceOwner}</td>
							<td>${resource.submitter}</td>
							<td>${resource.Status}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

			<p>Requested Resources</p>
			<table class="table">
				<tr>
					<th>ID</th>
					<th>incidentID</th>
					<th>resourceID</th>
					<th>requestDate</th>
					<th>requestDate</th>
					<th>returnDate</th>
					<th>submitter</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${resources}" var="request">
					<c:if test="${request.resourceOwner} eq ${username} ">
						<tr>
							<td>${resource.ID}</td>
							<td>${resource.incidentID}</td>
							<td>${resource.resourceID}</td>
							<td>${resource.requestDate}</td>
							<td>${resource.returnDate}</td>
							<td>${resource.resourceOwner}</td>
							<td>${resource.submitter}</td>
							<td>${resource.Status}</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>