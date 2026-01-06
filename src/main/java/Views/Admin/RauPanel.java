package Views.Admin;

import Models.NhaCungCap;
import Models.Rau;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;


public class RauPanel extends JPanel {
    
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtTen, txtGia, txtSoLuong, txtDonViTinh;
    private JComboBox<NccItem> cboNcc;
    private JTextField txtTimKiem;

    public RauPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Form + button (ở trên)
        JPanel formPanel = new JPanel(new GridLayout(4, 1));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Mã rau:"));
        txtId = new JTextField(8);
        row1.add(txtId);
        row1.add(new JLabel("Tên rau:"));
        txtTen = new JTextField(12);
        row1.add(txtTen);
        row1.add(new JLabel("Giá:"));
        txtGia = new JTextField(8);
        row1.add(txtGia);
        formPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField(5);
        row2.add(txtSoLuong);
        row2.add(new JLabel("ĐVT:"));
        txtDonViTinh = new JTextField(8);
        row2.add(txtDonViTinh);
        row2.add(new JLabel("Nhà cung cấp:"));
        cboNcc = new JComboBox<>();
        row2.add(cboNcc);
        formPanel.add(row2);

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Tìm rau (nhập mã rau hoặc tên):"));
        txtTimKiem = new JTextField(20);
        searchRow.add(txtTimKiem);
        btnTimKiem = new JButton("Tìm");
        searchRow.add(btnTimKiem);
        formPanel.add(searchRow);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        row3.add(btnThem);
        row3.add(btnSua);
        row3.add(btnXoa);
        row3.add(btnLamMoi);
        formPanel.add(row3);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin rau"));
        infoPanel.add(formPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);

        // Bảng (ở dưới)
        tableModel = new DefaultTableModel(new Object[]{"Mã rau", "Tên rau", "Giá", "Số lượng", "ĐVT", "Trạng thái", "Mã NCC"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public Rau getRauFromForm() {
        String id = txtId.getText().trim();
        String ten = txtTen.getText().trim();
        double gia = Double.parseDouble(txtGia.getText().trim());
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        String dvt = txtDonViTinh.getText().trim();
        String trangThai = null;
        NccItem selected = (NccItem) cboNcc.getSelectedItem();
        String nccId = selected == null ? null : selected.getId();
        return new Rau(id, ten, gia, soLuong, dvt, trangThai, nccId);
    }

    public void setRauData(List<Rau> list) {
        tableModel.setRowCount(0);
        for (Rau r : list) {
            tableModel.addRow(new Object[]{
                    r.getRauId(), r.getTenRau(), r.getGia(), r.getSoLuong(),
                    r.getDonViTinh(), r.getTrangThai(), r.getNccId()
            });
        }
    }

    public void setNccOptions(List<NhaCungCap> list) {
        cboNcc.removeAllItems();
        cboNcc.addItem(new NccItem(null, "-- Không chọn --"));
        for (NhaCungCap n : list) {
            cboNcc.addItem(new NccItem(n.getNccId(), n.getTenNcc()));
        }
        cboNcc.setSelectedIndex(0);
    }

    public void clearForm() {
        txtId.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtDonViTinh.setText("");
        cboNcc.setSelectedIndex(0);
    }

    public String getSelectedRauId() {
        int row = table.getSelectedRow();
        if (row < 0) return null;
        return tableModel.getValueAt(row, 0).toString();
    }

    public void setFormFromTableRow(int row) {
        if (row < 0 || row >= tableModel.getRowCount()) return;
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtTen.setText(tableModel.getValueAt(row, 1).toString());
        txtGia.setText(tableModel.getValueAt(row, 2).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 3).toString());
        txtDonViTinh.setText(tableModel.getValueAt(row, 4).toString());
        Object ncc = tableModel.getValueAt(row, 6);
        String nccId = ncc == null ? null : ncc.toString();
        if (nccId != null) {
            for (int i = 0; i < cboNcc.getItemCount(); i++) {
                NccItem item = cboNcc.getItemAt(i);
                if (item != null && nccId.equals(item.getId())) {
                    cboNcc.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            cboNcc.setSelectedIndex(0);
        }
    }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }

    public String getSearchKeyword() {
        return txtTimKiem.getText().trim();
    }

    public ListSelectionModel getTableSelectionModel() { return table.getSelectionModel(); }

    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }

    private static class NccItem {
        private final String id;
        private final String name;

        public NccItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id == null ? name : id + " - " + name;
        }
    }

}
