package com.hackerrank.banksystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Bank {
    private List<Account> accounts;

    public List<Account> getAccounts() {
        if(Objects.isNull(this.accounts)){
            accounts = new ArrayList<>();
        }
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        try{
            getAccount(account.getAccountId());
        }catch (TransactionException ex) {
            accounts.add(account);
        }
    }

    public void addMoney(Account account, int ammount) throws TransactionException {
        if(ammount <= 0){
            throw new TransactionException(new ResponseError("Amount should be greater than zero", "INVALID_AMOUNT"));
        }
        Account a = getAccount(account.getAccountId());
        a.setAccountBalance(a.getAccountBalance() + ammount);
    }

    public void withdrawMoney(Account account, int ammount) throws TransactionException {
        Account a = getAccount(account.getAccountId());
        if(ammount > a.getAccountBalance() ){
                throw new TransactionException(new ResponseError("Insufficient balance", "INSUFFICIENT_BALANCE"));
        }
        a.setAccountBalance(a.getAccountBalance() - ammount);
    }

    public Account getAccount(String account) throws TransactionException {
        Optional<Account> existentAccount =  getAccounts().stream()
                .filter(a -> a.getAccountId().equals(account)).findFirst();
        if(!existentAccount.isPresent()){
            throw new TransactionException(new ResponseError("User not authorized", "USER_NOT_AUTHORIZED"));
        }
        return existentAccount.get();
    }



}
