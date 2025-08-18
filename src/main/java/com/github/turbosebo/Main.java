package com.github.turbosebo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Slot machine java ");
        System.out.println("v0.1 TurboSebo");
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Start the game");
                playGame(scanner);
            }
            else if (choice == 2) {
                break;
            }
            else {
                System.out.println("Invalid choice");
            }

        }while (choice != 2);
        scanner.close();
    }
    public static void displayMenu() {
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
    }
    public static void playGame(Scanner scanner) {
        System.out.println("Game started");
        int credits = 100;
        int creditsForNextSpin = 0;
        int creditsWon = 0;
        displaySlotMachineRules();
        while (credits > 0) {
            creditsForNextSpin = howMuchForSpin(credits);
            credits = credits - creditsForNextSpin;
            System.out.println("Press enter to spin");
            scanner.nextLine();
            //spin
            creditsWon = spin(creditsForNextSpin);
            credits = credits + creditsWon;

        }
        System.out.println("Game over");
    }

    public static void displaySlotMachineRules() {
        System.out.println("7 - seven - 100$");
        System.out.println("Ó - orange - 50$");
        System.out.println("db -cherries -25$ ");
        System.out.println("D - watermelon - 5$");
        System.out.println("V - Strawberry - 1$");

    }

    public static int howMuchForSpin(int playerCredits) {
        Scanner scanner = new Scanner(System.in);
        int creditsForNextSpin;
        do  {
            System.out.println("you have now "+playerCredits+" credits");
            System.out.print("Please enter how much for a spin: ");
            creditsForNextSpin = scanner.nextInt();
            if (creditsForNextSpin > playerCredits) {
                System.out.println("You do not have enough credits");
                creditsForNextSpin = 0;
            }else if (creditsForNextSpin <= 0 || creditsForNextSpin > 10) {
                System.out.println("You must select beetween 1 and 10");
                creditsForNextSpin = 0;
            }


        }while(creditsForNextSpin == 0);
        // System.out.println("you choose" + creditsForNextSpin);
        return creditsForNextSpin;
    }
    static String intToSymbol(int number) {
        String symbol = "";
        switch (number) {
            case 1:
                symbol = "V";
                break;
            case 2:
                symbol = "D";
                break;
            case 3:
                symbol = "db";
                break;
            case 4:
                symbol = "Ó";
                break;
            case 5:
                symbol = "7";
                break;
            default:
                symbol = "?";
                break;
        }
        return symbol;
    }
    static int spin(int credits) {
        int[] slot = new int[3];
        Random rand = new Random();

        System.out.println("--------------------");
        for (int i = 0; i < 3; i++) {
            slot[i] = rand.nextInt(5)+1;
            System.out.print("|| "+intToSymbol(slot[i])  +" ");
        }

        System.out.println("||\n--------------------");
        int creditsWon = 0;
        if (slot[0] == slot[1] && slot[1] == slot[2]){
            switch (slot[0]){
                case 1:
                    creditsWon = 1;
                    break;
                case 2:
                    creditsWon = 5;
                    break;
                case 3:
                    creditsWon = 25;
                    break;
                case 4:
                    creditsWon = 50;
                    break;
                case 5:
                    creditsWon = 100;
                    break;
                default:
                    creditsWon = 0;
            }
            creditsWon = (creditsWon*credits);
        }
        System.out.println("You won: "+ creditsWon);
        return creditsWon;
    }
}
