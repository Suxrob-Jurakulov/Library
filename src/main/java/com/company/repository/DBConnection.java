package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaDb",
                    "postgres", "root123");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
