package com.example.dth_project;

public class MovieModal {
    private String name;
    private String address;
    private int phone;
    private int balance;

    public MovieModal(String name, String address, int phone, int balance) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String custname) {
        this.name = custname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String custaddress) {
        this.address = custaddress;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int custphone) {
        this.phone = custphone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int custbalance) {
        this.balance = custbalance;
    }
}
