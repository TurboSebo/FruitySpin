package com.github.turbosebo;

/**
 * ENG
 * Enum representing slot machine symbols.
 * An enum is a fixed set of named constants - perfect for elements that don't change during program execution.
 *
 * In this case, the enum stores:
 * - textual representation of each symbol (e.g., "V", "D")
 * - point value of each symbol (win multiplier)
 *
 * Using enums instead of magic numbers (1, 2, 3...) makes the code more readable and type-safe,
 * as we use meaningful names (STRAWBERRY, WATERMELON...) throughout the program.
 *
 * PL
 * Enum reprezentujący symbole w automacie.
 * Enum to stały zbiór nazwanych wartości - idealny do elementów, które nie zmieniają się w trakcie działania programu.
 *
 * W tym przypadku enum przechowuje:
 * - reprezentację tekstową symbolu (np. "V", "D")
 * - wartość punktową symbolu (mnożnik wygranej)
 *
 * Dzięki enumowi zamiast magicznych liczb (1, 2, 3...) używamy czytelnych nazw (STRAWBERRY, WATERMELON...),
 * co zwiększa czytelność i bezpieczeństwo kodu.
 */

public enum Symbol {
    STRAWBERRY("V", 1),
    WATERMELON("D", 5),
    CHERRIES("db", 10),
    ORANGE("Ó", 25),
    SEVEN("7", 50);

    private final String representation;
    private final int value;

    Symbol(String representation, int value){
        this.representation = representation;
        this.value = value;
    }
    public String getRepresentation() {
        return representation;
    }
    public int getValue() {
        return value;
    }
    public static Symbol fromInt(int number){
        return switch (number){
            case 1 -> STRAWBERRY;
            case 2 -> WATERMELON;
            case 3 -> CHERRIES;
            case 4 -> ORANGE;
            case 5 -> SEVEN;
            default -> throw new IllegalArgumentException("Illegal symbol number " + number);
        };
    }
}
