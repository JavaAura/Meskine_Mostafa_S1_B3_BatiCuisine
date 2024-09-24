package org.BatiCuisine.Utility;

import java.util.Scanner;

public class Validation {
    private final Scanner scanner;

    public Validation(Scanner scanner) {
        this.scanner = scanner;
    }

    public String validateStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                break;
            }
            System.out.println("Invalid input. Please try again.");
        }
        return input;
    }

    public double validateDoubleInput(String prompt) {
        double input = 0;
        while (true) {
            System.out.print(prompt);
            try {
                input = Double.parseDouble(scanner.nextLine());
                if (input >= 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return input;
    }
}
