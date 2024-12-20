package BankManagementSystem;

import java.time.LocalDateTime;

class Transaction {
    private String transactionId;
    private String accountId;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
    private TransactionType type;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER_IN,
        TRANSFER_OUT,
        CREDIT_CARD_PAYMENT
    }

    public Transaction(String transactionId, String accountId, double amount, TransactionType type, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction[ID=" + transactionId +
                ", AccountID=" + accountId +
                ", Amount=" + amount +
                ", Type=" + type +
                ", Description=" + description +
                ", Timestamp=" + timestamp + "]";
    }
}

