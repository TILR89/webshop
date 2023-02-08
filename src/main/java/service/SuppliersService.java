package service;

import dao.SuppliersDAO;
import entity.Suppliers;
import utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliersService extends Util implements SuppliersDAO {

    Connection connection = getConnection();

    @Override
    public void add(Suppliers suppliers) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO suppliers (supplier_name) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, suppliers.getSupplier_name());

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
    public List<Suppliers> getAll() throws SQLException {

        List<Suppliers> suppliersList = new ArrayList<>();

        String sql = "SELECT supplier_id, supplier_name FROM suppliers";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Suppliers suppliers = new Suppliers();

                suppliers.setSupplier_id(resultSet.getInt("user_id"));
                suppliers.setSupplier_name(resultSet.getString("supplier_name"));

                suppliersList.add(suppliers);
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
        return suppliersList;
    }

    @Override
    public Suppliers getById(int id) {
        return null;
    }

    @Override
    public void update(Suppliers suppliers) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE suppliers SET supplier_name=? WHERE supplier_id= ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, suppliers.getSupplier_name());

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
    public void remove(Suppliers suppliers) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM suppliers WHERE supplier_id= ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, suppliers.getSupplier_id());

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
