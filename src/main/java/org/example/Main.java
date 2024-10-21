package org.example;

import org.example.csv.ReadCSV;
import org.example.dao.AccountsDAO;
import org.example.entity.Accounts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        List<Accounts> accounts = ReadCSV.readExcel("./src/main/resources/account_list.xlsx", "Sheet1");



        for (Accounts acc : accounts) {
            System.out.println(acc); // Will call the toString() method
        }

        System.out.println(AccountsDAO.addAccount(accounts));
    }


}
