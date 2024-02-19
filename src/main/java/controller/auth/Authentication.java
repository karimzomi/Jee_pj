package controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Servlet implementation class Authentication
 */
@WebServlet(urlPatterns = "/login")
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		HashMap<String, String> result = validateUser(email, password);
		boolean isValidUser = result.get("id") != null;
		if (isValidUser) {
			// Create a new session for the user
			HttpSession session = request.getSession();
			// Set the user's information in the session
			session.setAttribute("email", email);
			session.setAttribute("id", result.get("id"));
			session.setAttribute("role", result.get("role"));

			// Redirect to a articles page after login
			response.sendRedirect("index.jsp");
		} else {
			// Handle invalid login attempt (redirect to login page with error, etc.)
			request.setAttribute("errorMessage", "Invalid username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private HashMap<String, String> validateUser(String email, String password) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("email").equals(email)) {
					result.put("id", resultSet.getString("id"));
					result.put("role", resultSet.getString("role"));
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
