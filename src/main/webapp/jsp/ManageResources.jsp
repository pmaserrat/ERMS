<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row margin-b10">
			<a href="<spring:url value="/addResource/" />">Add Resources for
				${username}</a>
		</div>
		<div class="row">
			<p>Resources In use</p>
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
				<c:forEach items="${deployedResources}" var="deployed">
						<tr>
							<td>${deployed.ID}</td>
							<td>${deployed.name}</td>
							<td>(${deployed.latitude}, ${deployed.longitude})</td>
							<td>${deployed.status}</td>
							<td>${deployed.nextAvailableDate}</td>
							<td>${deployed.amount}/ ${deployed.costTimeUnit}</td>
							<td>${deployed.model}</td>
						</tr>

				</c:forEach>
			</table>

			<p>Resources In Repair</p>
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
				<c:forEach items="${inRepairs}" var="inRepair">
						<tr>
							<td>${inRepair.ID}</td>
							<td>${inRepair.name}</td>
							<td>(${inRepair.latitude}, ${inRepair.longitude})</td>
							<td>${inRepair.status}</td>
							<td>${inRepair.nextAvailableDate}</td>
							<td>${inRepair.amount}/ ${inRepair.costTimeUnit}</td>
							<td>${inRepair.model}</td>
						</tr>
				</c:forEach>
			</table>

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