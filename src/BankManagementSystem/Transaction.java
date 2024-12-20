package BankManagementSystem;

public class Transaction {
    private String date;
    private int clientId;
    private int employeeId;
    private double amount;

    public Transaction(String date, int clientId, int employeeId, double amount) {
        this.date = date;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public int getClientId() {
        return clientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", clientId=" + clientId +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                '}';
    }
}
