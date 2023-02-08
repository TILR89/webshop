package dao;

import entity.Suppliers;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersDAO {
    //create
    void add(Suppliers suppliers) throws SQLException;

    //read
    List<Suppliers> getAll() throws SQLException;

    Suppliers getById(int id);

    //update
    void update(Suppliers suppliers) throws SQLException;

    //delete
    void remove(Suppliers suppliers) throws SQLException;
}
