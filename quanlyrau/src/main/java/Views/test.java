/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.AuthController;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
        Dangnhap view = new Dangnhap();
        AuthController controller = new AuthController();

        controller.login(view);
    }
}
