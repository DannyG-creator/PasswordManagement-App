package com.example.passwordmanager;

public class User {
    private String Account;
    private String Password;

    public User(String account, String password){
        Account = account;
        Password = password;
    }

    public String getAccount() {
        return Account;
    }

    public String getPassword() {
        return Password;
    }
}