<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Home</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">JEE</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<jsp:useBean id="user" class="models.UserBean" scope="session" />
					<c:choose>
						<c:when test="${user.email == null}">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="login">Login</a></li>
							<li class="nav-item"><a class="nav-link" href="signup.html">Sign
									up</a></li>
						</c:when>
						<c:otherwise>
						<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Username: <jsp:getProperty name="user" property="username" /></a></li>
							</a></li>
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="articles">Articles</a></li>
							<c:if test="${user.role == 'admin'}">
								<li class="nav-item"><a class="nav-link" href="users">Users</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>