/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.DBConnection;
import Models.User;
import java.sql.*;


/**
 *
 * @author Admin
 */
public class UserDAO {
    public User login(String username,String password){
        String sql="Select * From tai_khoan Where ten_dang _nhap = ? AND mat_khau = ?";
        
        try(Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                    rs.getString("user_id"),
                    rs.getString("ten_dang_nhap"),
                    rs.getString("mat_khau"),
                    rs.getString("role")
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;           
    }
    public boolean checkUser(String username,String email,String sdt){
        String sql = "Select * from tai_khoan u Join khach_hang k on u.user_id = k.user_id where u.ten_dang_nhap = ? and (k.email=? or k.sdt = ?)";
        try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, sdt);

        ResultSet rs = ps.executeQuery();
        return rs.next();

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
    }
    
    public boolean updatePassword(String username, String newPassword) {
    String sql = "UPDATE tai_khoan SET mat_khau=? WHERE ten_dang_nhap=?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, newPassword);
        ps.setString(2, username);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
