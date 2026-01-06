/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import Database.DBConnection;
import java.sql.Connection;
/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Kết nối database thành công");
        } else {
            System.out.println("❌ Kết nối database thất bại");
        }
    }
}
