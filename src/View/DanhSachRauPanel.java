package View;

import DAO.RauDAO;
import Model.Rau;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DanhSachRauPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private RauDAO rauDAO = new RauDAO();

    public DanhSachRauPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        model = new DefaultTableModel(
                new Object[]{"Mã", "Tên rau", "Giá", "Số lượng"}, 0
        );
        table = new JTable(model);

        JButton btnChiTiet = new JButton("Xem chi tiết");
        btnChiTiet.addActionListener(e -> xemChiTiet());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnChiTiet, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Rau> list = rauDAO.getAllRau();
        for (Rau r : list) {
            model.addRow(new Object[]{
                    r.getRauID(),
                    r.getTenRau(),
                    r.getGia(),
                    r.getSoLuong()
            });
        }
    }

    private void xemChiTiet() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        Rau rau = new Rau();
        rau.setRauID(model.getValueAt(row, 0).toString());
        rau.setTenRau(model.getValueAt(row, 1).toString());
        rau.setGia(Double.parseDouble(model.getValueAt(row, 2).toString()));
        rau.setSoLuong(Integer.parseInt(model.getValueAt(row, 3).toString()));

        new ChiTietRauDialog(rau);
    }
}
