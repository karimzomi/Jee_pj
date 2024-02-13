<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.sql.Connection, java.sql.Statement, java.sql.ResultSet"%>
<%
Connection connection = (Connection) application.getAttribute("DB_CONNECTION");
Statement statement = connection.createStatement();
ResultSet resultSet = statement
		.executeQuery("SELECT users.username,posts.post FROM users,posts where posts.user_id = users.id;");
%>
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
<title>Posts</title>
</head>
<body>
	<div class="container my-2">
		<div class="d-flex flex-column">

			<%
			while (resultSet.next()) {
				out.print("<div class=\"card my-1\">");
				out.print("<div class=\"card-header\">" + resultSet.getString("username") + "</div>");
				out.print("<div class=\"card-body\">");
				out.print("<p class=\"card-text\">" + resultSet.getString("post") + "</p>");
				out.print("</div>");
				out.print("</div>");
			}
			%>

			<form method="POST" action="posts" class="my-4">
				<div class="form-floating">
					<textarea name="post" class="form-control"
						placeholder="Leave your post here" id="floatingTextarea"
						style="height: 100px"></textarea>
					<label for="floatingTextarea2">Your post</label>
				</div>
				<div class="d-flex justify-content-end py-1">
					<button type="submit" class="btn btn-primary px-4">Post</button>
				</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>