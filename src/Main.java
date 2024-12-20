// Main Class
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.initialize();
        bank.runSystem();
    }
}

// 1. Bank Class
class Bank {
    private List<Employee> employees;
    private List<Client> clients;
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
        employees.add(new Employee("E001", "John", "Doe", "Manager"));
        clients.add(new Client("C001", "Tom", "Hanks", "tom123", "password"));
        accounts.put("A001", new SavingsAccount("A001", 10000));
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
                admin.adminMenu(employees, clients);
            } else if (authenticateEmployee(username, password)) {
                Employee emp = getEmployeeByUsername(username);
                emp.employeeMenu(clients, accounts);
            } else if (authenticateClient(username, password)) {
                Client client = getClientByUsername(username);
                client.clientMenu(accounts);
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
            if (e.getId().equals(username)) return e;
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

// 2. Admin Class
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
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Employees");
            System.out.println("2. View Clients");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayEmployees(employees);
                    break;
                case 2:
                    displayClients(clients);
                    break;
                case 3:
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

    private void displayClients(List<Client> clients) {
        for (Client c : clients) {
            System.out.println(c);
        }
    }
}

// 3. Employee Class
class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String role;

    public Employee(String id, String firstName, String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public boolean authenticate(String username, String password) {
        return id.equals(username) && "password".equals(password); // Simplified
    }

    public String getId() {
        return id;
    }

    public void employeeMenu(List<Client> clients, Map<String, Account> accounts) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. View Clients");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayClients(clients);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayClients(List<Client> clients) {
        for (Client c : clients) {
            System.out.println(c);
        }
    }

    @Override
    public String toString() {
        return "Employee[ID=" + id + ", Name=" + firstName + " " + lastName + ", Role=" + role + "]";
    }
}

// 4. Client Class
class Client {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Client(String id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public void clientMenu(Map<String, Account> accounts) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. View Account Details");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayAccountDetails(accounts);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayAccountDetails(Map<String, Account> accounts) {
        for (Account a : accounts.values()) {
            System.out.println(a);
        }
    }

    @Override
    public String toString() {
        return "Client[ID=" + id + ", Name=" + firstName + " " + lastName + "]";
    }
}

// 5. Account Class
abstract class Account {
    protected String accountId;
    protected double balance;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    @Override
    public String toString() {
        return "Account[ID=" + accountId + ", Balance=" + balance + "]";
    }
}

// 6. SavingsAccount Class
class SavingsAccount extends Account {
    public SavingsAccount(String accountId, double balance) {
        super(accountId, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

// 7. CurrentAccount Class
class CurrentAccount extends Account {
    public CurrentAccount(String accountId, double balance) {
        super(accountId, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Overdraft not allowed.");
        }
    }
}

// 8. Transaction Class
class Transaction {
    private String transactionId;
    private String accountId;
    private double amount;
    private Date date;

    public Transaction(String transactionId, String accountId, double amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction[ID=" + transactionId + ", Account=" + accountId + ", Amount=" + amount + ", Date=" + date + "]";
    }
}
