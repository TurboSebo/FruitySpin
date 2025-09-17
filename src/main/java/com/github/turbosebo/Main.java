package com.github.turbosebo;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static com.github.turbosebo.ClearScreen.clearConsole;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearConsole();
        System.out.print("Slot machine java ");
        System.out.println("v0.1.2 TurboSebo");
        int choice;
        do {
            displayMenu();
            while(!scanner.hasNextInt()){
                System.out.println("Invalid choice");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.println("Enter difficulty level: ");
                try {
                    int difficultyLevel = scanner.nextInt();
                    SlotMachine game = new SlotMachine(scanner);
                    game.play(difficultyLevel);
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid value");
                }

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

}
