<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
	<jsp:include page="header.jsp"/>
		<div class="container">
			<div class="row margin-b10">
        		<a href="<spring:url value="/addResource/" />" >Add Resources for ${username}</a>
        	</div>
       		<div class="row">
				<p>List of Resources for <b>${username}!</b> Enjoy!</p>	
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
        </div>
	</body>
</html>