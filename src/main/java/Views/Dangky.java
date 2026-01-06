package Views;

import java.awt.*;
import javax.swing.*;

public class Dangky extends javax.swing.JFrame {

    public JTextField txtUsername = new JTextField();
    public JPasswordField txtPassword = new JPasswordField();
    public JPasswordField txtPasswordConfirm = new JPasswordField();
    public JTextField txtHoTen = new JTextField();
    public JTextField txtNgaySinh = new JTextField();
    public JTextField txtEmail = new JTextField();
    public JTextField txtSDT = new JTextField();
    public JTextField txtDiaChi = new JTextField();

    public JButton btnRegister = new JButton("ĐĂNG KÝ");
    public JButton btnBack = new JButton("QUAY LẠI");
    public JLabel lblMessage = new JLabel("", JLabel.CENTER);

    public Dangky() {
        initComponents();
        setTitle("Đăng Ký Hệ Thống Quản Lý Rau");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(createTitle(), BorderLayout.NORTH);
        add(createForm(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JLabel createTitle() {
        JLabel lbl = new JLabel("ĐĂNG KÝ TÀI KHOẢN", JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        return lbl;
    }

    private JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        panel.add(new JLabel("Tên đăng nhập"));
        panel.add(txtUsername);

        panel.add(new JLabel("Mật khẩu"));
        panel.add(txtPassword);

        panel.add(new JLabel("Xác nhận mật khẩu"));
        panel.add(txtPasswordConfirm);

        panel.add(new JLabel("Họ tên"));
        panel.add(txtHoTen);

        panel.add(new JLabel("Ngày sinh (dd/MM/yyyy)"));
        panel.add(txtNgaySinh);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("Số điện thoại"));
        panel.add(txtSDT);

        panel.add(new JLabel("Địa chỉ"));
        panel.add(txtDiaChi);

        panel.add(lblMessage);
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(btnRegister);
        panel.add(btnBack);
        return panel;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pack();
    }
}
