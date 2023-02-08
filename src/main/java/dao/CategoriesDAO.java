package dao;

import entity.Categories;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesDAO {
    //create
    void add(Categories categories) throws SQLException;

    //read
    List<Categories> getAll() throws SQLException;

    Categories getById(int id) throws SQLException;

    //update
    void update(Categories categories) throws SQLException;

    //delete
    void remove(Categories categories) throws SQLException;
}
