package com.jdbc.app.main;

import com.jdbc.app.dao.UserDao;
import com.jdbc.app.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        UserDao userDao = new UserDao();

        while(true){
            System.out.println("What you want to do? \n1.Insert user \n2.Update user \n3.Delete user \n4.Exit");
            switch (sc.nextInt()) {
                case 1:
                    //insert new user
                    System.out.println("Enter user_Name:");
                    String userName = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Enter user_Password:");
                    String password = sc.next();
//                    User newUser = new User(userName, password);
                    userDao.insertUser(new User(userName, password));

                    break;
                case 2:
                    //delete user
                    System.out.println("Enter userID : ");
                    String userID = sc.nextLine();
                    userDao.deleteUser(userID);

                    break;
                case 4:
                    System.out.println("Closing Application...");
                    return;
                default:
                    System.out.println("❌Invalid input");
            }
        }
    }
}
