package com.example.paf_assessment.model;


public class Accounts {

    private String account_id;
    private String name;
    private Float balance;
    private Float amount;
    private String comments;

    public String getAccount_id() {
        return account_id;
    }
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
