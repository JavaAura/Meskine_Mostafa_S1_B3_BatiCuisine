package org.BatiCuisine.View;

import org.BatiCuisine.Dao.Impl.ProjectDaoImpl;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Model.Project;
import org.BatiCuisine.Repository.Impl.ClientRepositoryImpl;
import org.BatiCuisine.Dao.Impl.ClientDaoImpl;
import org.BatiCuisine.Repository.Impl.ProjectRepositoryImpl;
import org.BatiCuisine.Service.ClientService;
import org.BatiCuisine.Service.ProjectService;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    private static final Scanner scan = new Scanner(System.in);
    private final ClientService clientService = new ClientService(new ClientRepositoryImpl(new ClientDaoImpl()));
    private final ProjectService projectService = new ProjectService(new ProjectRepositoryImpl(new ProjectDaoImpl()));

    public ConsoleUI() {
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
        System.out.println(" ============================== Main Menu ============================= ");
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
            default -> mainMenu();
        }
    }

    public void materialMenu(Project project) {
        addNewMaterial(project);
        System.out.print("Do you want to add another material? (y/n) : ");
        String confirmation = scan.nextLine();
        switch (confirmation) {
            case "y" -> addNewMaterial(project);
            case "n" -> System.out.println("add labor");
        }
    }

    public void addNewClient() {
        System.out.print("Enter client name: ");
        String name = scan.nextLine();
        System.out.print("Enter client address: ");
        String address = scan.nextLine();
        System.out.print("Enter client phone: ");
        String phone = scan.nextLine();
        System.out.print("Is the client a professional? (true/false): ");
        boolean isProfessional = scan.nextBoolean();

        Client client = new Client(UUID.randomUUID(), name, address, phone, isProfessional);
        clientService.addClient(client);
        addNewProject(client);
    }

    public void searchClient() {
        System.out.println("--- searching an existing customer ---");
        System.out.print("Enter customer name: ");
        String name = scan.nextLine();
        Optional<Client> clientOptional = clientService.getClientByName(name);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client found!");
            System.out.println(client);
            System.out.print("Would you like to continue with this client? (y/n):");
            String choice = scan.nextLine();
            switch (choice) {
                case "y" -> addNewProject(client);
                case "n" -> clientMenu();
                default -> {
                    System.out.println("invalid input!");
                    mainMenu();
                }
            }
        } else {
            System.out.println("no client found by the name *" + name + "* !");
            clientMenu();
        }
    }

    public void addNewProject(Client client) {
        System.out.println("--- Creating a New Project ---");
        System.out.print("Enter the project name: ");
        String name = scan.nextLine();
        System.out.print("Enter the kitchen area (in m²): ");
        double area = scan.nextDouble();

        Project project = new Project();
        project.setProjectName(name);
        project.setSurface(area);
        project.setProjectStatus("In progress");
        project.setClientID(client.getClientID());
        materialMenu(project);
    }

    public void addNewMaterial(Project project) {

    }

    public void showAllProjects() {

    }

    public void calculateProjectCost() {

    }
}
