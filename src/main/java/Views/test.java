/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.AuthController;
import DAO.UserDAO;
import Models.User;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        // test login
        User u = dao.login("admin", "999999");
        System.out.println(u != null ? "LOGIN OK" : "LOGIN FAIL");

        // test check user
        boolean ok = dao.checkUser("abc", "abc@gmail.com", "abc@gmail.com");
        System.out.println(ok ? "CHECK OK" : "CHECK FAIL");

        // test update password
        boolean up = dao.updatePassword("admin", "999999");
        System.out.println(up ? "UPDATE OK" : "UPDATE FAIL");
    }
}
