package service;

import dao.ProductsDAO;
import entity.Products;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsService extends Util implements ProductsDAO {

    Connection connection = getConnection();

    @Override
    public void add(Products products) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO products.xml (product_name, product_price, product_description, supplier_id) VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, products.getProduct_name());
            preparedStatement.setDouble(2, products.getProduct_price());
            preparedStatement.setString(3, products.getProduct_description());
            preparedStatement.setInt(4, products.getSupplier_id());

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
    public List<Products> getAll() throws SQLException {
        List<Products> productsList = new ArrayList<>();

        String sql = "SELECT product_id, product_name, product_price, product_description, supplier_id FROM products.xml";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Products products = new Products();

                products.setProduct_id(resultSet.getInt("product_id"));
                products.setProduct_name(resultSet.getString("product_name"));
                products.setProduct_price(resultSet.getDouble("product_price"));
                products.setProduct_description(resultSet.getString("product_description"));
                products.setSupplier_id(resultSet.getInt("supplier_id"));

                productsList.add(products);
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
        return productsList;
    }

    @Override
    public Products getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT product_id, product_name, product_price, product_description, supplier_id FROM products.xml WHERE product_id = ?";

        Products products = new Products();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            products.setProduct_id(resultSet.getInt("product_id"));
            products.setProduct_name(resultSet.getString("product_name"));
            products.setProduct_price(resultSet.getDouble("product_price"));
            products.setProduct_description(resultSet.getString("product_description"));
            products.setSupplier_id(resultSet.getInt("supplier_id"));

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
            return products;
        }
    }

    @Override
    public void update(Products products) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE products.xml SET product_name =?, product_price = ?, product_description=?, supplier_id=? WHERE product_id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, products.getProduct_name());
            preparedStatement.setDouble(2, products.getProduct_price());
            preparedStatement.setString(3, products.getProduct_description());
            preparedStatement.setInt(4, products.getSupplier_id());

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
    public void remove(Products products) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM products.xml WHERE product_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, products.getProduct_id());

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
