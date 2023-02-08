package dao;

import entity.OrderHistory;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryDAO {
    //create
    void add(OrderHistory orderHistory) throws SQLException;

    //read
    List<OrderHistory> getAll() throws SQLException;

    OrderHistory getById(int id) throws SQLException;

    //update
    void update(OrderHistory orderHistory) throws SQLException;

    //delete
    void remove(OrderHistory orderHistory) throws SQLException;
}
