package BankManagementSystem;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void adminMenu(List<Employee> employees, List<Client> clients, List<Transaction> transactions) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Employees");
            System.out.println("2. View Clients");
            System.out.println("3. View Transactions by Date/Client/Employee");
            System.out.println("4. Authorize New Employee Accounts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (employees.isEmpty()) {
                        System.out.println("No clients found.");
                    } else {
                        System.out.println("--- Employees' Details ---");
                        for (Employee employee : employees) {
                            employee.displayDetails();
                        }
                    }
                    break;
                case 2:
                    displayClients(clients);
                    break;
                case 3:
                    viewTransactions(transactions, employees, clients);
                    break;
                case 4:
                    authorizeEmployee(employees);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayClients(List<Client> clients) {
        if (clients == null || clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            System.out.println("--- Clients' Details ---");
            for (Client client : clients) {
                client.displayDetails();
            }
        }

    }

    private void viewTransactions(List<Transaction> transactions, List<Employee> employees, List<Client> clients) {
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("--- Filter Transactions ---");
        System.out.println("1. By Date");
        System.out.println("2. By Client");
        System.out.println("3. By Employee");
        System.out.print("Choose an option: ");
        int filterChoice = sc.nextInt();
        sc.nextLine();

        switch (filterChoice) {
            case 1: // By Date
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                List<Transaction> dateFiltered = transactions.stream()
                        .filter(t -> t.getDate().equals(date))
                        .collect(Collectors.toList());
                displayTransactions(dateFiltered);
                break;

            case 2: // By Client
                System.out.print("Enter client ID: ");
                int clientId = sc.nextInt();
                List<Transaction> clientFiltered = transactions.stream()
                        .filter(t -> t.getClientId() == clientId)
                        .collect(Collectors.toList());
                displayTransactions(clientFiltered);
                break;

            case 3: // By Employee
                System.out.print("Enter employee ID: ");
                int employeeId = sc.nextInt();
                List<Transaction> employeeFiltered = transactions.stream()
                        .filter(t -> t.getEmployeeId() == employeeId)
                        .collect(Collectors.toList());
                displayTransactions(employeeFiltered);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found for the specified filter.");
        } else {
            System.out.println("--- Transactions ---");
            for (Transaction t : transactions) {
                System.out.println(t); // Assumes Transaction class has a toString() method
            }
        }
    }

    private void authorizeEmployee(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee ID to authorize: ");
        int empId = sc.nextInt();

        Employee employeeToAuthorize = employees.stream()
                .filter(e -> e.getId() == empId)
                .findFirst()
                .orElse(null);

        if (employeeToAuthorize != null) {
            employeeToAuthorize.setAuthorized(true);
            FileManager.saveEmployees(employees); // Assumes FileManager.saveEmployees is implemented
            System.out.println("Employee authorized successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
