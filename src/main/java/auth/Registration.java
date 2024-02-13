package auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Registration
 */
@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	private static final String registerQuery = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		connection = (Connection) getServletContext().getAttribute("DB_CONNECTION");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(registerQuery);
			preparedStatement.setString(1, request.getParameter("username"));
			preparedStatement.setString(2, request.getParameter("password"));
			preparedStatement.setString(3, request.getParameter("email"));

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
