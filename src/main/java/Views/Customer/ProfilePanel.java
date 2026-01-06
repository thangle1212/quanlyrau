package Views.Customer;

import DAO.KhachHangDAO;
import Models.KhachHang;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;
    private JTextField txtDiaChi;
    private JTextField txtSdt;
    private JTextField txtEmail;
    private JButton btnSua;
    private JButton btnLuu;
    private JButton btnHuy;

    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private String currentUserId; // This should be passed from the login session
    private KhachHang currentKhachHang;

    public ProfilePanel() {
        // Get the current user ID from session manager
        currentUserId = SessionManager.getCurrentUserId();
        initUI();
        loadData();
        setEditMode(false);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create input fields
        txtHoTen = new JTextField(20);
        txtNgaySinh = new JTextField(20);
        txtDiaChi = new JTextField(20);
        txtSdt = new JTextField(20);
        txtEmail = new JTextField(20);

        // Add labels and fields to form
        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtHoTen, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtNgaySinh, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtDiaChi, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtSdt, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtEmail, gbc);
        row++;

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSua = new JButton("Sửa thông tin");
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");

        buttonPanel.add(btnSua);
        buttonPanel.add(btnLuu);
        buttonPanel.add(btnHuy);

        // Add components to main panel
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add event listeners
        btnSua.addActionListener(e -> setEditMode(true));
        btnLuu.addActionListener(e -> luuThongTin());
        btnHuy.addActionListener(e -> {
            loadData();
            setEditMode(false);
        });
    }

    private void loadData() {
        // Get the customer based on the logged-in user ID
        currentKhachHang = khachHangDAO.selectByUserId(currentUserId);

        if (currentKhachHang != null) {
            txtHoTen.setText(currentKhachHang.getHoTen());
            txtNgaySinh.setText(currentKhachHang.getNgaySinh());
            txtDiaChi.setText(currentKhachHang.getDiaChi());
            txtSdt.setText(currentKhachHang.getSdt());
            txtEmail.setText(currentKhachHang.getEmail());
        }
    }

    private void luuThongTin() {
        if (currentKhachHang == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!");
            return;
        }

        // Update the customer object with new values
        currentKhachHang.setHoTen(txtHoTen.getText());
        currentKhachHang.setNgaySinh(txtNgaySinh.getText());
        currentKhachHang.setDiaChi(txtDiaChi.getText());
        currentKhachHang.setSdt(txtSdt.getText());
        currentKhachHang.setEmail(txtEmail.getText());

        // Save to database
        boolean success = khachHangDAO.update(currentKhachHang);

        if (success) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
            setEditMode(false);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }

    private void setEditMode(boolean editMode) {
        txtHoTen.setEditable(editMode);
        txtNgaySinh.setEditable(editMode);
        txtDiaChi.setEditable(editMode);
        txtSdt.setEditable(editMode);
        txtEmail.setEditable(editMode);

        btnSua.setVisible(!editMode);
        btnLuu.setVisible(editMode);
        btnHuy.setVisible(editMode);
    }
}