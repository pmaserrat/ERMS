<html>
	<body>
		<jsp:include page="jsp/loggedOutheader.jsp"/>
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