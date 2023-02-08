package service;

import dao.ProductCategoriesDAO;
import entity.ProductCategories;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoriesService extends Util implements ProductCategoriesDAO {
    Connection connection = getConnection();

    @Override
    public void add(ProductCategories productCategories) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO product_categories (product_id, category_id) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productCategories.getProduct_id());
            preparedStatement.setInt(2, productCategories.getCategory_id());

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
    public List<ProductCategories> getAll() throws SQLException {
            List<ProductCategories> productCategoriesList = new ArrayList<>();

            String sql = "SELECT product_id, category_id FROM product_categories";

            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    ProductCategories productCategories = new ProductCategories();

                    productCategories.setProduct_id(resultSet.getInt("product_id"));
                    productCategories.setCategory_id(resultSet.getInt("category_id"));

                    productCategoriesList.add(productCategories);
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
            return productCategoriesList;
    }

    @Override
    public ProductCategories getById(int ProductId, int CategoryId) {
        return null;
    }

    @Override
    public void update(ProductCategories productCategories) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE product_categories SET category_id WHERE product_id= ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productCategories.getCategory_id());

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
    public void remove(ProductCategories productCategories) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM products_categories WHERE product_id= ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productCategories.getProduct_id());

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
