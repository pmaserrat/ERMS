<head>
	<title>ERMS</title>
	<link rel="stylesheet" href="../css/global.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/></script>
</head>	
	<div class="row header">
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
