package dao;

import entity.Products;

import java.sql.SQLException;
import java.util.List;

public interface ProductsDAO {
    //create
    void add(Products products) throws SQLException;

    //read
    List<Products> getAll() throws SQLException;

    Products getById(int id) throws SQLException;

    //update
    void update(Products products) throws SQLException;

    //delete
    void remove(Products products) throws SQLException;
}
