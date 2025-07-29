package com.alvim.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection current;
    private static Connection connection = null;

    private DBConnection(){

    }

    public static DBConnection getCurrent(){
        if(current == null){
            current = new DBConnection();
            try{
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommercy","postgres","123");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return current;
    }

    public Connection getConnection() {
        return connection;
    }
}
