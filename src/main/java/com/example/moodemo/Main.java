/**
 * MooOriginal
 * - övning i refaktorisering
 * Sorella Tavares
 * JU23 ITHS
 */

/*
Tyvärr har jag en bugg där spelet vid upprepade ronder
inte får kontakt med databasen och då kraschar.
 */


package com.example.moodemo;

import com.example.moodemo.data.DbAccess;
import com.example.moodemo.logic.BullsAndCows;
import com.example.moodemo.logic.IO;
import com.example.moodemo.ui.SimpleWindow;

import java.sql.SQLException;


public class Main {
    public static SimpleWindow gw;

    public static void main(String[] args) {

        DbAccess dbAccess;
        try {
            gw = new SimpleWindow("Moo");
            dbAccess = new DbAccess("jdbc:mysql://localhost/Moo", "ulf", "ulfpw");
            IO.login(dbAccess, gw);
            BullsAndCows.playGame(dbAccess, gw);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}