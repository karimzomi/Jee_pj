package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Posts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private static final String insertQuery = "INSERT INTO posts(user_id, post) VALUES (?, ?)";

    public Posts() {
        super();
      
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		connection = (Connection) getServletContext().getAttribute("DB_CONNECTION");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = (Integer)request.getAttribute("id");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, request.getParameter("post"));
			preparedStatement.executeUpdate();
			
			this.doGet(request,response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
