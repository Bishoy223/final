package BankManagementSystem;

import java.util.List;
import java.util.Scanner;
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

    public void adminMenu(List<Employee> employees, List<Client> clients) {
        Scanner sc = new Scanner(System.in);
        SystemManager system = new SystemManager();
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
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    if (clients.isEmpty()) {
                        System.out.println("No clients found.");
                    } else {
                        System.out.println("--- Clients' Details ---");
                        for (Client client : clients) {
                            client.displayDetails();
                            System.out.println();
                        }
                    }
                    break;
                case 3:
                    break;
                case 4:
                    authorizeEmployee(employees);
                    return;
                case 5: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void authorizeEmployee(List<Employee> employees) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee ID to authorize: ");
        String empId = sc.nextLine(); // Input is a String
        for (Employee e : employees) {
            if (String.valueOf(e.getId()).equals(empId)) {
                e.setAuthorized(true);
                FileManager.saveEmployees(employees);
                System.out.println("Employee authorized successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
