package com.example.user_interface;

public class ClientObject {

    private String clientName , password , phoneNumber  ;
    private int accountBalance ;


    public ClientObject(String clientName, String password, String email) {
        this.clientName = clientName;
        this.password = password;
        this.phoneNumber = email;
        this.accountBalance = 0;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return phoneNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.phoneNumber = email;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
}
