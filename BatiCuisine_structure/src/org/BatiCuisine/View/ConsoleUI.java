package org.BatiCuisine.View;

import org.BatiCuisine.Dao.Impl.LaborDaoImpl;
import org.BatiCuisine.Dao.Impl.MaterialDaoImpl;
import org.BatiCuisine.Dao.Impl.ProjectDaoImpl;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Model.Labor;
import org.BatiCuisine.Model.Material;
import org.BatiCuisine.Model.Project;
import org.BatiCuisine.Repository.Impl.ClientRepositoryImpl;
import org.BatiCuisine.Dao.Impl.ClientDaoImpl;
import org.BatiCuisine.Repository.Impl.ComponentRepositoryImpl;
import org.BatiCuisine.Repository.Impl.ProjectRepositoryImpl;
import org.BatiCuisine.Service.ClientService;
import org.BatiCuisine.Service.ComponentService;
import org.BatiCuisine.Service.ProjectService;

import java.util.*;

public class ConsoleUI {
    private static final Scanner scan = new Scanner(System.in);
    private final ClientService clientService = new ClientService(new ClientRepositoryImpl(new ClientDaoImpl()));
    private final ProjectService projectService = new ProjectService(new ProjectRepositoryImpl(new ProjectDaoImpl()));
    private final ComponentService componentService = new ComponentService(new ComponentRepositoryImpl(new LaborDaoImpl(), new MaterialDaoImpl()));
    private Map<UUID, Material> materialsMap = new HashMap<>();
    private Map<UUID, Labor> laborsMap = new HashMap<>();
    private Project project = null;

