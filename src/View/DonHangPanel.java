package View;

import Controller.DonHangController;
import Model.DonHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DonHangPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private DonHangController donHangController;

    private final String KHACH_HANG_ID = "KH01";

    public DonHangPanel() {
        donHangController = new DonHangController();
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new Object[]{"Mã đơn", "Ngày đặt", "Tổng tiền", "Trạng thái"},
                0
        );

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnHuy = new JButton("Hủy đơn");
        btnHuy.addActionListener(e -> huyDon());

        JPanel panelBottom = new JPanel();
        panelBottom.add(btnHuy);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    void loadData() {
        tableModel.setRowCount(0);
        List<DonHang> list =
                donHangController.getDonHangByKhachHang(KHACH_HANG_ID);

        for (DonHang dh : list) {
            tableModel.addRow(new Object[]{
                    dh.getDonhangId(),
                    dh.getNgayDat(),
                    dh.getTongTien(),
                    dh.getTrangThai()
            });
        }
    }

    private void huyDon() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn đơn hàng cần hủy!");
            return;
        }

        String donHangId = tableModel.getValueAt(row, 0).toString();
        String trangThai = tableModel.getValueAt(row, 3).toString();

        if (!"CHO_XU_LY".equals(trangThai)) {
            JOptionPane.showMessageDialog(this,
                    "Chỉ hủy được đơn CHỜ XỬ LÝ!");
            return;
        }

        boolean ok = donHangController.huyDonHang(donHangId);
        JOptionPane.showMessageDialog(this,
                ok ? "Hủy đơn thành công!" : "Hủy đơn thất bại!");

        loadData();
    }
}
