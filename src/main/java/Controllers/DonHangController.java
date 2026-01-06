package Controllers;

import DAO.DonHangDAO;
import Models.ChiTietDonHang;
import Models.DonHang;
import java.util.List;
import java.time.LocalDateTime;

public class DonHangController {
    private DonHangDAO donHangDAO = new DonHangDAO();

    public boolean datDonHang(String khachHangId, List<ChiTietDonHang> chiTietDonHangs) {
        // Tạo đơn hàng mới
        String maDonHang = generateMaDonHang();
        DonHang donHang = new DonHang();
        donHang.setDonHangId(maDonHang);
        donHang.setKhachHangId(khachHangId);
        donHang.setNgayDat(LocalDateTime.now());
        
        // Tính tổng tiền
        double tongTien = 0;
        for (ChiTietDonHang ct : chiTietDonHangs) {
            tongTien += ct.getSoLuong() * ct.getDonGia();
        }
        donHang.setTongTien(tongTien);
        donHang.setTrangThai("CHO_XU_LY");

        // Thêm đơn hàng vào database
        boolean success = donHangDAO.insert(donHang, chiTietDonHangs);
        return success;
    }

    public List<DonHang> getDonHangByKhachHang(String khachHangId) {
        return donHangDAO.selectByKhachHangId(khachHangId);
    }

    public boolean huyDonHang(String donHangId) {
        DonHang donHang = donHangDAO.selectById(donHangId);
        if (donHang != null && "CHO_XU_LY".equals(donHang.getTrangThai())) {
            donHang.setTrangThai("HUY");
            return donHangDAO.update(donHang);
        }
        return false;
    }

    private String generateMaDonHang() {
        // Tạo mã đơn hàng ngẫu nhiên với độ dài ngắn hơn
        return "DH" + (System.currentTimeMillis() % 1000000);
    }
}