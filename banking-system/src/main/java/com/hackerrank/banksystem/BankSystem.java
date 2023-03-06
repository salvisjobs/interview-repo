package com.hackerrank.banksystem;

import java.util.Scanner;

public class BankSystem {

    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final Bank bank = new Bank();


    public static void main(String[] args) throws TransactionException {
        int numberOfAccounts = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfAccounts-- > 0) {
            String[] accounts = INPUT_READER.nextLine().split(" ");
            Account account;

            if (accounts.length == 2) {
                account = new Account(accounts[0], accounts[1]);
            } else {
                account = new Account(accounts[0], accounts[1], accounts[2]);
            }

            bank.addAccount(account);
        }

        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfTransactions-- > 0) {
            String[] transaction = INPUT_READER.nextLine().split(" ");
            Account account = bank.getAccount(transaction[0]);

            if (transaction[1].equals("add")) {
                try {
                    bank.addMoney(account, Integer.parseInt(transaction[2]));
                    System.out.println("Account successfully credited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getResponseError().getErrorCode() + ": " + ex.getResponseError().getMessage() + ".");
                }
            } else {
                try {
                    bank.withdrawMoney(account, Integer.parseInt(transaction[2]));
                    System.out.println("Account successfully debited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getResponseError().getErrorCode() + ": " + ex.getResponseError().getMessage() + ".");
                }
            }
        }

        for (Account a : bank.getAccounts()) {
            System.out.println("\n" + a.getAccountId() +  " " + a.getUserName() + " " + a.getAccountBalance());
        }

    }
}
