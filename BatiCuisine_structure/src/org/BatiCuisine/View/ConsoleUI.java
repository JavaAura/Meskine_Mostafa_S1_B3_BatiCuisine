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
                    clientMenu();
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
        System.out.print("=> ");
    }

    public void clientMenu(){
        System.out.println("Would you like to search for an existing customer or add a new one?");
        System.out.println("1.Search an existing customer");
        System.out.println("2.Add a new customer");
        System.out.print("=> ");
        int choice = scan.nextInt();
        switch (choice){
            case 1->{
                System.out.println("Search an existing customer");
            }
            case 2->{
                System.out.println("Add a new customer");
            }
        }
    }
}
