package View;

import Model.Rau;
import javax.swing.*;
import java.awt.*;

public class ChiTietRauDialog extends JDialog {

    private Rau rau;
    private JSpinner spnSoLuong;

    public ChiTietRauDialog(Rau rau) {
        this.rau = rau;
        initUI();
        setModal(true);
        setVisible(true);
    }

    private void initUI() {
        setTitle("Chi tiết rau: " + rau.getTenRau());
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Sử dụng JPanel chính để chứa border
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel thông tin chi tiết
        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        
        infoPanel.add(new JLabel("Mã rau:"));
        infoPanel.add(new JLabel(rau.getRauID()));
        
        infoPanel.add(new JLabel("Tên rau:"));
        infoPanel.add(new JLabel(rau.getTenRau()));
        
        infoPanel.add(new JLabel("Giá bán:"));
        infoPanel.add(new JLabel(String.format("%,.0f VND", rau.getGia())));
        
        infoPanel.add(new JLabel("Đơn vị tính:"));
        infoPanel.add(new JLabel(rau.getDonViTinh()));
        
        infoPanel.add(new JLabel("Số lượng tồn:"));
        infoPanel.add(new JLabel(String.valueOf(rau.getSoLuong())));

        // Panel chọn số lượng
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        quantityPanel.setBorder(BorderFactory.createTitledBorder("Chọn số lượng"));
        
        JLabel lblSoLuong = new JLabel("Số lượng mua:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(
            1, 1, rau.getSoLuong(), 1
        );
        spnSoLuong = new JSpinner(spinnerModel);
        JLabel lblDonVi = new JLabel(rau.getDonViTinh());
        
        quantityPanel.add(lblSoLuong);
        quantityPanel.add(spnSoLuong);
        quantityPanel.add(lblDonVi);

        // Panel nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton btnThem = new JButton("Thêm vào giỏ hàng");
        btnThem.setBackground(new Color(70, 130, 180));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(e -> themVaoGioHang());
        
        JButton btnHuy = new JButton("Hủy bỏ");
        btnHuy.addActionListener(e -> dispose());
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnHuy);

        // Thêm các panel vào main panel
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(quantityPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm mainPanel vào dialog
        add(mainPanel);
        
        // Đặt phím tắt
        getRootPane().setDefaultButton(btnThem);
    }

    private void themVaoGioHang() {
        int soLuong = (int) spnSoLuong.getValue();
        
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this,
                "Số lượng phải lớn hơn 0!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (soLuong > rau.getSoLuong()) {
            JOptionPane.showMessageDialog(this,
                "Số lượng vượt quá tồn kho!\nChỉ còn " + rau.getSoLuong() + " " + rau.getDonViTinh(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Thêm vào giỏ hàng
        Model.GioHangManager.themSanPham(rau, soLuong);
        
        // Hiển thị thông báo thành công
        JOptionPane.showMessageDialog(this,
            "✅ Đã thêm vào giỏ hàng:\n" +
            "• " + rau.getTenRau() + "\n" +
            "• Số lượng: " + soLuong + " " + rau.getDonViTinh() + "\n" +
            "• Thành tiền: " + String.format("%,.0f VND", rau.getGia() * soLuong),
            "Thành công",
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
}