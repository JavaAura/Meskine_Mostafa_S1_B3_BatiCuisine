package org.BatiCuisine.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public double validatePercentageInput(String prompt) {
        double input = 0;
        while (true) {
            System.out.print(prompt);
            try {
                input = Double.parseDouble(scanner.nextLine());
                if (input >= 0 && input <= 100) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            System.out.println("Invalid input. Please enter a percentage between 0 and 100.");
        }
        return input;
    }

    public Date validateDateInput(String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        while (true) {
            System.out.print(prompt);
            String dateInput = scanner.nextLine();
            try {
                date = dateFormat.parse(dateInput);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }
        return date;
    }

    public int validateIntegerInput(String prompt, int min, int max) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            System.out.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
        }
        return input;
    }


}
