<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp"/>
<html>
	<head>
		<title>Welcome!</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/></script>
		
		<style type="text/css">
		body {
			background-color:#e6e6e6;
		}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="col-xs-11 col-centered welcomeMessage">
				<p>Welcome <b>${username}!</b> Enjoy!</p>
				<br><br>
				<p><a href="<spring:url value="/resource/" />" >View Resources</a><p>
				<p><a href="<spring:url value="/incidents/" />" >View Incidents</a><p>
			</div>
		</div> 
	
	</body>
</html>