package models.mappers;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.UserBean;

public class UserMapper {

    public static ArrayList<UserBean> mapUsers(ResultSet resultSet) {
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        try {
            while (resultSet.next()) {
                UserBean user = new UserBean();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setUsername(resultSet.getString("username"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
