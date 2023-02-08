package dao;

import entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {
    //create
    void add(Users users) throws SQLException;

    //read
    List<Users> getAll() throws SQLException;

    Users getById(int id);

    //update
    void update(Users users) throws SQLException;

    //delete
    void remove(Users users) throws SQLException;
}
