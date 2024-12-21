package BankManagementSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
class Employee {
    public int id;
    public String firstName;
    public String lastName;
    public String address;
    public String position;
    public String graduatedCollege;
    public double yearOfGraduation;
    public String totalGrade;
    private boolean isAuthorized;
    private static int idCounter = 1;

    public Employee(String firstName, String lastName, String address, String position, String graduatedCollege, double yearOfGraduation, String totalGrade) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.totalGrade = totalGrade;
        this.isAuthorized = false;

    }

    public boolean authenticate(String id, String password) {

        //username=ID , password=password
        return id.equals(id) && "password".equals(password);
    }

    public boolean isAuthorized() {

        return isAuthorized;
    }

    public void setAuthorized(boolean isAuthorized) {

        this.isAuthorized = isAuthorized;
    }

    public int getId() {
        return id;

    }

    public void displayDetails() {

        System.out.println("Employee[ID=" + id + ", Name=" + firstName + " " + lastName + ", Address=" + address +
                ", Position=" + position + ", College=" + graduatedCollege + ", Year=" + yearOfGraduation +
                ", Grade=" + totalGrade + "]");
    }


    public void employeeMenu(List<Employee> employees, Map<String, Account> accounts, List<Client> clients) {
        Scanner sc = new Scanner(System.in);
        SystemManager system = new SystemManager();
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Add New Client");
            System.out.println("3. Edit Personal Information for a Client");
            System.out.println("4. Edit Employee Information");
            System.out.println("5. Display Employees");
            System.out.println("6. Display All Clients' Details");
            System.out.println("7. Delete Client");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Add Employee
                    addEmployee(employees);
                    break;
                case 2:// Add a new client
                    System.out.print("Enter ID: ");
                    String ID = sc.next();

                    System.out.print("Enter first name: ");
                    String firstName = sc.next();

                    System.out.print("Enter last name: ");
                    String lastName = sc.next();

                    System.out.print("Enter username: ");
                    String username = sc.next();

                    System.out.print("Enter telephone number: ");
                    String telephoneNumber = sc.next();

                    System.out.print("Enter initial balance: ");
                    double initialBalance = sc.nextDouble();
                    clients.add(new Client(ID, firstName, lastName, username, telephoneNumber, initialBalance));
                    FileManager.saveClients(clients);
                    System.out.println("Client added successfully.");
                    break;
                case 3:// Edit client information
                    System.out.print("Enter the ID of the client you want to edit: ");
                    String editID = sc.next();
                    Client clientToEdit = findClientByID((ArrayList<Client>) clients, editID);

                    if (clientToEdit != null) {
                        System.out.println("Current details:");
                        clientToEdit.displayDetails();

                        System.out.println("\nWhat would you like to edit?");
                        System.out.println("1. First Name");
                        System.out.println("2. Last Name");
                        System.out.println("3. Username");
                        System.out.println("4. Telephone Number");
                        System.out.println("5. Cancel");
                        System.out.print("Choose an option: ");
                        int editChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        switch (editChoice) {
                            case 1:
                                System.out.print("Enter new first name: ");
                                String newFirstName = sc.nextLine();
                                clientToEdit.setFirstName(newFirstName);
                                System.out.println("First name updated successfully.");
                                break;

                            case 2:
                                System.out.print("Enter new last name: ");
                                String newLastName = sc.nextLine();
                                clientToEdit.setLastName(newLastName);
                                System.out.println("Last name updated successfully.");
                                break;

                            case 3:
                                System.out.print("Enter new username: ");
                                String newUsername = sc.nextLine();
                                clientToEdit.setUsername(newUsername);
                                System.out.println("Username updated successfully.");
                                break;

                            case 4:
                                System.out.print("Enter new telephone number: ");
                                String newTelephoneNumber = sc.nextLine();
                                clientToEdit.setTelephoneNumber(newTelephoneNumber);
                                System.out.println("Telephone number updated successfully.");
                                break;

                            case 5:
                                System.out.println("Edit canceled.");
                                break;

                            default:
                                System.out.println("Invalid choice. Returning to main menu.");
                        }

                        // Save updated clients list
                        FileManager.saveClients(clients);
                    } else {
                        System.out.println("Client with ID " + editID + " not found.");
                    }
                    break;

                case 4: // Edit Employee Information
                    editEmployee(employees);

                    break;
                case 5: // Display Employees
                    if (employees.isEmpty()) {
                        System.out.println("No clients found.");
                    } else {
                        System.out.println("--- Employees' Details ---");
                        for (Employee employee : employees) {
                            employee.displayDetails();
                        }
                    }
                    break;
                case 6:
                    if (clients.isEmpty()) {
                        System.out.println("No clients found.");
                    } else {
                        System.out.println("--- Clients' Details ---");
                        for (Client client : clients) {
                            client.displayDetails();
                        }
                    }
                    break;
                    case 7:// Delete client
                    System.out.print("Enter the ID of the client you want to delete: ");
                String deleteID = sc.next();
                if (deleteClient((ArrayList<Client>) clients, deleteID)) {
                    System.out.println("Client deleted successfully.");
                } else {
                    System.out.println("Client with ID " + deleteID + " not found.");
                }
                FileManager.saveClients(clients);
                break;
                case 8: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    private static Client findClientByID(ArrayList<Client> clients, String ID) {
        for (Client client : clients) {
            if (client.getID().equals(ID)) {
                return client;
            }
        }
        return null;
    }
    private static boolean deleteClient(ArrayList<Client> clients, String ID) {
        for (Client client : clients) {
            if (client.getID().equals(ID)) {
                clients.remove(client);
                return true;
            }
        }
        return false;
    }
    private void addEmployee(List<Employee> employees) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
        System.out.print("Enter College: ");
        String college = sc.nextLine();
        System.out.print("Enter Year of Graduation: ");
        double yearOfGraduation = sc.nextDouble();
        sc.nextLine(); // Consume the newline
        System.out.print("Enter Total Grade: ");
        String grade = sc.nextLine();

        Employee newEmployee = new Employee(firstName, lastName, address, position, college, yearOfGraduation, grade);
        employees.add(newEmployee);

        FileManager.saveEmployees(employees);
        System.out.println("Employee added successfully!");
    }

    public void editEmployee(List<Employee> employees) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID to Edit: ");
        int empId = sc.nextInt();
        sc.nextLine(); // Consume the newline

        Employee employeeToEdit = employees.stream()
                .filter(e -> e.getId() == empId)
                .findFirst()
                .orElse(null);

        if (employeeToEdit == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter New Address (leave blank to skip): ");
        String newAddress = sc.nextLine();
        System.out.print("Enter New Position (leave blank to skip): ");
        String newPosition = sc.nextLine();

        if (!newAddress.isEmpty()) {
            employeeToEdit.address = newAddress;
        }
        if (!newPosition.isEmpty()) {
            employeeToEdit.position = newPosition;
        }

        FileManager.saveEmployees(employees);
        System.out.println("Employee information updated successfully!");
    }
}
