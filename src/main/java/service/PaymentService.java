package service;

import dao.PaymentDAO;
import entity.Payment;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService extends Util implements PaymentDAO {
    Connection connection = getConnection();

    @Override
    public void add(Payment payment) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO payment (payment_date, orders_order_id) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, payment.getPayment_date());
            preparedStatement.setInt(2,payment.getOrders_order_id());

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
    public List<Payment> getAll() throws SQLException {
        List<Payment> paymentList = new ArrayList<>();

        String sql = "SELECT payment_id, payment_date, orders_order_id FROM payment";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPayment_id(resultSet.getInt("payment_id"));
                payment.setPayment_date(resultSet.getTimestamp("payment_date"));
                payment.setOrders_order_id(resultSet.getInt("orders_order_id"));

                paymentList.add(payment);
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
        return paymentList;
    }

    @Override
    public Payment getById(int payment_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT payment_id, payment_date, users_user_id FROM payment WHERE payment_id = ?";

        Payment payment = new Payment();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, payment_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            payment.setPayment_id(resultSet.getInt("payment_id"));
            payment.setPayment_date(resultSet.getTimestamp("payment_date"));
            payment.setOrders_order_id(resultSet.getInt("orders_order_id"));

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
            return payment;
        }
    }

    @Override
    public void update(Payment payment) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE payment SET orders_order_id = ? WHERE payment_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, payment.getPayment_id());

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
    public void remove(Payment payment) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM payment WHERE payment_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, payment.getPayment_id());

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
