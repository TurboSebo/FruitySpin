package com.github.turbosebo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class WinningCalculatorTest {

    @Test
    void testAllSameSymbols() {
        Symbol[] slots = {Symbol.SEVEN, Symbol.SEVEN, Symbol.SEVEN};
        int win = WinningsCalculator.getCreditsWon(10, 3, slots);
        assertEquals(500, win, "3x SEVEN should give 500 credits");
    }

    @Test
    void testTwoSameSymbols() {
        Symbol[] slots = {Symbol.SEVEN, Symbol.SEVEN, Symbol.CHERRIES};
        int win = WinningsCalculator.getCreditsWon(10, 3, slots);
        int expected = (int)(Symbol.SEVEN.getValue() * 10 * (2.0/3.0));
        assertEquals(expected, win, "2x SEVEN should give partial win");
    }
    @Test
    void testNoWin(){
        Symbol[] slots = {Symbol.SEVEN, Symbol.CHERRIES, Symbol.ORANGE};
        int win = WinningsCalculator.getCreditsWon(10, 3, slots);
        assertEquals(0, win, "All different symbols should give 0");
    }

    @Test
    void testFourSlotsWithThreeSame(){
        Symbol[] slots = {Symbol.SEVEN, Symbol.SEVEN, Symbol.ORANGE, Symbol.SEVEN};
        int win = WinningsCalculator.getCreditsWon(10, 4, slots);
        int expected = (int)(Symbol.SEVEN.getValue() * 10 * (3.0/4.0));
        assertEquals(expected, win, "All different symbols should give 75% of win");
    }

}
