package View;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HomePanel homePanel;
    private GioHangPanel gioHangPanel;  // Giữ reference
    private DonHangPanel donHangPanel;

    public MainFrame() {
        setTitle("Hệ thống bán rau");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // KHỞI TẠO CÁC PANEL TRƯỚC (QUAN TRỌNG!)
        gioHangPanel = new GioHangPanel();
        homePanel = new HomePanel();
        donHangPanel = new DonHangPanel();

        initMenu();
        initMainPanel();
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Chức năng");

        JMenuItem mnHome = new JMenuItem("Trang chủ");
        JMenuItem mnCart = new JMenuItem("Giỏ hàng");
        JMenuItem mnOrder = new JMenuItem("Đơn hàng của tôi");
        JMenuItem mnLogout = new JMenuItem("Đăng xuất");

        mnHome.addActionListener(e -> showPage("HOME"));
        
        mnCart.addActionListener(e -> {
            // QUAN TRỌNG: Refresh giỏ hàng trước khi hiển thị
            if (gioHangPanel != null) {
                gioHangPanel.loadData();
            }
            showPage("GIOHANG");
        });
        
        mnOrder.addActionListener(e -> {
            if (donHangPanel != null) {
                donHangPanel.loadData();
            }
            showPage("ORDER");
        });
//        mnLogout.addActionListener(e -> logout());

        menu.add(mnHome);
        menu.add(mnCart);
        menu.add(mnOrder);
        menu.addSeparator();
        menu.add(mnLogout);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void initMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Thêm các panel đã khởi tạo
        mainPanel.add(homePanel, "HOME");
        mainPanel.add(gioHangPanel, "GIOHANG");
        mainPanel.add(donHangPanel, "ORDER");

        add(mainPanel);
    }

    private void showPage(String name) {
        cardLayout.show(mainPanel, name);
    }
}