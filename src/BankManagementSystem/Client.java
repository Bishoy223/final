package BankManagementSystem;

import java.util.*;

class Client {
    private String ID;
    private String firstName;
    private String lastName;
    private String username;
    private String telephoneNumber;
    private Account accountNumber;
    ArrayList<Client> clients = new ArrayList<>();// List to store multiple clients




    public Client(String ID, String firstName, String lastName, String username, String telephoneNumber, double initialBalance) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.telephoneNumber = telephoneNumber;
        this.accountNumber = new Account(initialBalance);
    }

    public boolean authenticate(String username, String password) {

        return this.username.equals(username) && "akram123".equals(password);
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Account getAccountNumber() {
        return accountNumber;
    }

    public void displayDetails() {

        System.out.println("Client[ID="+ ID +
                ", FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Username=" + username +
                ", Telephone Number=" + telephoneNumber + "]");
    }



    public void clientMenu(Map<String, Account> accounts,List<Client> clients) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Add New Client");
            System.out.println("2. Display All Clients' Details");
            System.out.println("3. Edit Personal Information for a Client");
            System.out.println("4. Perform Operations on a Client's Account");
            System.out.println("5. Delete Client");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Add new client
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


                case 4:


                case 5:

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
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

    private static void performAccountOperations(Scanner scanner, Client client, ArrayList<Client> clients) {
        while (true) {
            System.out.println("\n--- Account Operations for " + client.getUsername() + " ---");
            System.out.println("1. Display Account Details");
            System.out.println("2. Transfer Money");
            System.out.println("3. Request Credit Card");
            System.out.println("4. Pay with Credit Card");
            System.out.println("5. Disable Credit Card");
            System.out.println("6. Deposit Money");
            System.out.println("7. Show Transaction History");
            System.out.println("8. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    client.displayDetails();
                    break;

                case 2:
                    System.out.print("Enter the ID of the client you want to transfer money to: ");
                    String targetClientID = scanner.next();
                    Client targetClient = findClientByID(clients, targetClientID);

                    if (targetClient != null) {
                        System.out.print("Enter the amount to transfer: ");
                        double amount = scanner.nextDouble();
                        if (client.getAccountNumber().transfer(amount, targetClient.getAccountNumber())) {
                            System.out.println("Transferred " + amount + " to " + targetClient.getUsername() + " successfully.");
                        } else {
                            System.out.println("Transfer failed. Insufficient balance.");
                        }
                    } else {
                        System.out.println("Client with ID " + targetClientID + " not found.");
                    }
                    break;

                case 3:
                    client.getAccountNumber().requestCreditCard();
                    break;

                case 4:
                    System.out.print("Enter payment amount: ");
                    double payment = scanner.nextDouble();
                    client.getAccountNumber().payWithCreditCard(payment);
                    break;

                case 5:
                    client.getAccountNumber().disableCreditCard();
                    break;

                case 6:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    client.getAccountNumber().deposit(deposit);
                    break;

                case 7:
                    client.getAccountNumber().displayTransactionHistory();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}