package com.github.turbosebo;

import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
    private static final int MAX_BET = 5; // maximum bet per spin
    private static final int MIN_BET = 1; // minimum bet per spin
    private static final int INITIAL_CREDITS=100; // Initial credits for the player

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

        ClearScreen.clearConsole();
        System.out.println("Game started");
        int creditsWon;
        displayRules();
        while (credits > 0) {
            int bet = getBet();
            System.out.println("Bet: " + bet);
            credits -= bet;

            System.out.println("Press enter to spin");
            scanner.nextLine();
            ClearScreen.clearConsole();
            //spin
            creditsWon = spin(bet);
            credits = credits + creditsWon;
        }
        System.out.println("Game over");
        System.out.println("Press enter to go back to menu");
        scanner.nextLine();
        ClearScreen.clearConsole();
    }

    public static void displayRules() {
        Symbol[] symbols = Symbol.values();

        for (int i = symbols.length - 1; i >= 0; i--) {
            System.out.println(symbols[i].getRepresentation() + " - " + symbols[i] + " - "+ symbols[i].getValue()+"$");
        }

        /*
        System.out.println("7 - seven - 100$");
        System.out.println("Ó - orange - 50$");
        System.out.println("db -cherries -25$ ");
        System.out.println("D - watermelon - 5$");
        System.out.println("V - Strawberry - 1$");
        */
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
        }while(bet <= MIN_BET || bet > MAX_BET || bet > credits);
        // System.out.println("you choose" + creditsForNextSpin);
        return bet;
    }

    int spin(int credits) {
        Symbol[] slots = new Symbol[3];
        //Random rand = new Random();

        System.out.println("--------------------");
        for (int i = 0; i < 3; i++) {
            if(Difficulty.EASY.equals(difficulty)) slots[i] = Symbol.fromInt(random.nextInt(5) + 1);
            else{
                slots[i] = drawWeightedSymbol();
            }
            System.out.print("|| "+slots[i].getRepresentation()  +" ");
        }

        System.out.println("||\n--------------------");
        int creditsWon = 0;


        if (slots[0] == slots[1] && slots[1] == slots[2]){
            creditsWon = slots[0].getValue() * credits;

        }
        else if ((slots[0]==slots[1]||slots[1]==slots[2]) && !(Difficulty.HARD.equals(difficulty))) {
            Symbol winningSymbol = null;
            if (slots[0]==slots[1]) winningSymbol = slots[0];
            else if (slots[1]==slots[2]) winningSymbol = slots[1];
            if (winningSymbol != null) creditsWon = (winningSymbol.getValue() * credits)/2;
        }


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
