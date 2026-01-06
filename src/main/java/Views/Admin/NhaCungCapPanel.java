package Views.Admin;

import Models.NhaCungCap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class NhaCungCapPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtTen, txtDiaChi, txtSdt, txtEmail;
    private JTextField txtTimKiem;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    public NhaCungCapPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel formPanel = new JPanel(new GridLayout(4, 1));

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

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Tìm (mã hoặc tên):"));
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

        // Đưa form + nút lên trên trong panel có tiêu đề
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));
        infoPanel.add(formPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);

        // Bảng hiển thị xuống dưới
        tableModel = new DefaultTableModel(new Object[]{"Mã NCC", "Tên NCC", "Địa chỉ", "SĐT", "Email"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public NhaCungCap getNhaCungCapFromForm() {
        String id = txtId.getText().trim();
        String ten = txtTen.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String sdt = txtSdt.getText().trim();
        String email = txtEmail.getText().trim();
        return new NhaCungCap(id, ten, diaChi, sdt, email);
    }

    public void setNhaCungCapData(List<NhaCungCap> list) {
        tableModel.setRowCount(0);
        if (list == null) return;
        for (NhaCungCap n : list) {
            tableModel.addRow(new Object[]{
                    n.getNccId(), n.getTenNcc(), n.getDiaChi(), n.getSdt(), n.getEmail()
            });
        }
    }

    public void clearForm() {
        txtId.setText("");
        txtTen.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtEmail.setText("");
    }

    public String getSelectedNccId() {
        int row = table.getSelectedRow();
        if (row < 0) return null;
        return tableModel.getValueAt(row, 0).toString();
    }

    public void setFormFromTableRow(int row) {
        if (row < 0 || row >= tableModel.getRowCount()) return;
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtTen.setText(tableModel.getValueAt(row, 1).toString());
        txtDiaChi.setText(tableModel.getValueAt(row, 2).toString());
        txtSdt.setText(tableModel.getValueAt(row, 3).toString());
        txtEmail.setText(tableModel.getValueAt(row, 4).toString());
    }

    public String getSearchKeyword() {
        return txtTimKiem.getText().trim();
    }

    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }

    public ListSelectionModel getTableSelectionModel() { return table.getSelectionModel(); }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
