package BankManagementSystem;

public class CreditCard {

    private double limit;
    private double balance;
    private int loyaltyPoints;

    public CreditCard(double limit) {
        this.limit = limit;
        this.balance = limit;
        this.loyaltyPoints = 0;
    }

    public boolean makePayment(double amount) {
        if (amount <= balance) {
            balance -= amount;
            loyaltyPoints += (int) (amount * 1); // 100 loyalty point per 100 LE
            System.out.println("Payment successful. Remaining credit: " + balance);
            System.out.println("Loyalty points earned: " + loyaltyPoints);
        } else {
            System.out.println("Insufficient credit limit.");
        }
        return true;
    }

    public void displayCreditCardDetails() {
        System.out.println("Credit Card Limit: " + limit);
        System.out.println("Available Balance: " + balance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
    }

    public void pay(double amount) {
        // TODO Auto-generated method stub

    }



}
