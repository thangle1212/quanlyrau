/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



import Controllers.AuthController;
import Views.Dangnhap;
import Controllers.*;
import Views.*;

/**
 *
 * @author Admin
 */
public class Quanlyrau {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Dangnhap loginView = new Dangnhap();
            AuthController authController = new AuthController();
            authController.login(loginView);
            loginView.setVisible(true);
        });
    }
}
