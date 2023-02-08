package dao;

import entity.ProductCategories;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoriesDAO {

    //create
    void add(ProductCategories productCategories) throws SQLException;

    //read
    List<ProductCategories> getAll() throws SQLException;

    ProductCategories getById(int ProductId , int CategoryId);

    //update
    void update(ProductCategories productCategories) throws SQLException;

    //delete
    void remove(ProductCategories productCategories) throws SQLException;
}
