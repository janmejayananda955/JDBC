package com.jdbc.app.main;

import com.jdbc.app.dao.UserDao;
import com.jdbc.app.model.User;
import com.jdbc.app.util.LoadingAnimation;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        UserDao userDao = new UserDao();

        while(true){
            System.out.println("What you want to do? \n1.Insert user \n2.Update user \n3.Delete user \n4.Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    //insert new user
                    System.out.println("Enter user_Name:");
                    String userName = sc.nextLine();
                    if (userName.trim().isEmpty() || userName==null) {
                        System.out.println("Name cannot be empty");
                        break;
                    }
                    System.out.println("Enter user_Password:");
                    String password = sc.next();
//                    User newUser = new User(userName, password);
                    userDao.insertUser(new User(userName, password));

                    break;

                case 2:
                    System.out.println("Enter user_ID:");
                    String userId = sc.next();
                    System.out.println("Enter user_OldPassword:");
                    String oldPassword = sc.next();
                    System.out.println("Enter user_NewPassword:");
                    String newPassword = sc.next();
                    userDao.updateUser(userId, oldPassword, newPassword);
                    break;
                case 3:
                    //delete user
                    System.out.println("Enter userID : ");
                    String userID = sc.nextLine();
                    userDao.deleteUser(userID);

                    break;
                case 4:
                    System.out.print("Closing Application");
                    LoadingAnimation animate = new LoadingAnimation();
                    animate.animation();
                    System.out.println();
                    System.out.println("Closing Successful✅");
                    return;
                default:
                    System.out.println("❌Invalid input");
            }
        }
    }
}
