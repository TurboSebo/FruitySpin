package com.github.turbosebo;

public class ClearScreen {
    public static void clearConsole() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Error while trying to clear the console.");
        }
    }

}