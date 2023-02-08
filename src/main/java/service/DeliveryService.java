package service;

import dao.DeliveryDAO;
import entity.Delivery;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService extends Util implements DeliveryDAO {

    Connection connection = getConnection();

    @Override
    public void add(Delivery delivery) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO delivery (delivery_status) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, delivery.getDelivery_status());

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
    public List<Delivery> getAll() throws SQLException {
        List<Delivery> deliveryList = new ArrayList<>();

        String sql = "SELECT delivery_id, delivery_status, users_user_id FROM delivery";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Delivery delivery = new Delivery();
                delivery.setDelivery_id(resultSet.getInt("delivery_id"));
                delivery.setDelivery_status(resultSet.getString("delivery_status"));
                delivery.setUsers_user_id(resultSet.getInt("users_user_id"));

                deliveryList.add(delivery);
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
        return deliveryList;
    }

    @Override
    public Delivery getById(int delivery_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT delivery_id, delivery_status, users_user_id FROM delivery WHERE delivery_id = ?";

        Delivery delivery = new Delivery();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, delivery_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            delivery.setDelivery_id(resultSet.getInt("delivery_id"));
            delivery.setDelivery_status(resultSet.getString("delivery_status"));
            delivery.setUsers_user_id(resultSet.getInt("users_user_id"));

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
            return delivery;
        }
    }

    @Override
    public void update(Delivery delivery) throws SQLException {
            PreparedStatement preparedStatement = null;

            String sql = "UPDATE delivery SET delivery_status = ? WHERE delivery_id = ?";

            try {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, delivery.getDelivery_status());

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
    public void remove(Delivery delivery) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM delivery WHERE delivery_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, delivery.getDelivery_id());

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
