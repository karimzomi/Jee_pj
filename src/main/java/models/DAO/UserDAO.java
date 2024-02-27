package models.DAO;

import jakarta.servlet.ServletContext;
import models.UserBean;
import models.mappers.UserMapper;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private final Connection connection;

    private static final String validationQuery = "SELECT * FROM USERS WHERE email = ? and password = ?";
    private static final String registerQuery = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)";
    private static final String getByRoleQuery = "SELECT * FROM users WHERE role = ?";

    public UserDAO(ServletContext servletContext) {
        this.connection = (Connection) servletContext.getAttribute("DB_CONNECTION");
    }

    public UserBean validateUser(String email, String password) {

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(validationQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<UserBean> users = UserMapper.mapUsers(resultSet);
            preparedStatement.close();
            resultSet.close();
            if (users.size() == 0) {
                return null;
            }
            return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserBean saveUser(UserBean user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(registerQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserBean> getUsersByRole(String role) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByRoleQuery);
            preparedStatement.setString(1, role);
            ArrayList<UserBean> users = UserMapper.mapUsers(preparedStatement.executeQuery());
            preparedStatement.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
