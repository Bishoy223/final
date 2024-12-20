package BankManagementSystem;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String CLIENTS_FILE = "clients.txt";

    // Save employees to file
    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEES_FILE))) {
            for (Employee emp : employees) {
                // Write employee data in CSV format
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%.2f,%s,%b\n",
                        emp.getId(),
                        emp.firstName,
                        emp.lastName,
                        emp.address,
                        emp.position,
                        emp.graduatedCollege,
                        emp.yearOfGraduation,
                        emp.totalGrade,
                        emp.isAuthorized()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    // Read employees from file
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(EMPLOYEES_FILE);

        if (!file.exists()) {
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = new Employee(
                        data[1],    // firstName
                        data[2],    // lastName
                        data[3],    // address
                        data[4],    // position
                        data[5],    // graduatedCollege
                        Double.parseDouble(data[6]), // yearOfGraduation
                        data[7]     // totalGrade
                );
                emp.setAuthorized(Boolean.parseBoolean(data[8]));
                employees.add(emp);
            }
        } catch (IOException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
        return employees;
    }

    // Save clients to file
    public static void saveClients(List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE))) {
            for (Client client : clients) {
                // Write client data in CSV format
                writer.write(String.format("%s,%s,%s,%s,%s,%.2f\n",
                        client.getID(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getUsername(),
                        client.getTelephoneNumber(),
                        client.getAccountNumber().getBalance()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving clients: " + e.getMessage());
        }
    }

    // Read clients from file
    public static List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        File file = new File(CLIENTS_FILE);

        if (!file.exists()) {
            return clients;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Client client = new Client(
                        data[0],    // ID
                        data[1],    // firstName
                        data[2],    // lastName
                        data[3],    // username
                        data[4],    // telephoneNumber
                        Double.parseDouble(data[5]) // initialBalance
                );
                clients.add(client);
            }
        } catch (IOException e) {
            System.err.println("Error loading clients: " + e.getMessage());
        }
        return clients;
    }
}
