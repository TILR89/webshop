package dao;

import entity.Delivery;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryDAO {
    //create
    void add(Delivery delivery) throws SQLException;

    //read
    List<Delivery> getAll() throws SQLException;

    Delivery getById(int id) throws SQLException;

    //update
    void update(Delivery delivery) throws SQLException;

    //delete
    void remove(Delivery delivery) throws SQLException;
}
