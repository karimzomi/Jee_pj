package auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private static final String query = "SELECT * FROM USERS WHERE email = ? and password = ?";

	public Authentication() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		connection = (Connection) getServletContext().getAttribute("DB_CONNECTION");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int result = validateUser(email, password);
		boolean isValidUser = result != -1;
		if (isValidUser) {
			// Create a new session for the user
			HttpSession session = request.getSession();
			// Set the user's information in the session
			session.setAttribute("email", email);
			session.setAttribute("id",result);

			// Redirect to a posts page after login
			response.sendRedirect("posts.jsp");
		} else {
			// Handle invalid login attempt (redirect to login page with error, etc.)
			request.setAttribute("errorMessage", "Invalid username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private int validateUser(String email, String password) {
		int result = -1;
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("email").equals(email)) {
					result = resultSet.getInt("id");
				}
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
