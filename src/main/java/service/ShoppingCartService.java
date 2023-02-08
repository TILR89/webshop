package service;

import dao.ShoppingCartDAO;
import entity.ShoppingCart;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService extends Util implements ShoppingCartDAO {

    Connection connection = getConnection();

    @Override
    public void add(ShoppingCart shoppingCart) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO shopping_cart (user_id, product_id, orders_order_id) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shoppingCart.getUser_id());
            preparedStatement.setInt(2, shoppingCart.getProduct_id());
            preparedStatement.setInt(3, shoppingCart.getOrders_order_id());

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
    public List<ShoppingCart> getAll() throws SQLException {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();

        String sql = "SELECT user_id, product_id, order_order_id FROM shopping_cart";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();

                shoppingCart.setUser_id(resultSet.getInt("user_id"));
                shoppingCart.setProduct_id(resultSet.getInt("product_id"));
                shoppingCart.setOrders_order_id(resultSet.getInt("orders_order_id"));

                shoppingCartList.add(shoppingCart);
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
        return shoppingCartList;
    }

    @Override
    public ShoppingCart getById(int userId, int orderId) {
        return null;
    }

    @Override
    public void update(ShoppingCart shoppingCart) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE shopping_cart SET user_id=?, product_id = ? WHERE orders_order_id= ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shoppingCart.getUser_id());
            preparedStatement.setInt(2, shoppingCart.getProduct_id());

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
    public void remove(ShoppingCart shoppingCart) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM shopping_cart WHERE orders_order_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shoppingCart.getOrders_order_id());

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
}
