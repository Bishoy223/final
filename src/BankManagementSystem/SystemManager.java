package BankManagementSystem;
import java.util.ArrayList;
import java.util.List;
public class SystemManager {
    private List<Employee> employees = new ArrayList<>();
    private List<Client> clients=new ArrayList<>();

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void addEmployee(String firstName, String lastName, String address, String position,
                            String graduatedCollege, int yearOfGraduation, String totalGrade) {
        employees.add(new Employee(firstName, lastName, address, position,
                graduatedCollege, yearOfGraduation, totalGrade));
        FileManager.saveEmployees(employees);
        System.out.println("Employee added.");
    }

    public Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void editEmployee(int id, String newAddress, String newPosition) {
        for (Employee employee : employees) {
            if (employee.getId()==id) { // Use .equals() for string comparison
                employee.editInfo(newAddress, newPosition);
                FileManager.saveEmployees(employees);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("\nEmployee List:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

}