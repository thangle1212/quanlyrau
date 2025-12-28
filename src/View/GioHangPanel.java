package View;

import Controller.DonHangController;
import Model.ChiTietDonHang;
import Model.GioHangManager;
import Model.Rau;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GioHangPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private DonHangController donHangController = new DonHangController();

    // giả lập khách đăng nhập
    private final String KHACH_HANG_ID = "KH01";

    public GioHangPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    // thêm sản phẩm từ DanhSachRauPanel
    public static void themSanPham(Rau rau, int soLuong) {
        // Sử dụng GioHangManager để quản lý
        GioHangManager.themSanPham(rau, soLuong);
        System.out.println("Đã thêm vào giỏ: " + rau.getTenRau() + " x" + soLuong);
        System.out.println("Số lượng trong giỏ: " + GioHangManager.getGioHang().size());
    }

    private void initUI() {
        tableModel = new DefaultTableModel(
                new Object[]{"Mã rau", "Tên rau", "Số lượng", "Đơn giá", "Thành tiền"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa
            }
        };
        
        table = new JTable(tableModel);
        
        // Lấy tên rau từ database để hiển thị
        Controller.RauController rauController = new Controller.RauController();

        JButton btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(e -> xoa());

        JButton btnDat = new JButton("Đặt đơn");
        btnDat.addActionListener(e -> datDon());
        
        JButton btnRefresh = new JButton("Làm mới");
        btnRefresh.addActionListener(e -> loadData());

        JPanel bottom = new JPanel();
        bottom.add(btnXoa);
        bottom.add(btnDat);
        bottom.add(btnRefresh);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    void loadData() {
        tableModel.setRowCount(0);
        List<ChiTietDonHang> gioHang = GioHangManager.getGioHang();
        
        if (gioHang.isEmpty()) {
            tableModel.addRow(new Object[]{"", "Giỏ hàng trống", "", "", ""});
            return;
        }
        
        Controller.RauController rauController = new Controller.RauController();
        double tongTien = 0;
        
        for (ChiTietDonHang ct : gioHang) {
            // Lấy thông tin rau để hiển thị tên
            Model.Rau rau = rauController.getRauid(ct.getRauId());
            String tenRau = (rau != null) ? rau.getTenRau() : ct.getRauId();
            double thanhTien = ct.getSoLuong() * ct.getDonGia();
            tongTien += thanhTien;
            
            tableModel.addRow(new Object[]{
                    ct.getRauId(),
                    tenRau,
                    ct.getSoLuong(),
                    String.format("%,.0f VND", ct.getDonGia()),
                    String.format("%,.0f VND", thanhTien)
            });
        }
        
        // Thêm hàng tổng cộng
        tableModel.addRow(new Object[]{
            "", "TỔNG CỘNG:", "", "",
            String.format("%,.0f VND", tongTien)
        });
    }

    private void xoa() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
            return;
        }
        
        List<ChiTietDonHang> gioHang = GioHangManager.getGioHang();
        if (row < gioHang.size()) {
            String tenRau = tableModel.getValueAt(row, 1).toString();
            gioHang.remove(row);
            loadData();
            JOptionPane.showMessageDialog(this, "Đã xóa " + tenRau + " khỏi giỏ hàng!");
        }
    }

    private void datDon() {
        List<ChiTietDonHang> gioHang = GioHangManager.getGioHang();
        
        if (gioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Xác nhận đặt đơn hàng với " + gioHang.size() + " sản phẩm?",
                "Xác nhận đặt hàng",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Tạo mã đơn hàng ngắn hơn để tránh lỗi database
        // Sửa trong DonHangController đã sửa
        
        boolean ok = donHangController.datDonHang(KHACH_HANG_ID, gioHang);
        
        if (ok) {
            JOptionPane.showMessageDialog(this, "Đặt hàng thành công!");
            GioHangManager.clear();
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Đặt hàng thất bại! Vui lòng thử lại.");
        }
    }
}