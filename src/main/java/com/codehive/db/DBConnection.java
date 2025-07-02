package com.codehive.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private static Connection connection;

    private DBConnection() {
        String url = "jdbc:mysql://localhost:3306/codehive";
        String username = "myuser"; // as defined in docker compose
        String password = "mypassword"; // as defined in docker compose

//        First load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully");
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot load jdbc driver: " + ex.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to database. " + e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        if (instance == null || connection == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
