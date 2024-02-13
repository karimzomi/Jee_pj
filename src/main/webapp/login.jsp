<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row align-items-center" style="height: 100vh;">
			<div class="col-md-4 offset-md-4 shadow p-3 mb-5 bg-body rounded">
				<h1 class="text-center">Login</h1>
				<form action="login" method="post">
					<div class="mb-3">
						<label for="inputEmail" class="form-label">Email
							address</label> <input type="email" name="email" class="form-control"
							id="inputEmail" aria-describedby="emailHelp" required>
					</div>
					<div class="mb-3">
						<label for="inputPassword" class="form-label">Password</label>
						<input type="password" name="password" class="form-control"
							id="inputPassword">
					</div>
					<%
					if (request.getAttribute("errorMessage") != null) {
					%>
					<div class="text-danger">
						<%=request.getAttribute("errorMessage")%>
					</div>
					<%
					}
					%>
					<div class="d-grid gap-2 col-6 mx-auto">
						<button class="btn btn-primary" type="submit">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>