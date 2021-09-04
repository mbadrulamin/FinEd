package com.enigma.finexapp;

public class UserInfo {
    private static Integer balance;
    private static Integer spend;


    public UserInfo() {

    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        UserInfo.balance = balance;
    }

    public Integer getSpend() {
        return spend;
    }

    public void setSpend(Integer spend) {
        UserInfo.spend = spend;
    }
}
