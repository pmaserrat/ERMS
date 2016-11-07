<html>
	<head>
		<title>Login</title>
	</head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" /></script>
		<style type="text/css">
			body {
				background-color:#e6e6e6!important;
			}
			
			.col-centered{
			    float: none!important;
			    margin: 0 auto;
			}
			
			.loginPanel{
				margin-top:100px;
				padding:30px;
				background-color: #ffffff;
				box-shadow
				-webkit-box-shadow: 6px 5px 6px 0px rgba(0,0,0,0.75);
				-moz-box-shadow: 6px 5px 6px 0px rgba(0,0,0,0.75);
				box-shadow: 6px 5px 6px 0px rgba(0,0,0,0.75);
				border-radius:5px;
			}
			
			.loginBanner{
				margin-top: 70px;
				text-align: center;
				font-size: 40px;
			}
		</style>
	<body>
		<div class="row">
			<div class="col-xs-12 col-sm-5 col-centered loginBanner">
				Welcome! Please Login!	
			</div>
			<div class="col-xs-12 col-sm-5 col-centered loginPanel">
				<form action="welcome" method="POST">
					<div class="row">
						<div class="col-xs-11 col-centered">
							<div class="form-group">
								<label for="username">Username</label>
								<input type="text" name="username" class="form-control" id="username" placeholder="Username">
							</div>
							<div class="form-group">
								<label for="password">Password</label>
								<input type="password" name="password" class="form-control" id="password" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-default pull-right">Submit</button>
						 </div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>