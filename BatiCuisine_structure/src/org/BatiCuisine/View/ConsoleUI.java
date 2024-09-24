package org.BatiCuisine.View;

import org.BatiCuisine.Dao.Impl.*;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Enum.ComponentType;
import org.BatiCuisine.Enum.ProjectStatus;
import org.BatiCuisine.Model.*;
import org.BatiCuisine.Repository.Impl.ClientRepositoryImpl;
import org.BatiCuisine.Repository.Impl.ComponentRepositoryImpl;
import org.BatiCuisine.Repository.Impl.ProjectRepositoryImpl;
import org.BatiCuisine.Repository.Impl.QuoteRepositoryImpl;
import org.BatiCuisine.Service.ClientService;
import org.BatiCuisine.Service.ComponentService;
import org.BatiCuisine.Service.ProjectService;
import org.BatiCuisine.Service.QuoteService;
import org.BatiCuisine.Utility.Validation;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleUI {
    private final Connection connection = DbConnection.getInstance();
    private final Scanner scan = new Scanner(System.in);
    private final Validation validator = new Validation(scan);
    private final ClientService clientService = new ClientService(new ClientRepositoryImpl(new ClientDaoImpl(connection)));
    private final ProjectService projectService = new ProjectService(new ProjectRepositoryImpl(new ProjectDaoImpl(connection)));
    private final ComponentService componentService = new ComponentService(new ComponentRepositoryImpl(new LaborDaoImpl(connection), new MaterialDaoImpl(connection)));
    private final QuoteService quoteService = new QuoteService(new QuoteRepositoryImpl(new QuoteDaoImpl(connection)));
    private Map<UUID, Material> materialsMap = new HashMap<>();
    private Map<UUID, Labor> laborsMap = new HashMap<>();
    private Project project = null;
    private Client client = null;

    public ConsoleUI() {
        while (true) {
            mainMenu();

            int choice = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = scan.nextInt();
                    scan.nextLine();

                    if (choice >= 1 && choice <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scan.nextLine();
                }
            }

            switch (choice) {
                case 1 -> clientMenu();
                case 2 -> showAllProjects();
                case 3 -> {
                    selectProject();
                    getProjectComponets();
                    calculateProjectCost();
                }
                case 4 -> {
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
        System.out.println("1. Search an existing customer");
        System.out.println("2. Add a new customer");
        System.out.print("=> ");

        int choice = -1;
        while (true) {
            try {
                choice = scan.nextInt();
                scan.nextLine();
                if (choice == 1) {
                    searchClient();
                    break;
                } else if (choice == 2) {
                    addNewClient();
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scan.nextLine();
            }
        }
    }


    public void materialMenu() {
        System.out.println("--- Adding Materials ---");
        boolean addingMore = true;

        while (addingMore) {
            addNewMaterial();

            String confirmation = "";
            while (true) {
                System.out.print("Do you want to add another material? (y/n): ");
                confirmation = scan.nextLine().trim().toLowerCase();
                if (confirmation.equals("y")) {
                    break;
                } else if (confirmation.equals("n")) {
                    addingMore = false;
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 'y' or 'n'.");
                }
            }
        }

        laborMenu();
    }


    public void laborMenu() {
        System.out.println("--- Adding Labor (Manpower) ---");
        while (true) {
            addNewLabor();
            if (!validator.confirmYesNo("Do you want to add another type of labor? (y/n): ")) {
                break;
            }
        }
        taxesRate();
    }

    public void taxesRate() {
        System.out.println("--- Calculation of Total Cost ---");

        if (validator.confirmYesNo("Would you like to apply VAT to the project? (y/n): ")) {
            addVAT();
        }

        if (validator.confirmYesNo("Would you like to apply a profit margin to the project? (y/n): ")) {
            addMargin();
        }

        calculateProjectCost();
        insertAll();
    }

    public void addNewClient() {
        String name = validator.validateStringInput("Enter client name: ");
        String address = validator.validateStringInput("Enter client address: ");
        String phone = validator.validateStringInput("Enter client phone: ");

        boolean isProfessional = false;
        while (true) {
            System.out.print("Is the client a professional? (true/false): ");
            String input = scan.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                isProfessional = true;
                break;
            } else if (input.equals("false")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter 'true' or 'false'.");
            }
        }

        client = new Client(UUID.randomUUID(), name, address, phone, isProfessional);
        clientService.addClient(client);
        addNewProject();
    }


    public void searchClient() {
        System.out.println("--- Searching an Existing Customer ---");

        String name = validator.validateStringInput("Enter customer name: ");
        Optional<Client> clientOptional = clientService.getClientByName(name);

        if (clientOptional.isPresent()) {
            client = clientOptional.get();
            System.out.println("Client found!");
            System.out.println(client);

            if (validator.confirmYesNo("Would you like to continue with this client? (y/n): ")) {
                addNewProject();
            } else {
                clientMenu();
            }
        } else {
            System.out.println("No client found by the name *" + name + "* !");
            clientMenu();
        }
    }


    public void addNewProject() {
        System.out.println("--- Creating a New Project ---");

        String name = validator.validateStringInput("Enter the project name: ");
        double area = validator.validateDoubleInput("Enter the kitchen area (in m²): "); // Use your existing double validation method

        project = new Project();
        project.setProjectName(name);
        project.setSurface(area);
        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        project.setClient(client);

        materialMenu();
    }


    public void addNewMaterial() {
        String name = validator.validateStringInput("Enter the name of the material: ");
        double quantity = validator.validateDoubleInput("Enter the quantity of this material (in m²): ");
        double unitCost = validator.validateDoubleInput("Enter the unit cost of this material (€ / m²): ");
        double transportCost = validator.validateDoubleInput("Enter the transport cost of this material (€): ");
        double qualityCoefficient = validator.validateDoubleInput("Enter the quality coefficient of the material (1.0 = standard, > 1.0 = high quality): ");

        UUID componentID = UUID.randomUUID();
        Material material = new Material();

        material.setComponentID(componentID);
        material.setName(name);
        material.setQuantity(quantity);
        material.setUnitCost(unitCost);
        material.setTransportCost(transportCost);
        material.setQualityCoefficient(qualityCoefficient);
        material.setProject(project);

        materialsMap.put(componentID, material);
        System.out.println("Material added successfully!");
    }


    public void addNewLabor() {
        String name = validator.validateStringInput("Enter the type of labor (e.g., Basic Worker, Specialist): ");
        double hourlyRate = validator.validateDoubleInput("Enter the hourly rate for this labor (€ / h): ");
        double workingHours = validator.validateDoubleInput("Enter the number of working hours: ");
        double workerProductivity = validator.validateDoubleInput("Enter the productivity factor (1.0 = standard, > 1.0 = high productivity): ");

        UUID componentID = UUID.randomUUID();
        Labor labor = new Labor();

        labor.setComponentID(componentID);
        labor.setName(name);
        labor.setHourlyRate(hourlyRate);
        labor.setWorkingHours(workingHours);
        labor.setWorkerProductivity(workerProductivity);
        labor.setProject(project);

        laborsMap.put(componentID, labor);
        System.out.println("Labor added successfully!");
    }


    public void addVAT() {
        double VATRate = validator.validatePercentageInput("Enter the VAT percentage (%): ");

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
        double profitMargin = validator.validatePercentageInput("Enter the profit margin percentage (%): ");
        project.setProfitMargin(profitMargin);
    }

    public void insertAll() {
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

        saveQuote();
    }

    public void saveQuote() {
        Quote quote = new Quote();
        System.out.println("--- Quote Registration ---");

        Date issueDate = validator.validateDateInput("Enter the date the quote was issued (format: dd/MM/yyyy): ");
        quote.setIssueDate(issueDate);

        Date validityDate = validator.validateDateInput("Enter the validity date of the quote (format: dd/MM/yyyy): ");
        quote.setValidityDate(validityDate);

        quote.setEstimatedAmount(project.getTotalCost());
        quote.setProject(project);

        System.out.println("Would you like to save the quote? (y/n) : ");
        String confirmation = scan.nextLine();
        if (confirmation.equals("y")) {
            quoteService.addQuote(quote);
        }
    }


    public void selectProject() {
        List<Project> projects = projectService.getAllProjects();

        System.out.println("Available Projects:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("project index : " + i);
            System.out.println("project : " + projects.get(i).getProjectName());
            System.out.println("client : " + projects.get(i).getClient().getName());
            System.out.println("*******************************");
        }

        int selectedIndex = validator.validateIntegerInput("Please choose a project by entering the index: ", 0, projects.size() - 1);

        Project selectedProject = projects.get(selectedIndex);
        System.out.println("calculating Cost:");
        project = selectedProject;
        calculateProjectCost();
    }


    public void getProjectComponets() {
        List<Component> components = componentService.getProjectComponents(project.getProjectID());
        for (Component component : components) {
            if (component.getComponentType() == ComponentType.MATERIAL) {
                materialsMap.put(component.getComponentID(), (Material) component);
            }
            if (component.getComponentType() == ComponentType.MATERIAL) {
                laborsMap.put(component.getComponentID(), (Labor) component);
            }
        }
    }

    public void showAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        for (Project project : projects) {
            project.showDetails();
        }
    }

    public void calculateProjectCost() {
        double totalMaterialCost = 0;
        double totalLaborCost = 0;
        double profitMarginRate = project.getProfitMargin();

        System.out.println("--- Calculation Result ---");
        System.out.println("Project Name : " + project.getProjectName());
        System.out.println("Client : " + project.getClient().getName());
        System.out.println("Address : " + project.getClient().getAddress());
        System.out.println("Surface : " + project.getSurface() + "  m²");
        System.out.println("--- Cost Detail ---");

        if (!materialsMap.isEmpty()) {
            totalMaterialCost = calculateMaterialCost();
        }

        if (!laborsMap.isEmpty()) {
            totalLaborCost = calculateLaborCost();
        }

        double totalCost = totalMaterialCost + totalLaborCost;

        if (profitMarginRate > 0) {
            totalCost = calculateProfitMargin(totalCost, profitMarginRate);
        }

        if (client.isProfessional()) {
            totalCost = calculateDiscount(totalCost);
        }

        System.out.println("**Total final cost of the project : " + totalCost + " €**");

        project.setTotalCost(totalCost);
    }

    public double calculateMaterialCost() {
        double totalMaterialCost = 0;
        double vatRate = 0;
        System.out.println("1. Materials:");
        for (Map.Entry<UUID, Material> entry : materialsMap.entrySet()) {
            Material material = entry.getValue();
            double materialCost = material.calculateComponentCost();
            totalMaterialCost += materialCost;
            vatRate = material.getVATRate();
        }
        System.out.println("**Total cost of materials before VAT : " + totalMaterialCost + " €**");
        if (vatRate > 0) {
            totalMaterialCost = costAfterVAT(totalMaterialCost, vatRate);
            System.out.println("**Total cost of materials after VAT : " + totalMaterialCost + " €**");
        }

        return totalMaterialCost;
    }

    public double calculateLaborCost() {
        double totalLaborCost = 0;
        double vatRate = 0;

        System.out.println("2. Labor:");
        for (Map.Entry<UUID, Labor> entry : laborsMap.entrySet()) {
            Labor labor = entry.getValue();
            double laborCost = labor.calculateComponentCost();
            totalLaborCost += laborCost;
            vatRate = labor.getVATRate();
        }
        System.out.println("**Total cost of labors before VAT : " + totalLaborCost + " €**");
        if (vatRate > 0) {
            totalLaborCost = costAfterVAT(totalLaborCost, vatRate);
            System.out.println("**Total cost of labors after VAT : " + totalLaborCost + " €**");
        }

        return totalLaborCost;
    }

    public double calculateProfitMargin(double totalCost, double profitMarginRate) {
        System.out.println("3. Total cost before profit margin: " + totalCost + " €");
        double profitMargin = totalCost * (profitMarginRate / 100);
        System.out.println("4. Profit margin (" + profitMarginRate + "%): " + profitMargin + " €");
        return (totalCost + profitMargin);
    }

    public double calculateDiscount(double totalCost) {
        double discount = 0.1;
        double discountAmount = totalCost * discount;
        System.out.println("5. Total cost before discount: " + totalCost + " €");
        System.out.println("6. Discount amount (" + discountAmount + "%): " + discountAmount + " €");
        return (totalCost - discountAmount);
    }

    public double costAfterVAT(double totalCost, double vatRate) {
        return totalCost * (1 + vatRate / 100);
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
