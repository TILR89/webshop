package dao;

import entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {
    //create
    void add(Payment payment) throws SQLException;

    //read
    List<Payment> getAll() throws SQLException;

    Payment getById(int id) throws SQLException;

    //update
    void update(Payment payment) throws SQLException;

    //delete
    void remove(Payment payment) throws SQLException;
}
