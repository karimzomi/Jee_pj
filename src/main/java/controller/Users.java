package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserBean;
import models.mappers.UserMapper;

@WebServlet("/users")
public class Users extends HttpServlet{
    private Connection connection;
	private static final String query = "SELECT * FROM users WHERE role = 'user'";

    public Users() {
        super();
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		connection = (Connection) getServletContext().getAttribute("DB_CONNECTION");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ArrayList<UserBean> users = UserMapper.mapUsers(preparedStatement.executeQuery());
            request.setAttribute("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
		dispatcher.forward(request, response);
	}

}
