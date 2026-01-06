package Views.Customer;

import DAO.RauDAO;
import Models.Rau;
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
        List<Rau> list = rauDAO.findAll();
        for (Rau r : list) {
            model.addRow(new Object[]{
                    r.getRauId(),
                    r.getTenRau(),
                    r.getGia(),
                    r.getSoLuong()
            });
        }
    }

    private void xemChiTiet() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String rauId = model.getValueAt(row, 0).toString();
        Rau rau = rauDAO.selectById(rauId);
        if (rau == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong hệ thống!");
            return;
        }

        new ChiTietRauDialog(rau);
        // Dialog là modal, đóng xong thì reload để thấy tồn kho đã giảm
        loadData();
    }
}
