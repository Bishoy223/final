package BankManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private double balance;
    private CreditCard creditCard;
    private ArrayList<String> transactionHistory; // New attribute for transaction history

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with initial balance: " + initialBalance);
    }

    public double getBalance() {

        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean transfer(double amount, Account targetAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount);
            addTransaction("Transferred: " + amount + " to another account");
            targetAccount.addTransaction("Received: " + amount + " from another account");
            return true;
        } else {
            System.out.println("Transfer failed. Insufficient balance or invalid amount.");
            return false;
        }
    }

    public void requestCreditCard() {
        if (creditCard == null) {
            creditCard = new CreditCard(20000); // Fixed credit limit
            addTransaction("Credit card issued with limit: 20000");
            System.out.println("Credit card issued successfully.");
        } else {
            System.out.println("You already have a credit card.");
        }
    }

    public void payWithCreditCard(double amount) {
        if (creditCard != null) {
            if (creditCard.makePayment(amount)) {
                addTransaction("Paid with credit card: " + amount);
                System.out.println("Payment successful.");
            }
        } else {
            System.out.println("No credit card associated with the account.");
        }
    }

    public void disableCreditCard() {
        if (creditCard != null) {
            addTransaction("Credit card disabled.");
            creditCard = null;
            System.out.println("Credit card disabled successfully.");
        } else {
            System.out.println("No credit card to disable.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private void addTransaction(String transactionDetail) {
        transactionHistory.add(transactionDetail);
    }

    public void displayAccountDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAccountDetails'");
    }
}

