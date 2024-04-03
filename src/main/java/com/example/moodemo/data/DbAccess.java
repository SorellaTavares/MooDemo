package com.example.moodemo.data;

import java.sql.*;

public class DbAccess {
    private static Connection connection;
    public DbAccess(String url, String username,String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/Moo", "ulf","ulfpw");
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
