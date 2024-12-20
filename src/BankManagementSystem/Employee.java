package BankManagementSystem;
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


    public void employeeMenu(List<Employee> employees, Map<String, Account> accounts) {
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
                    addEmployee(employees);
                    break;
                case 2: // Edit Employee Information
                    editEmployee(employees);

                    break;
                case 3: // Display Employees
                    if (employees.isEmpty()) {
                        System.out.println("No clients found.");
                    } else {
                        System.out.println("--- Employees' Details ---");
                        for (Employee employee : employees) {
                            employee.displayDetails();
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
