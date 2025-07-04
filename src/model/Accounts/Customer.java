package model.Accounts;

import model.Cart.Cart;

public class Customer {
    private String name ;
    private double balance;
    private Cart cart ;

    public Customer(String name) {
        super();
        this.name = name;
    }

    public Customer(String name, double balance) {
        super();
        this.name = name;
        this.balance = balance;
    }


    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
//
//    public void deduct(double amount) {
//        balance -= amount;
//    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
