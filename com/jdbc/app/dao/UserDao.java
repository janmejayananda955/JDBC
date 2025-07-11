package com.jdbc.app.dao;

import com.jdbc.app.model.User;
import com.jdbc.app.util.DBConnection;

import java.sql.*;

public class UserDao {
    //Insert user details
    public void insertUser(User user) throws SQLException {
        String insertSql = "insert into user(user_name, user_password) values(?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUser_Name());
            ps.setString(2, user.getUser_Password());
            int result =ps.executeUpdate();
            if(result>0){
                System.out.println("✅User inserted successfully");
                ResultSet rs = ps.getGeneratedKeys() ;
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Congratulation🎉🎉 Your User_ID is " + '"'+"USER12300S"+id+'"');
                }
            }else{
                System.out.println("❌User inserted unsuccessful");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete user details
    public void deleteUser(String userId) throws SQLException {
        String deleteSql = "delete from user where user_id = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(deleteSql);

            int id = Integer.parseInt(userId.substring(userId.lastIndexOf('S')+1));
            System.out.println(id);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("✅User deleted successfully");
            }else {
                System.out.println("❌User deletion unsuccessful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
