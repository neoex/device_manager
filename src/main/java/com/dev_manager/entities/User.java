package com.dev_manager.entities;

public class User {
    private int userId;
    private String account;
    private String password;
    private int roleLevel;
    private String roleName;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
