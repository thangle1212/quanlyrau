package Views.Admin;

import Controllers.AdminController;
import Views.Dangnhap;
import Controllers.AuthController;
import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {

    private final AdminController controller;

    public AdminMainFrame(AdminController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Quản lý bán rau - ADMIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        RauPanel rauPanel = new RauPanel();
        controller.bindRauPanel(rauPanel);

        NhaCungCapPanel nhaCungCapPanel = new NhaCungCapPanel();
        controller.bindNhaCungCapPanel(nhaCungCapPanel);

        DonHangPanel donHangPanel = new DonHangPanel();
        controller.bindDonHangPanel(donHangPanel);

        ThongKePanel thongKePanel = new ThongKePanel();
        controller.bindThongKePanel(thongKePanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("🏠 Quản lý rau", rauPanel);
        tabbedPane.addTab("🏭 Quản lý NCC", nhaCungCapPanel);
        tabbedPane.addTab("📦 Quản lý đơn hàng", donHangPanel);
        tabbedPane.addTab("👤 Quản lý tài khoản", new AccountPanel(controller));
        tabbedPane.addTab("📊 Thống kê", thongKePanel);

        add(tabbedPane, BorderLayout.CENTER);
        add(createLogoutPanel(), BorderLayout.SOUTH);
    }

    private JPanel createLogoutPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnLogout = new JButton("Đăng xuất");
        
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn đăng xuất?", 
                "Xác nhận đăng xuất", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                Dangnhap dn = new Dangnhap();
                AuthController auth = new AuthController();
                auth.login(dn);
                dn.setVisible(true);
                this.dispose();
            }
        });
        
        panel.add(btnLogout);
        return panel;
    }
}
