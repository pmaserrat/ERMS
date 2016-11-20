<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
	<jsp:include page="header.jsp"/>
		<div class="container">
			<div class="row margin-b10">
        		<a href="<spring:url value="addIncident" />" > Add Incident for ${username}</a>
        	</div>
       		<div class="row">
				<p>List of Incidents for <b>${username}!</b> Enjoy!</p>	
				<table class="table">
				    <tr>
				        <th>ID</th>
				        <th>Date</th>
				        <th>Location</th>
				        <th>Description</th>
				    </tr>	
        			<c:forEach items="${incidents}" var="incident">
				        <tr>
				            <td>${incident.ID}</td>
				            <td>${incident.date}</td>
				            <td>(${incident.latitude}, ${incident.longitude})</td>
				            <td>${incident.description}</td>
				        </tr>
					</c:forEach>
				</table>
       		</div>
        </div>	
	</body>
</html>