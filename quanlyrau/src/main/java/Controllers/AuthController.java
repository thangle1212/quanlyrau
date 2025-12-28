/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DAO.UserDAO;
import Models.User;
import Views.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class AuthController {
    private UserDAO dao = new UserDAO();
    
    
    public void login(Dangnhap v){
        v.btnLogin.addActionListener(e->{
            String u = v.txtUsername.getText();
            String p = new String(v.txtPassword.getPassword());
            if (u.isEmpty() || p.isEmpty()) {
                v.lblMessage.setText("Vui lòng nhập đủ thông tin");
                return;
            }
            User user = dao.login(u, p);
            if (user == null) {
                v.lblMessage.setText("Sai tài khoản hoặc mật khẩu");
            } else {
                v.dispose();
                if (user.getRole().equals("ADMIN"))
                    System.out.println("TRANG ADMIN");
                else
                    System.out.println("TRANG KHACH HANG");
            }
        });
        
        v.btnForget.addActionListener(e -> {
            new Quenmatkhau();
            v.dispose();
        });
            }
        
    public void Quenmatkhau(Quenmatkhau v) {
        v.btnConfirm.addActionListener(e -> {

        String username = v.txtUsername.getText();
        String input = v.txtEmailorSdt.getText();

        if (username.isEmpty() || input.isEmpty()) {
            JOptionPane.showMessageDialog(v, "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (dao.checkUser(username, input, input)) {
            new Doimatkhau(username); // ✅ TRUYỀN USERNAME
            v.dispose();
        } else {
            JOptionPane.showMessageDialog(v, "Thông tin không đúng");
        }
    });
        v.btnBack.addActionListener(e ->{
            new Dangnhap();
            v.dispose();
        });
    }
    
    public void Doimatkhau(Doimatkhau v){
        v.btnConfirm.addActionListener(e->{
            String p1 = new String(v.txtPassword1.getPassword());
            String p2 = new String(v.txtPassword2.getPassword());
            if (p1.isEmpty() || p2.isEmpty()) {
            JOptionPane.showMessageDialog(v, "Không được để trống");
            return;
        }

        if (!p1.equals(p2)) {
            JOptionPane.showMessageDialog(v, "Mật khẩu không khớp");
            return;
        }

        dao.updatePassword(v.getUsername(), p1);

        JOptionPane.showMessageDialog(v, "Đổi mật khẩu thành công");
        new Dangnhap();
        v.dispose();
        });
        v.btnBack.addActionListener(e ->{
            new Dangnhap();
            v.dispose();
        });
    }
    
    
}
