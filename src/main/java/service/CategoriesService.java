package service;

import dao.CategoriesDAO;
import entity.Categories;
import utils.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesService extends Util implements CategoriesDAO {

    Connection connection = getConnection();

    @Override
    public void add(Categories categories) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO categories (category_name) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categories.getCategory_name());

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
    public List<Categories> getAll() throws SQLException {
        List<Categories> categoriesList = new ArrayList<>();

        String sql = "SELECT category_id, category_name FROM categories";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Categories categories = new Categories();
                categories.setCategory_id(resultSet.getInt("category_id"));
                categories.setCategory_name(resultSet.getString("category_name"));

                categoriesList.add(categories);
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
        return categoriesList;
    }

    @Override
    public Categories getById(int category_id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT category_id, category_name FROM categories WHERE category_id = ?";

        Categories categories = new Categories();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, category_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            categories.setCategory_id(resultSet.getInt("category_id"));
            categories.setCategory_name(resultSet.getString("category_name"));

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
            return categories;
        }
    }

        @Override
        public void update(Categories categories) throws SQLException {
            PreparedStatement preparedStatement = null;

            String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";

            try {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, categories.getCategory_name());

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
    public void remove(Categories categories) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM categories WHERE category_id=?";
        try {preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categories.getCategory_id());

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
