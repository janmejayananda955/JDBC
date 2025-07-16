package com.jdbc.transactionConcept;

import com.jdbc.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//here also handled insufficient balance

public class Transaction {
    public static void main(String[] args) {
        String withdrawalSql = "update transaction set balance = balance - ? where account_no = ? and balance >= ?";

        String depositSql = "update transaction set balance = balance + ? where account_no = ?";
        try {
            Connection con = DBConnection.getConnection();
            System.out.println("Connection extablished");
            con.setAutoCommit(false);
            PreparedStatement withdrawalPs = con.prepareStatement(withdrawalSql);
            PreparedStatement depositPs = con.prepareStatement(depositSql);
            double amount = 4000.0;
            withdrawalPs.setDouble(1, amount);
            withdrawalPs.setString(2, "account123");
            withdrawalPs.setDouble(3, amount);

            depositPs.setDouble(1, amount);
            depositPs.setString(2, "account456");

            int withdrawalResult = withdrawalPs.executeUpdate();
            int depositResult = depositPs.executeUpdate();
            if (withdrawalResult > 0){
                if(depositResult > 0) {
                    con.commit();
                    System.out.println("Transaction Successful");
                }else {
                    con.rollback();
                    System.out.println("Transaction Failed");
                }
            }else{
                System.out.println("Insufficient Funds");
            }
            con.close();
            withdrawalPs.close();
            depositPs.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
