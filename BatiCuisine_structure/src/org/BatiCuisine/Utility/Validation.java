package org.BatiCuisine.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    private final Scanner scanner;

    public Validation(Scanner scanner) {
        this.scanner = scanner;
    }

    public String validateName(String prompt) {
        String name;
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");

        while (true) {
            System.out.print(prompt);
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Invalid input! The name cannot be empty.");
            } else if (pattern.matcher(name).matches()) {
                return name;
            } else {
                System.out.println("Invalid input! The name must not contain numbers and can only include letters and spaces.");
            }
        }
    }

    public String validatePhone(String prompt) {
        String phone;
        Pattern pattern = Pattern.compile("^[\\d\\s\\-\\(\\)]+$");

        while (true) {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();

            if (phone.isEmpty()) {
                System.out.println("Invalid input! The phone number cannot be empty.");
            } else if (pattern.matcher(phone).matches()) {
                return phone;
            } else {
                System.out.println("Invalid input! The phone number can only contain digits, spaces, dashes, and parentheses.");
            }
        }
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

    public boolean confirmYesNo(String message) {
        String confirmation;
        while (true) {
            System.out.print(message);
            confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("y")) {
                return true;
            } else if (confirmation.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'y' or 'n'.");
            }
        }
    }

}
