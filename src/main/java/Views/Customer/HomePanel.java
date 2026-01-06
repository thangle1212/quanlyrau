/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Views.Customer;

import Controllers.RauController;
import Models.Rau;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HomePanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private RauController rauController = new RauController();

    public HomePanel() {
        setLayout(new BorderLayout());

        // Tìm kiếm
        JPanel top = new JPanel();
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm");

        btnSearch.addActionListener(e -> loadData());

        top.add(new JLabel("Tìm rau:"));
        top.add(txtSearch);
        top.add(btnSearch);

        // Bảng rau
        model = new DefaultTableModel(
                new Object[]{"Mã", "Tên rau", "Giá", "Số lượng", "Đơn vị"},
                0
        );
        table = new JTable(model);

        JButton btnDetail = new JButton("Xem chi tiết");
        btnDetail.addActionListener(e -> xemChiTiet());

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnDetail, BorderLayout.SOUTH);

        loadData();
    }

    public void refreshData() {
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Rau> list = rauController.getDanhSachRau();

        String keyword = txtSearch.getText().toLowerCase();

        for (Rau r : list) {
            if (r.getTenRau().toLowerCase().contains(keyword)) {
                model.addRow(new Object[]{
                        r.getRauId(),
                        r.getTenRau(),
                        r.getGia(),
                        r.getSoLuong(),
                        r.getDonViTinh()
                });
            }
        }
    }

    private void xemChiTiet() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String rauId = model.getValueAt(row, 0).toString();
        Rau rau = rauController.getRauid(rauId);

        // Dialog là modal (chặn), đóng xong thì refresh để cập nhật tồn kho
        new ChiTietRauDialog(rau);
        loadData();
    }
}

