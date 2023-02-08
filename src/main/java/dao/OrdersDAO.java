package dao;

import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO {
    //create
    void add(Orders orders) throws SQLException;

    //read
    List<Orders> getAll() throws SQLException;

    Orders getById(int id) throws SQLException;

    //update
    void update(Orders orders) throws SQLException;

    //delete
    void remove(Orders orders) throws SQLException;
}
