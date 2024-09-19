package org.BatiCuisine.View;

import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Repository.Impl.ClientRepositoryImpl;
import org.BatiCuisine.Dao.Impl.ClientDaoImpl;
import org.BatiCuisine.Service.ClientService;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    private static final Scanner scan = new Scanner(System.in);
    private final ClientService clientService;

    public ConsoleUI() {
        clientService = new ClientService(new ClientRepositoryImpl(new ClientDaoImpl()));
        while (true) {
            mainMenu();
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1 -> clientMenu();
                case 2 -> showAllProjects();
                case 3 -> calculateProjectCost();
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

    public void clientMenu() {
        System.out.println("Would you like to search for an existing customer or add a new one?");
        System.out.println("1.Search an existing customer");
        System.out.println("2.Add a new customer");
        System.out.print("=> ");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1 -> searchClient();
            case 2 -> addNewClient();
        }
    }

    public void addNewClient() {
        System.out.println("Enter client name: ");
        String name = scan.next();
        System.out.println("Enter client address: ");
        String address = scan.next();
        System.out.println("Enter client phone: ");
        String phone = scan.next();
        System.out.println("Is the client a professional? (true/false): ");
        boolean isProfessional = scan.nextBoolean();

        Client client = new Client(UUID.randomUUID(), name, address, phone, isProfessional);
        clientService.addClient(client);
    }

    public void searchClient() {
//        System.out.println("Enter client ID (UUID format): ");
//        String idInput = scan.next();
//        try {
//            UUID clientId = UUID.fromString(idInput);
//            Client client = clientService.getClientById(clientId);
//            if (client != null) {
//                System.out.println("Client found: " + client);
//            } else {
//                System.out.println("No client found with ID: " + clientId);
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid UUID format");
//        }
    }

    public void showAllProjects() {

    }

    public void calculateProjectCost() {

    }
}
