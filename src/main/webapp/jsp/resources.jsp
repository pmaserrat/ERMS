<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<title>Welcome!</title>
		<link rel="stylesheet" href="../css/global.css"/>
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
	<p>Welcome <b>${username}!</b> Enjoy!</p>
		<section class="container">
        		<p><a href="<spring:url value="/addResource/" />" > Add Resources for ${username}</a><p>
        		<div class="row">
					<p>List of Resources for <b>${username}!</b> Enjoy!</p>		
        			<c:forEach items="${resources}" var="resource">
        				<div class="" style="padding-bottom: 15px">
        					<div class="thumbnail">
        						<div class="caption">
        							<p>${resource.id} ${resource.name}<p>
        						</div>
        					</div>
        				</div>
        			</c:forEach>
        		</div>
        	</section>

	</body>
</html>