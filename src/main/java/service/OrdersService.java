package service;

import dao.OrdersDAO;
import entity.Orders;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersService extends Util implements OrdersDAO {

    Connection connection = getConnection();
    @Override
    public void add(Orders orders) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO orders (user_id, user_name, user_email) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orders.getOrder_id());
            preparedStatement.setString(2, orders.getUser_name());
            preparedStatement.setString(3, orders.getUser_email());

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
    public List<Orders> getAll() throws SQLException {
        List<Orders> ordersList = new ArrayList<>();

        String sql = "SELECT order_id, user_id, user_name, user_email FROM orders";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setOrder_id(resultSet.getInt("order_id"));
                orders.setUser_id(resultSet.getInt("user_id"));
                orders.setUser_name(resultSet.getString("user_name"));
                orders.setUser_email(resultSet.getString("user_email"));

                ordersList.add(orders);
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
        return ordersList;
    }

    @Override
    public Orders getById(int order_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT order_id, user_id, user_name, user_email FROM orders WHERE order_id = ?";

        Orders orders = new Orders();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            orders.setOrder_id(resultSet.getInt("order_id"));
            orders.setUser_id(resultSet.getInt("user_id"));
            orders.setUser_name(resultSet.getString("user_name"));
            orders.setUser_email(resultSet.getString("user_email"));

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
            return orders;
        }
    }

    @Override
    public void update(Orders orders) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE orders SET user_id = ? WHERE order_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, orders.getUser_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {if (preparedStatement != null) {
            preparedStatement.close();
        }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void remove(Orders orders) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM orders WHERE order_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orders.getOrder_id());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {if (preparedStatement != null) {
            preparedStatement.close();
        }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
