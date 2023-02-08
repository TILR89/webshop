package service;

import dao.OrderHistoryDAO;
import entity.OrderHistory;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryService extends Util implements OrderHistoryDAO {

    Connection connection = getConnection();
    @Override
    public void add(OrderHistory orderHistory) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO order_history (history_time, history_status, orders_order_id) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, orderHistory.getHistory_time());
            preparedStatement.setString(2, orderHistory.getHistory_status());
            preparedStatement.setInt(3, orderHistory.getOrders_order_id());

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
    public List<OrderHistory> getAll() throws SQLException {
        List<OrderHistory> orderHistoryList = new ArrayList<>();

        String sql = "SELECT order_id, history_time, history_status, orders_order_id FROM order_history";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrder_id(resultSet.getInt("order_id"));
                orderHistory.setHistory_time(resultSet.getTimestamp("history_time"));
                orderHistory.setHistory_status(resultSet.getString("history_status"));
                orderHistory.setOrders_order_id(resultSet.getInt("orders_order_id"));

                orderHistoryList.add(orderHistory);
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
        return orderHistoryList;
    }

    @Override
    public OrderHistory getById(int order_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT order_id, history_time, history_status, orders_order_id FROM order_history WHERE order_id = ?";

        OrderHistory orderHistory = new OrderHistory();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            orderHistory.setOrder_id(resultSet.getInt("order_id"));
            orderHistory.setHistory_time(resultSet.getTimestamp("history_time"));
            orderHistory.setHistory_status(resultSet.getString("history_status"));
            orderHistory.setOrders_order_id(resultSet.getInt("orders_order_id"));

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
            return orderHistory;
        }
    }

    @Override
    public void update(OrderHistory orderHistory) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE order_history SET history_status = ? WHERE order_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, orderHistory.getHistory_status());

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
    public void remove(OrderHistory orderHistory) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM order_history WHERE order_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderHistory.getOrder_id());

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
