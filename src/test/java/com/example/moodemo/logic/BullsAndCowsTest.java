package com.example.moodemo.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BullsAndCowsTest {

    @Test
    public void testCheckGuess() {
        String finalAnswer = "1234";
        assertEquals("BBBB,", BullsAndCows.checkGuess(finalAnswer, "1234"));
        assertEquals("BB,", BullsAndCows.checkGuess(finalAnswer, "1277"));
        assertEquals("B,", BullsAndCows.checkGuess(finalAnswer, "1567"));
    }

    @Test
    public void testGenerateFinalAnswer() {
        String finalAnswer = BullsAndCows.generateFinalAnswer();
        assertNotNull(finalAnswer);
        assertEquals(4, finalAnswer.length());
        for (char c : finalAnswer.toCharArray()) {
            int digit = Character.getNumericValue(c);
            assertTrue(digit >= 0 && digit <= 9, "0-9");
        }
    }

}
