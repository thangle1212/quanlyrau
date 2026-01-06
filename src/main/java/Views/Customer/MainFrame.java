package Views.Customer;

import Views.Dangnhap;
import Controllers.AuthController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;

    private HomePanel homePanel;
    private GioHangPanel gioHangPanel;
    private DonHangPanel donHangPanel;
    private ProfilePanel profilePanel;

    public MainFrame() {
        setTitle("Hệ thống bán rau");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        tabbedPane = new JTabbedPane();

        homePanel = new HomePanel();
        gioHangPanel = new GioHangPanel();
        donHangPanel = new DonHangPanel();
        profilePanel = new ProfilePanel();

        tabbedPane.addTab("🏠 Trang chủ", homePanel);
        tabbedPane.addTab("🛒 Giỏ hàng", gioHangPanel);
        tabbedPane.addTab("📦 Đơn hàng", donHangPanel);
        tabbedPane.addTab("👤 Cá nhân", profilePanel);

        // Khi chuyển tab → refresh dữ liệu
        tabbedPane.addChangeListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            if (index == 0) homePanel.refreshData();
            if (index == 1) gioHangPanel.loadData();
            if (index == 2) donHangPanel.loadData();
        });

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
                SessionManager.clearSession();
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