package com.jdbc.app.dao;

import com.jdbc.app.model.User;
import com.jdbc.app.util.DBConnection;
import com.jdbc.app.util.LoadingAnimation;

import java.sql.*;

public class UserDao {
    //Insert user details
    public void insertUser(User user)  {
        String insertSql = "insert into user(user_name, user_password) values(?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUser_Name());
            ps.setString(2, user.getUser_Password());
            int result =ps.executeUpdate();
            if(result>0){
                System.out.print("Data inserting");
                new LoadingAnimation().animation();
                System.out.println();
                System.out.println("✅User inserted successfully");
                ResultSet rs = ps.getGeneratedKeys() ;
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Congratulation🎉🎉 Your User_ID is " + '"'+"USER12300S"+id+'"'+"\n");
                }
            }else{
                System.out.println("❌User inserted unsuccessful");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete user details
    public void deleteUser(String userId) {
        String deleteSql = "delete from user where user_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(deleteSql);

            int id = Integer.parseInt(userId.substring(userId.lastIndexOf('S')+1));
            System.out.println(id);
            ps.setInt(1, id);//add id to delete ?
            int result = ps.executeUpdate();
            if(result>0){
                System.out.print("Data Deleting");
                new LoadingAnimation().animation();
                System.out.println();
                System.out.println("✅User deleted successfully"+ "\n");
            }else {
                System.out.println("❌User deletion unsuccessful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Update user password
    public void updateUser(String userId,String userOldPassword,String newPassword) throws SQLException {
        //check user given password is same as old password
        String oldPasswordQuery = "select user_password from user where user_id = ?";

        //check user given old password is equal to stored password or not
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(oldPasswordQuery);

        //to get the real DATABASE Id
        int id = Integer.parseInt(userId.substring(userId.lastIndexOf('S')+1));

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String oldPassword = rs.getString("user_password");//get userPassword from DB
            System.out.print("Analizing password");
            new LoadingAnimation().animation();
            System.out.println();
            if (userOldPassword.equals(oldPassword)) {
                String updateSql = "update user set user_password = ? where user_id = ?";
                PreparedStatement ps1 = con.prepareStatement(updateSql);
                ps1.setString(1, newPassword);
                ps1.setInt(2, id);
                int result = ps1.executeUpdate();
                if(result>0){
                    System.out.print("Data updating");
                    new LoadingAnimation().animation();
                    System.out.println();
                    System.out.println("Data updated successfully✅");
                }
            } else {
                System.out.println("Wrong oldPassword❌");
            }
        }

    }
}
