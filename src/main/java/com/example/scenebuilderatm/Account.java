package com.example.scenebuilderatm;

public class Account {
    private int id;
    private String password = "password";
    public double balance = 100.0;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getBalance() {
        return balance;
    }
    public void withdraw(double amount) {
        balance -= amount;
    }
    public void deposit(double amount) {
        balance += amount;
    }
}
