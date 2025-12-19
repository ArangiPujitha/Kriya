package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = System.getenv().getOrDefault("DATABASE_URL", "jdbc:mysql://localhost:3306/todo_db");
    private static final String USER = System.getenv().getOrDefault("DATABASE_USER", "root");
    private static final String PASS = System.getenv().getOrDefault("DATABASE_PASSWORD", "password");

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
