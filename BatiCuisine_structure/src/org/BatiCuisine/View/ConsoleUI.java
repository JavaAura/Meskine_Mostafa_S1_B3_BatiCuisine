package org.BatiCuisine.View;

import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleUI {
    public static Scanner scan = new Scanner(System.in);
//    private static final Logger logger = Logger.getLogger(ConsoleUI.class.getName());

    public ConsoleUI() {
        while (true) {
            mainMenu();
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> {

                }
                case 2 -> {
                    System.out.println("Afficher les projets existants");
                }
                case 3 -> {
                    System.out.println("Calculer le coût d'un projet");
                }
                default -> {
                    return;
                }
            }
        }
    }

    public void mainMenu() {
        System.out.println("=== Welcome to the kitchen renovation project management application ===");
        System.out.println("=== Main Menu ===");
        System.out.println("1.Create a new project");
        System.out.println("2.Show All projects");
        System.out.println("3.Calculate the cost of a project");
        System.out.println("4.Exit");
        System.out.println("=> ");
    }
}
