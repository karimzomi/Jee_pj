package controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.UserBean;
import models.DAO.UserDAO;

import java.io.IOException;

/**
 * Servlet implementation class Authentication
 */
@WebServlet(urlPatterns = "/login")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public Authentication() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		userDAO = new UserDAO(getServletContext());
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

		UserBean result = userDAO.validateUser(email, password);
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

}
