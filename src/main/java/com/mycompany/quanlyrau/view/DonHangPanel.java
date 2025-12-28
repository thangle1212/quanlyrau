package com.mycompany.quanlyrau.view;

import com.mycompany.quanlyrau.controller.AdminController;
import com.mycompany.quanlyrau.model.DonHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DonHangPanel extends JPanel {

    private final AdminController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboTrangThai;

    public DonHangPanel(AdminController controller) {
        this.controller = controller;
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Mã đơn", "Mã KH", "Ngày đặt", "Tổng tiền", "Trạng thái"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefresh = new JButton("Làm mới");
        bottom.add(btnRefresh);

        bottom.add(new JLabel("Trạng thái mới:"));
        cboTrangThai = new JComboBox<>(new String[]{"CHO_XU_LY", "VAN_CHUYEN", "DA_GIAO", "HUY"});
        bottom.add(cboTrangThai);

        JButton btnXacNhan = new JButton("Cập nhật trạng thái");
        bottom.add(btnXacNhan);
        add(bottom, BorderLayout.SOUTH);

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(DonHangPanel.this, "Chọn một đơn hàng");
                    return;
                }
                String id = tableModel.getValueAt(row, 0).toString();
                String trangThaiMoi = (String) cboTrangThai.getSelectedItem();
                if (controller.capNhatTrangThaiDonHang(id, trangThaiMoi)) {
                    JOptionPane.showMessageDialog(DonHangPanel.this, "Cập nhật trạng thái thành công");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(DonHangPanel.this, "Cập nhật trạng thái thất bại");
                }
            }
        });
    }

    private void loadData() {
        List<DonHang> list = controller.getAllDonHang();
        tableModel.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (DonHang d : list) {
            String ngay = d.getNgayDat() == null ? "" : d.getNgayDat().format(fmt);
            tableModel.addRow(new Object[]{
                    d.getDonHangId(), d.getKhachHangId(), ngay, d.getTongTien(), d.getTrangThai()
            });
        }
    }
}
