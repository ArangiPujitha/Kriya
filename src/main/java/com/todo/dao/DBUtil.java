package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:postgresql://ep-empty-firefly-ah4ulx9t-pooler.c-3.us-east-1.aws.neon.tech/neondb?sslmode=require&channelBinding=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_z1iEUXoA5PRe";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }
    }
}