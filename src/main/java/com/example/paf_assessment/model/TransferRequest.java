package com.example.paf_assessment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class TransferRequest {
    @Size(min = 10, message = "Account id must be at least 10 digit.")
    private String fromAccount;
    @Size(min = 10, message = "Account id must be at least 10 digit.")
    private String toAccount;
    @Min(value = 10, message = "Minimum transfer amount $10")
    private float amount;
    private String comments;
    private String transactionId;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public static void setTransactionId(String transactionId) {
    }

}
