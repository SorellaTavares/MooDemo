package com.example.moodemo.ui;

import java.sql.Connection;

public interface DbAccessInterface {
    void DbAccess(String url, String username, String password);

    void executeQuery(String query);

    void close();

    Connection getConnection();
}
