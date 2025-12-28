package com.mycompany.quanlyrau.view;

import com.mycompany.quanlyrau.controller.AdminController;
import com.mycompany.quanlyrau.model.NhaCungCap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NhaCungCapPanel extends JPanel {

    private final AdminController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtTen, txtDiaChi, txtSdt, txtEmail;

    public NhaCungCapPanel(AdminController controller) {
        this.controller = controller;
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Mã NCC", "Tên NCC", "Địa chỉ", "SĐT", "Email"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(3, 1));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Mã NCC:"));
        txtId = new JTextField(8);
        row1.add(txtId);
        row1.add(new JLabel("Tên NCC:"));
        txtTen = new JTextField(12);
        row1.add(txtTen);
        formPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField(20);
        row2.add(txtDiaChi);
        row2.add(new JLabel("SĐT:"));
        txtSdt = new JTextField(10);
        row2.add(txtSdt);
        row2.add(new JLabel("Email:"));
        txtEmail = new JTextField(15);
        row2.add(txtEmail);
        formPanel.add(row2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLamMoi = new JButton("Làm mới");
        row3.add(btnThem);
        row3.add(btnSua);
        row3.add(btnXoa);
        row3.add(btnLamMoi);
        formPanel.add(row3);

        add(formPanel, BorderLayout.SOUTH);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCungCap n = getFromForm();
                if (n.getNccId() == null || n.getNccId().isEmpty()) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Mã NCC không được để trống");
                    return;
                }
                if (controller.isNhaCungCapIdExists(n.getNccId())) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Mã nhà cung cấp đã tồn tại, vui lòng nhập mã khác");
                    return;
                }
                if (controller.addNhaCungCap(n)) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Thêm NCC thành công");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Thêm NCC thất bại");
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCungCap n = getFromForm();
                if (controller.updateNhaCungCap(n)) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Sửa NCC thành công");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Sửa NCC thất bại");
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Nhập mã NCC để xóa");
                    return;
                }
                if (controller.deleteNhaCungCap(id)) {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Xóa NCC thành công");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(NhaCungCapPanel.this, "Xóa NCC thất bại");
                }
            }
        });

        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtTen.setText(tableModel.getValueAt(row, 1).toString());
                txtDiaChi.setText(tableModel.getValueAt(row, 2).toString());
                txtSdt.setText(tableModel.getValueAt(row, 3).toString());
                txtEmail.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
    }

    private NhaCungCap getFromForm() {
        String id = txtId.getText().trim();
        String ten = txtTen.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String sdt = txtSdt.getText().trim();
        String email = txtEmail.getText().trim();
        return new NhaCungCap(id, ten, diaChi, sdt, email);
    }

    private void loadData() {
        List<NhaCungCap> list = controller.getAllNhaCungCap();
        tableModel.setRowCount(0);
        for (NhaCungCap n : list) {
            tableModel.addRow(new Object[]{
                    n.getNccId(), n.getTenNcc(), n.getDiaChi(), n.getSdt(), n.getEmail()
            });
        }
    }
}
