package BankManagementSystem;

import java.util.*;

class Bank {
    private List<Employee> employees;
    private List<Client> clients;
    private List<Transaction> transactions;
    private Map<String, Account> accounts;
    private Admin admin;

    public Bank() {
        employees = new ArrayList<>();
        clients = new ArrayList<>();
        accounts = new HashMap<>();

        admin = new Admin("admin", "admin");
    }

    public void initialize() {
        // Load data or initialize with default values
        employees = FileManager.loadEmployees();
        clients = FileManager.loadClients();
        if (employees.isEmpty()) {
            employees.add(new Employee("Hamza","Ahmed","Tagmo3","Manager","ASU",2027,"Very good"));
            employees.add(new Employee("Abdelrahman","Ayman","Yasmin","CEO","ASU",2028,"Excellent"));
            employees.add(new Employee("Hassan","Ahmed","Banfseg","COO","ASU",2029,"Good"));
            FileManager.saveEmployees(employees);
        }

        if (clients.isEmpty()) {
            clients.add(new Client("1", "Akram", "Ayman", "akram123", "01150526011", 2000));
            clients.add(new Client("2", "Yassin", "Ayman", "yassin123", "01222216900", 2000));
            clients.add(new Client("3", "Bishoy", "Ayman", "bishoy123", "01224784528", 2000));
            clients.add(new Client("4", "Ziad", "Kamal", "ziad123", "010302855356", 2000));
            clients.add(new Client("5", "Moaiyed", "Hady", "mo123", "01277674946", 2000));
            FileManager.saveClients(clients);
        }
    }

    public void runSystem() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Bank Management System");
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (admin.authenticate(username, password)) {
                admin.adminMenu(employees, clients,transactions);
            } else if (authenticateEmployee(username, password)) {
                Employee emp = getEmployeeByUsername(username);
                emp.employeeMenu(employees, accounts,clients);
            } else if (authenticateClient(username, password)) {
                Client client = getClientByUsername(username);
                client.clientMenu(accounts,clients);
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }
    }

    private boolean authenticateEmployee(String username, String password) {
        for (Employee e : employees) {
            if (e.authenticate(username, password)) return true;
        }
        return false;
    }

    private boolean authenticateClient(String username, String password) {
        for (Client c : clients) {
            if (c.authenticate(username, password)) return true;
        }
        return false;
    }

    private Employee getEmployeeByUsername(String username) {
        for (Employee e : employees) {
            if (String.valueOf(e.getId()).equals(username)) { // Convert int to String for comparison
                return e;
            }
        }
        return null;
    }

    private Client getClientByUsername(String username) {
        for (Client c : clients) {
            if (c.getUsername().equals(username)) return c;
        }
        return null;
    }

}
