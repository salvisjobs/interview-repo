package com.hackerrank.banksystem;

public class Account {

    private String accountId;
    private String userName;
    private String userAccountToken;

    private int accountBalance;

    public Account(String accountId, String userName) {
        this.accountId = accountId;
        this.userName = userName;
    }

    public Account(String accountId, String userName, String userAccountToken) {
        this.accountId = accountId;
        this.userName = userName;
        this.userAccountToken = userAccountToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccountToken() {
        return userAccountToken;
    }

    public void setUserAccountToken(String userAccountToken) {
        this.userAccountToken = userAccountToken;
    }

    public int getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

}
