package com.example.moodemo.logic;

import com.example.moodemo.Main;
import com.example.moodemo.data.DbAccess;
import com.example.moodemo.ui.SimpleWindow;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IO {

    // Kan detta testas?
    public static void login(@org.jetbrains.annotations.NotNull DbAccess dbAccess, @NotNull SimpleWindow gw) throws SQLException, InterruptedException {
        gw.addString("Enter your user name:\n");
        String name = gw.getString();
        int id = 0;
        ResultSet rs = dbAccess.executeQuery("select id,name from players where name = '" + name + "'");
        if (rs.next()) {
            id = rs.getInt("id");
        } else {
            gw.addString("User not in database, please register with admin");
            Thread.sleep(5000);
            gw.exit();
        }
    }

    public static String guess() {
        String guess = Main.gw.getString();
        return guess;
    }


}
