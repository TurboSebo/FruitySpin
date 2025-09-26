package com.github.turbosebo;

import java.util.HashMap;
import java.util.Map;

public class WinningsCalculator {
    public static int getCreditsWon(int credits, int slotsNumber, Symbol[] slots) {
        Map<Symbol, Integer> symbolCounts = new HashMap<>();

        for (Symbol symbol : slots) {
            symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
        }

        //znajdź symbol z największą ilością wystąpień
        Symbol bestSymbol = null;
        int maxCount = 0;
        for (Map.Entry<Symbol, Integer> entry: symbolCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                bestSymbol = entry.getKey();
            }
        }

        //oblicz wygraną na podstawie wystąpienia identycznych symboli
        if (maxCount >=2) {
            double winMultiplier = (double) maxCount / slotsNumber;
            return (int) (bestSymbol.getValue() * credits *winMultiplier);
        }

        return 0;
    }
}

