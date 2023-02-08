package dao;

import entity.ShoppingCart;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartDAO {
    //create
    void add(ShoppingCart shoppingCart) throws SQLException;

    //read
    List<ShoppingCart> getAll() throws SQLException;

    ShoppingCart getById(int userId, int orderId);

    //update
    void update(ShoppingCart shoppingCart) throws SQLException;

    //delete
    void remove(ShoppingCart shoppingCart) throws SQLException;
}
