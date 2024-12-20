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
    ArrayList<Client> clients = new ArrayList<>();


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


    public void employeeMenu(List<Employee> employees,List<Client> clients, Map<String, Account> accounts) {
        Scanner sc = new Scanner(System.in);
        SystemManager system = new SystemManager();
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Edit Employee Information");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Add Employee
                    System.out.print("Enter Employee First Name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Employee Last Name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String empAddress = sc.nextLine();
                    System.out.print("Enter Position: ");
                    String empPosition = sc.nextLine();
                    System.out.print("Enter College: ");
                    String graduatedCollege = sc.nextLine();
                    System.out.print("Enter Year of Graduation: ");
                    int yearOfGraduation = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Total Grade: ");
                    String totalGrade = sc.nextLine();
                    system.addEmployee(firstName, lastName, empAddress, empPosition, graduatedCollege, yearOfGraduation, totalGrade);
                    break;
                case 2: // Edit Employee Information
                    System.out.print("Enter Employee ID to Edit: ");
                    int empId = Integer.parseInt(sc.nextLine());
                    if (system.findEmployee(empId) == null) {
                        System.out.println("Employee not found.");
                        break;
                    }
                    System.out.print("Enter New Address (leave blank to skip): ");
                    String newAddress = sc.nextLine();
                    System.out.print("Enter New Position (leave blank to skip): ");
                    String newPosition = sc.nextLine();
                    system.editEmployee(empId, newAddress, newPosition);
                    break;
                case 3: // Display Employees
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
                case 4: // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void editInfo(String address, String position) {
        if (address != null && !address.isEmpty()) {
            this.address = address;
        }
        if (position != null && !position.isEmpty()) {
            this.position = position;
        }
        System.out.println("Employee information updated.");
    }


    @Override
    public String toString() {
        return "Employee[ID=" + id + ", Name=" + firstName + " " + lastName + ", Address=" + address +
                ", Position=" + position + ", College=" + graduatedCollege + ", Year=" + yearOfGraduation +
                ", Grade=" + totalGrade + "]";
    }
}