    public ConsoleUI() {
        while (true) {
            mainMenu();
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1 -> clientMenu();
                case 2 -> showAllProjects();
//                case 3 -> calculateProjectCost();
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

    public void materialMenu() {
        System.out.println("--- Adding materials ---");
        while (true) {
            addNewMaterial();
            System.out.print("Do you want to add another material? (y/n) : ");
            String confirmation = scan.nextLine();
            if (!confirmation.equals("y")) {
                break;
            }
        }
        laborMenu();
    }

    public void laborMenu() {
        System.out.println("--- Adding Labor (Manpower) ---");
        while (true) {
            addNewLabor();
            System.out.print("Do you want to add another type of labor? (y/n): ");
            String confirmation = scan.nextLine();
            if (!confirmation.equals("y")) {
                break;
            }
        }
        taxesRate();
    }

    public void taxesRate() {
        System.out.println("--- Calculation of total cost ---");
        System.out.print("Would you like to apply VAT to the project? (y/n): ");
        String VatConfirmation = scan.nextLine();
        if (VatConfirmation.equals("y")) {
            addVAT();
        }
        System.out.print("Would you like to apply a profit margin to the project? (y/n): ");
        String marginConfirmation = scan.nextLine();
        if (marginConfirmation.equals("y")) {
            addMargin();
        }

        double totalCost = calculateProjectCost();

        insertAll(totalCost);
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
        scan.nextLine();

        project = new Project();
        project.setProjectName(name);
        project.setSurface(area);
        project.setProjectStatus("In progress");
        project.setClientID(client.getClientID());

        materialMenu();
    }

    public void addNewMaterial() {
        System.out.print("Enter the name of the material: ");
        String name = scan.nextLine();

        System.out.print("Enter the quantity of this material (in m²): ");
        double quantity = Double.parseDouble(scan.nextLine());

        System.out.print("Enter the unit cost of this material (€ / m²): ");
        double unitCost = Double.parseDouble(scan.nextLine());

        System.out.print("Enter the transport cost of this material (€): ");
        double transportCost = Double.parseDouble(scan.nextLine());

        System.out.print("Enter the quality coefficient of the material (1.0 = standard, > 1.0 = high quality): ");
        double qualityCoefficient = Double.parseDouble(scan.nextLine());

        UUID componentID = UUID.randomUUID();
        Material material = new Material();

        material.setComponentID(componentID);
        material.setName(name);
        material.setQuantity(quantity);
        material.setUnitCost(unitCost);
        material.setTransportCost(transportCost);
        material.setQualityCoefficient(qualityCoefficient);
        material.setProjectID(project.getProjectID());

        materialsMap.put(componentID, material);

        System.out.println("Material added successfully!");
    }


    public void addNewLabor() {
        System.out.print("Enter the type of labor (e.g., Basic Worker, Specialist): ");
        String name = scan.nextLine();

        System.out.print("Enter the hourly rate for this labor (€ / h): ");
        double hourlyRate = Double.parseDouble(scan.nextLine());

        System.out.print("Enter the number of working hours: ");
        double workingHours = Double.parseDouble(scan.nextLine());

        System.out.print("Enter the productivity factor (1.0 = standard, > 1.0 = high productivity): ");
        double workerProductivity = Double.parseDouble(scan.nextLine());

        UUID componentID = UUID.randomUUID();
        Labor labor = new Labor();
        labor.setComponentID(componentID);
        labor.setName(name);
        labor.setHourlyRate(hourlyRate);
        labor.setWorkingHours(workingHours);
        labor.setWorkerProductivity(workerProductivity);
        labor.setProjectID(project.getProjectID());

        laborsMap.put(componentID, labor);

        System.out.println("Labor added successfully!");
    }

    public void addVAT() {
        System.out.print("Enter the VAT percentage (%): ");
        double VATRate = Double.parseDouble(scan.nextLine());
        if (!materialsMap.isEmpty()) {
            for (Map.Entry<UUID, Material> entry : materialsMap.entrySet()) {
                Material material = entry.getValue();
                material.setVATRate(VATRate);
            }
        }
        if (!laborsMap.isEmpty()) {
            for (Map.Entry<UUID, Labor> entry : laborsMap.entrySet()) {
                Labor labor = entry.getValue();
                labor.setVATRate(VATRate);
            }
        }
    }

    public void addMargin() {
        System.out.print("Enter the profit margin percentage (%): ");
        double profitMargin = Double.parseDouble(scan.nextLine());
        project.setProfitMargin(profitMargin);
    }

    public void insertAll(double totalCost) {
        project.setTotalCost(totalCost);
        projectService.addProject(project);

        if (!materialsMap.isEmpty()) {
            for (Map.Entry<UUID, Material> entry : materialsMap.entrySet()) {
                Material material = entry.getValue();
                componentService.addComponent(material);
            }
        }
        if (!laborsMap.isEmpty()) {
            for (Map.Entry<UUID, Labor> entry : laborsMap.entrySet()) {
                Labor labor = entry.getValue();
                componentService.addComponent(labor);
            }
        }
    }


    public void showAllProjects() {

    }

    public double calculateProjectCost() {
        double totalMaterialCost = 0;
        double totalLaborCost = 0;
        if (!materialsMap.isEmpty()) {
            for (Map.Entry<UUID, Material> entry : materialsMap.entrySet()) {
                Material material = entry.getValue();
                double materialCost = material.calculateComponentCost();
                totalMaterialCost += materialCost;
            }
        }
        if (!laborsMap.isEmpty()) {
            for (Map.Entry<UUID, Labor> entry : laborsMap.entrySet()) {
                Labor labor = entry.getValue();
                double laborCost = labor.calculateComponentCost();
                totalLaborCost += laborCost;
            }
        }
        return 20000;
    }

    // this method is here only to test materials hashmap
    public void showMaterials() {
        System.out.println("--- Materials List ---");
        if (materialsMap.isEmpty()) {
            System.out.println("No materials added yet.");
        } else {
            for (Map.Entry<UUID, Material> entry : materialsMap.entrySet()) {
                UUID componentID = entry.getKey();
                Material material = entry.getValue();
                System.out.println("ID: " + componentID +
                        ", Name: " + material.getName() +
                        ", Quantity: " + material.getQuantity() +
                        ", Unit Cost: " + material.getUnitCost() +
                        ", Transport Cost: " + material.getTransportCost() +
                        ", Quality Coefficient: " + material.getQualityCoefficient() +
                        ", VAT Rate: " + material.getVATRate());
            }
        }
    }

    // this method is here only to test labors hashmap
    public void showLabors() {
        System.out.println("--- Labor List ---");
        if (laborsMap.isEmpty()) {
            System.out.println("No labor added yet.");
        } else {
            for (Map.Entry<UUID, Labor> entry : laborsMap.entrySet()) {
                UUID componentID = entry.getKey();
                Labor labor = entry.getValue();
                System.out.println("ID: " + componentID +
                        ", Name: " + labor.getName() +
                        ", Hourly Rate: " + labor.getHourlyRate() +
                        ", Working Hours: " + labor.getWorkingHours() +
                        ", Worker Productivity: " + labor.getWorkerProductivity() +
                        ", VAT Rate: " + labor.getVATRate());
            }
        }
    }

}
