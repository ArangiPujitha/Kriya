package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = System.getenv().getOrDefault("DATABASE_URL", "jdbc:postgresql://localhost:5432/todo_db");
    private static final String USER = System.getenv().getOrDefault("DATABASE_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DATABASE_PASSWORD", "password");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }
    }
}