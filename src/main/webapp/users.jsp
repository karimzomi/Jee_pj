<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cu" uri="WEB-INF/TableTag.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Users Management</title>
</head>
<body>

	<jsp:useBean id="users" class="java.util.ArrayList" scope="request" />
	<div class="container my-2">
		<div class="d-flex flex-column">
			<cu:CTable columns="id,email,role,username" data="${users}"/>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>