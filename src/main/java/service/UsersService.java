package service;

import dao.UsersDAO;
import entity.Users;
import utils.ConnectionPool;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsersService extends Util  {

    ResourceBundle resource = ResourceBundle.getBundle("database");
    String dbUrl = resource.getString("dbUrl");
    String dbUser = resource.getString("dbUser");
    String dbPass = resource.getString("dbPass");

    ConnectionPool pool = new ConnectionPool(dbUrl, dbUser, dbPass, 2);
    Connection conn = null;

    public void add(Users users) throws SQLException {
        PreparedStatement preparedStatement = null;
        conn = pool.getConnection();
        String sql = "INSERT INTO users (user_name, user_email, user_password) VALUES (?, ?, ?)";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,users.getUser_name());
            preparedStatement.setString(2, users.getUser_email());
            preparedStatement.setString(3, users.getUser_password());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Users> getAll() throws SQLException {
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT user_id, user_name, user_email, user_password FROM users";

        conn = pool.getConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Users users = new Users();

                users.setUser_id(resultSet.getInt("user_id"));
                users.setUser_name(resultSet.getString("user_name"));
                users.setUser_email(resultSet.getString("user_email"));
                users.setUser_password(resultSet.getString("user_password"));

                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null)
                try{pool.returnConnection(conn);}
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersList;
    }

/*
    Connection connection = getConnection();

    @Override
    public void add(Users users) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO users (user_name, user_email, user_password) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,users.getUser_name());
            preparedStatement.setString(2, users.getUser_email());
            preparedStatement.setString(3, users.getUser_password());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Users> getAll() throws SQLException {
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT user_id, user_name, user_email, user_password FROM users";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Users users = new Users();

                users.setUser_id(resultSet.getInt("user_id"));
                users.setUser_name(resultSet.getString("user_name"));
                users.setUser_email(resultSet.getString("user_email"));
                users.setUser_password(resultSet.getString("user_password"));

                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return usersList;
    }

    @Override
    public Users getById(int id) {
        return null;
    }

    @Override
    public void update(Users users) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE users SET user_name=?, user_email=?, user_password=? WHERE user_id= ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, users.getUser_name());
            preparedStatement.setString(2, users.getUser_email());
            preparedStatement.setString(3, users.getUser_password());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void remove(Users users) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM users WHERE users_id= ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, users.getUser_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }


    }
 */
}
