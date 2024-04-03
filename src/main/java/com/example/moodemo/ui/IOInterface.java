package com.example.moodemo.ui;

import com.example.moodemo.data.DbAccess;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public interface IOInterface {
    void login(@NotNull DbAccess dbAccess, @NotNull SimpleWindow gw) throws SQLException, InterruptedException;
}
