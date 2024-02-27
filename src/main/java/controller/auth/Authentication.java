package controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.UserBean;
import models.mappers.UserMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

		UserBean result = validateUser(email, password);
		boolean isValidUser = result != null;
		if (isValidUser) {
			// Create a new session for the user
			HttpSession session = request.getSession();
			// Set the user's information in the session
			session.setAttribute("user", result);
			// Redirect to a articles page after login
			response.sendRedirect("index.jsp");
		} else {
			// Handle invalid login attempt (redirect to login page with error, etc.)
			request.setAttribute("errorMessage", "Invalid username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private UserBean validateUser(String email, String password) {
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<UserBean> users = UserMapper.mapUsers(resultSet);
			preparedStatement.close();
			resultSet.close();
			if(users.size() == 0) {
				return null;
			}
			return users.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
