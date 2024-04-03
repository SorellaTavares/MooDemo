package com.example.moodemo.logic;

import com.example.moodemo.data.DbAccess;
import com.example.moodemo.ui.SimpleWindow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import static com.example.moodemo.Main.gw;

public class BullsAndCows {

    public static void playGame(DbAccess dbAccess, SimpleWindow gw) throws SQLException {
        boolean answer = true;
        while (answer) {
            String finalAnswer = generateFinalAnswer();
            gw.clear();
            gw.addString("New game:\n");
            //comment out or remove next line to play real games!
            gw.addString("For practice, number is: " + finalAnswer + "\n");
            String guess = IO.guess();
            gw.addString(guess + "\n");
            int nGuess = 1;
            String bullOrCow = checkGuess(finalAnswer, guess);
            gw.addString(bullOrCow + "\n");
            while (!bullOrCow.equals("BBBB,")) {
                nGuess++;
                guess = gw.getString();
                gw.addString(guess + ": ");
                bullOrCow = checkGuess(finalAnswer, guess);
                gw.addString(bullOrCow + "\n");
            }
            showTopTen(dbAccess);
            answer = gw.continuePlaying("Correct, it took " + nGuess
                    + " guesses\nContinue?");
            if (!answer) {
                dbAccess.close();
                gw.exit();
            }

        }


    }

    static void showTopTen(DbAccess dbAccess) throws SQLException {
            ArrayList<PlayerAverage> topList = new ArrayList<>();
            Connection connection = dbAccess.getConnection();
            Statement stmt2 = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from players");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ResultSet rs2 = stmt2.executeQuery("select * from results where playerid = " + id);
                int nGames = 0;
                int totalGuesses = 0;
                while (rs2.next()) {
                    nGames++;
                    totalGuesses += rs2.getInt("result");
                }
                if (nGames > 0) {
                    topList.add(new PlayerAverage(name, (double) totalGuesses / nGames));
                }

            }
            gw.addString("Top Ten List\n    Player     Average\n");
            int pos = 1;
            topList.sort((p1, p2) -> (Double.compare(p1.average, p2.average)));
            for (PlayerAverage p : topList) {
                gw.addString(String.format("%3d %-10s%5.2f%n", pos, p.name, p.average));
                if (pos++ == 10) break;
            }
            rs.close();
            stmt.close();
            connection.close();
        }

    public static String checkGuess(String finalAnswer, String guess) {
        guess += "    ";
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (finalAnswer.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++) {
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++) {
            result = result + "C";
        }
        return result;
    }

    public static String generateFinalAnswer() {
        String finalAnswer = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (finalAnswer.contains(randomDigit)) {
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            finalAnswer = finalAnswer + randomDigit;
        }
        return finalAnswer;
    }

}

class PlayerAverage {
    String name;
    double average;

    public PlayerAverage(String name, double average) {
        this.name = name;
        this.average = average;
    }
}
