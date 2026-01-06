/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DAO.UserDAO;
import Models.User;
import Views.*;
import javax.swing.JOptionPane;
import Controllers.AdminController;
import Views.Admin.AdminMainFrame;

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

            if ("ADMIN".equals(user.getRole())) {
                 AdminController adminController = new AdminController();
                AdminMainFrame adminFrame = new AdminMainFrame(adminController);
                adminFrame.setVisible(true);

            } else if ("CUSTOMER".equals(user.getRole())) {
                // Initialize the session with the logged-in user
                Views.Customer.SessionManager.setCurrentUser(user.getUserID());

                Views.Customer.MainFrame customerFrame = new Views.Customer.MainFrame();
                customerFrame.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Role không hợp lệ: " + user.getRole());
            }
        }
        });
        
        v.btnForget.addActionListener(e -> {
            Quenmatkhau qmk = new Quenmatkhau();
            AuthController auth = new AuthController();
            auth.Quenmatkhau(qmk);
            qmk.setVisible(true);
            v.dispose();
        });

        v.btnRegister.addActionListener(e -> {
            Dangky dk = new Dangky();
            AuthController auth = new AuthController();
            auth.register(dk);
            dk.setVisible(true);
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
            Doimatkhau dmk = new Doimatkhau(username);
            AuthController auth = new AuthController();
            auth.Doimatkhau(dmk);
            dmk.setVisible(true);
v.dispose();
        } else {
            JOptionPane.showMessageDialog(v, "Thông tin không đúng");
        }
    });
        v.btnBack.addActionListener(e ->{
            Dangnhap dn = new Dangnhap();
            AuthController auth = new AuthController();
            auth.login(dn);
            dn.setVisible(true);
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
        Dangnhap dn = new Dangnhap();
        AuthController auth = new AuthController();
        auth.login(dn);
        dn.setVisible(true);
        v.dispose();

        });
        v.btnBack.addActionListener(e ->{
            Dangnhap dn = new Dangnhap();
            AuthController auth = new AuthController();
            auth.login(dn);
            dn.setVisible(true);
            v.dispose();
        });
    }

    public void register(Dangky v) {
        v.btnRegister.addActionListener(e -> {
            String username = v.txtUsername.getText();
            String password = new String(v.txtPassword.getPassword());
            String passwordConfirm = new String(v.txtPasswordConfirm.getPassword());
            String hoTen = v.txtHoTen.getText();
            String ngaySinh = v.txtNgaySinh.getText();
            String email = v.txtEmail.getText();
            String sdt = v.txtSDT.getText();
            String diaChi = v.txtDiaChi.getText();

            // Validate inputs
            if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || 
                hoTen.isEmpty() || ngaySinh.isEmpty() || email.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
                v.lblMessage.setText("Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (!password.equals(passwordConfirm)) {
                v.lblMessage.setText("Mật khẩu không khớp");
                return;
            }

            if (dao.checkUsernameExists(username)) {
                v.lblMessage.setText("Tên đăng nhập đã tồn tại");
                return;
            }

            if (dao.register(username, password, hoTen, ngaySinh, email, sdt, diaChi)) {
                JOptionPane.showMessageDialog(v, "Đăng ký thành công. Vui lòng đăng nhập!");
                Dangnhap dn = new Dangnhap();
                AuthController auth = new AuthController();
                auth.login(dn);
                dn.setVisible(true);
                v.dispose();
            } else {
                v.lblMessage.setText("Đăng ký thất bại. Vui lòng thử lại!");
            }
        });

        v.btnBack.addActionListener(e -> {
            Dangnhap dn = new Dangnhap();
            AuthController auth = new AuthController();
            auth.login(dn);
            dn.setVisible(true);
            v.dispose();
        });
    }
    
    
}
