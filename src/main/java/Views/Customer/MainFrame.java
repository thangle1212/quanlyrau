package Views.Customer;

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
    }
}