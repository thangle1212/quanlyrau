package Controller;

import DAO.DonHangDAO;
import Model.DonHang;
import Model.ChiTietDonHang;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DonHangController {

    private DonHangDAO donHangDAO;

    public DonHangController() {
        donHangDAO = new DonHangDAO();
    }

    // ĐẶT ĐƠN HÀNG
    public boolean datDonHang(String khachHangId, List<ChiTietDonHang> gioHang) {
        DonHang dh = new DonHang();
        
        // Tạo mã đơn hàng ngắn hơn
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        dh.setDonhangId("DH" + timestamp.substring(2)); // Bỏ 2 số đầu (năm)
        
        // Hoặc cách khác (chỉ 10 ký tự)
        // dh.setDonhangId("DH" + System.currentTimeMillis() % 1000000000);
        
        dh.setKhachHangId(khachHangId);
        dh.setTrangThai("CHO_XU_LY");

        double tongTien = 0;
        for (ChiTietDonHang ct : gioHang) {
            tongTien += ct.getSoLuong() * ct.getDonGia();
        }
        dh.setTongTien(tongTien);

        return donHangDAO.taoDonHang(dh, gioHang);
    }

    // LẤY DANH SÁCH ĐƠN HÀNG CỦA KHÁCH
    public List<DonHang> getDonHangByKhachHang(String khachHangId) {
        return donHangDAO.getDonHangByKhachHang(khachHangId);
    }

    // HỦY ĐƠN HÀNG
    public boolean huyDonHang(String donHangId) {
        return donHangDAO.huyDonHang(donHangId);
    }
}
