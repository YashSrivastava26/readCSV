package org.example.dao;

import org.example.entity.Accounts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AccountsDAO {
    public static boolean addAccount(List<Accounts> accounts){
        Configuration con = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = con.buildSessionFactory();
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction(); // Start a transaction

            for (Accounts account : accounts) {
                session.save(account);  // Save each account
            }

            session.getTransaction().commit(); // Commit the transaction
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close(); // Close the session
            }
        }
    }
}