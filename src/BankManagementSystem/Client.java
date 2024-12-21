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
        //username=client's username , password=pass
        return this.username.equals(username) && "password".equals(password);
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



    public void clientMenu(Map<String, Account> accounts, List<Client> clients) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Edit Personal Information for a Client");
            System.out.println("2. Perform Operations on a Client's Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {


                case 1:
                    // Edit client information
                    System.out.print("Enter the ID of the client you want to edit: ");
                    String editID = scanner.next();
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
                        int editChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (editChoice) {
                            case 1:
                                System.out.print("Enter new first name: ");
                                String newFirstName = scanner.nextLine();
                                clientToEdit.setFirstName(newFirstName);
                                System.out.println("First name updated successfully.");
                                break;

                            case 2:
                                System.out.print("Enter new last name: ");
                                String newLastName = scanner.nextLine();
                                clientToEdit.setLastName(newLastName);
                                System.out.println("Last name updated successfully.");
                                break;

                            case 3:
                                System.out.print("Enter new username: ");
                                String newUsername = scanner.nextLine();
                                clientToEdit.setUsername(newUsername);
                                System.out.println("Username updated successfully.");
                                break;

                            case 4:
                                System.out.print("Enter new telephone number: ");
                                String newTelephoneNumber = scanner.nextLine();
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

                case 2:
                    // Perform operations on a client's account
                    System.out.print("Enter the ID of the client whose account you want to access: ");
                    String clientID = scanner.next();
                    Client client = findClientByID((ArrayList<Client>) clients, clientID);

                    if (client != null) {
                        performAccountOperations(scanner, client, (ArrayList<Client>) clients);
                    } else {
                        System.out.println("Client with ID " + clientID + " not found.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
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
