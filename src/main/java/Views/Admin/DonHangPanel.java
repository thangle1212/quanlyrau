package Views.Admin;

import Models.DonHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DonHangPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboTrangThai;
    private JButton btnXacNhan;

    public DonHangPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel nút ở trên (căn giữa)
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.add(new JLabel("Trạng thái mới:"));
        cboTrangThai = new JComboBox<>(new String[]{"CHO_XU_LY", "VAN_CHUYEN", "DA_GIAO", "HUY"});
        top.add(cboTrangThai);

        btnXacNhan = new JButton("Cập nhật trạng thái");
        top.add(btnXacNhan);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Quản lý đơn hàng"));
        infoPanel.add(top, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);

        // Bảng ở dưới
        tableModel = new DefaultTableModel(new Object[]{"Mã đơn", "Mã KH", "Ngày đặt", "Tổng tiền", "Trạng thái"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setDonHangData(List<DonHang> list) {
        tableModel.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (list == null) return;
        for (DonHang d : list) {
            String ngay = d.getNgayDat() == null ? "" : d.getNgayDat().format(fmt);
            tableModel.addRow(new Object[]{
                    d.getDonHangId(), d.getKhachHangId(), ngay, d.getTongTien(), d.getTrangThai()
            });
        }
    }

    public String getSelectedDonHangId() {
        int row = table.getSelectedRow();
        if (row < 0) return null;
        return tableModel.getValueAt(row, 0).toString();
    }

    public String getTrangThaiMoi() {
        Object selected = cboTrangThai.getSelectedItem();
        return selected == null ? null : selected.toString();
    }
    public void addCapNhatTrangThaiListener(ActionListener l) { btnXacNhan.addActionListener(l); }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
