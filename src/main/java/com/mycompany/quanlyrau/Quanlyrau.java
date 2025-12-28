/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlyrau;

import com.mycompany.quanlyrau.controller.AdminController;
import com.mycompany.quanlyrau.view.AdminMainFrame;

/**
 *
 * @author Admin
 */
public class Quanlyrau {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AdminController controller = new AdminController();
            AdminMainFrame frame = new AdminMainFrame(controller);
            frame.setVisible(true);
        });
    }
}
