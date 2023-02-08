package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/webshop";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Bkmz0944";

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection is ok");
        } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
            System.out.println("Connection ERROR");}
        return connection;
    }
}
