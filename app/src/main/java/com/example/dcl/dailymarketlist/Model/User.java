package com.example.dcl.dailymarketlist.Model;

public class User {
    private String Number;
    private  String Name;
    private  String Password;

    public User() {
    }

    public User(String number, String name, String password) {
        Number = number;
        Name = name;
        Password = password;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
