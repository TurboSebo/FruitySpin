package com.github.turbosebo;

import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
    private static final int MAX_BET = 5; // maximum bet per spin
    private static final int MIN_BET = 1; // minimum bet per spin
    private static final int INITIAL_CREDITS=100; // Initial credits for the player
    private int slotsNumber = 4;
    private int credits;
    private Scanner scanner;
    private Random random;
    Difficulty difficulty;

    public SlotMachine(Scanner scanner) {
        this.credits = INITIAL_CREDITS;
        this.scanner = scanner;
        this.random = new Random();
    }

    public void play(int difficultyLevel) {
        difficulty = Difficulty.setDifficulty(difficultyLevel);

        slotsNumber = difficulty.getSlotsNumber();

        ClearScreen.clearConsole();
        System.out.println("Game started");
        System.out.println("Difficulty Selected: " + difficulty.getDisplayName());
        int creditsWon;
        displayRules();

        while (credits > 0) {
            int bet = getBet();
            System.out.println("Bet: " + bet);


            System.out.println("Press enter to spin");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            if (input.equals("quit")||input.equals("q")) {
                break;
            }
            credits -= bet;
            ClearScreen.clearConsole();

            creditsWon = spin(bet);
            credits = credits + creditsWon;
        }
        System.out.println("--------------------");
        System.out.println("Game over");
        System.out.println("You have left with " + credits + "$");
        System.out.println("Press enter to go back to menu");
        try {
            scanner.nextLine();
        } catch (Exception e) {
            // Ignoruj błędy wejścia
        }
        ClearScreen.clearConsole();
    }

    public static void displayRules() {
        Symbol[] symbols = Symbol.values();

        for (int i = symbols.length - 1; i >= 0; i--) {
            System.out.println(symbols[i].getRepresentation() + " - " + symbols[i] + " - "+ symbols[i].getValue()+"$");
        }

    }

    private int getBet() {
        int bet;
        do  {
            System.out.println("you have now "+credits+" credits");
            System.out.print("Please enter how much for a spin: ");
            while(!scanner.hasNextInt()){
                System.out.println("That is not a valid number");
                scanner.next();
            }
            bet = scanner.nextInt();
            scanner.nextLine(); // wyczyszczenie bufora po nextInt()
            if (bet > credits) {
                System.out.println("You do not have enough credits");
            }else if (bet < MIN_BET || bet > MAX_BET) {
                System.out.println("You must select beetween "+ MIN_BET + " and "+ MAX_BET + " credits");
            }
        }while(bet < MIN_BET || bet > MAX_BET || bet > credits || credits < MIN_BET);
        // System.out.println("you choose" + creditsForNextSpin);
        return bet;
    }

    int spin(int credits) {
        Symbol[] slots = new Symbol[slotsNumber];

        System.out.println("--------------------");


        for (int i = 0; i < slotsNumber; i++) {
            slots[i] = drawWeightedSymbol();
            System.out.print("|| ");
            try {
                Thread.sleep(300);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print(slots[i].getRepresentation() + " ");

        }
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("||\n--------------------");
        int creditsWon = WinningsCalculator.getCreditsWon(credits, slotsNumber, slots);


        System.out.println("You won: "+ creditsWon);
        return creditsWon;
    }

    private Symbol drawWeightedSymbol() {
        int draw = random.nextInt(100); // Losuje liczbę od 0 do 99

        if (draw < 30) return Symbol.STRAWBERRY; // 30% szansy (0-29)
        else if (draw < 55) return Symbol.WATERMELON; // 25% szansy (30-54)
        else if (draw < 75) return Symbol.CHERRIES; // 20% szansy (55-74)
        else if (draw < 95) return Symbol.ORANGE; // 20% szansy (75-94)
        else  return Symbol.SEVEN; // 5% szansy (95-99)
    }
}
