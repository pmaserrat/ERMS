<html>
	<head>
		<title>Main Menu!</title>
		<link rel="stylesheet" href="/css/global.css"/>
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
				<p>Welcome <b>${username}!</b></p>
				<br><br>
				<form action="index.jsp">
   					<input type="submit" value="Logout" />
				</form>
			</div>
		</div> 
	
	</body>
</html>