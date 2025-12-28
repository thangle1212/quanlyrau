package DAO;

import Model.ChiTietDonHang;
import Model.DonHang;
import DAO.DBconnect;
import Model.DonHang;
import Model.ChiTietDonHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class DonHangDAO {

    // Tạo đơn hàng mới
    public boolean taoDonHang(DonHang donHang, List<ChiTietDonHang> chiTietList) {

        String sqlDonHang =
                "INSERT INTO don_hang(don_hang_id, khach_hang_id, tong_tien, trang_thai) VALUES (?,?,?,?)";

        String sqlChiTiet =
                "INSERT INTO chi_tiet_don_hang(don_hang_id, rau_id, so_luong, don_gia) VALUES (?,?,?,?)";

        Connection conn = null;

        try {
            conn = DBconnect.getConnection();
            conn.setAutoCommit(false); // TRANSACTION

            // 1. Insert đơn hàng
            PreparedStatement psDonHang = conn.prepareStatement(sqlDonHang);
            psDonHang.setString(1, donHang.getDonhangId());
            psDonHang.setString(2, donHang.getKhachHangId());
            psDonHang.setDouble(3, donHang.getTongTien());
            psDonHang.setString(4, donHang.getTrangThai());
            psDonHang.executeUpdate();

            // 2. Insert chi tiết đơn hàng
            PreparedStatement psChiTiet = conn.prepareStatement(sqlChiTiet);
            for (ChiTietDonHang ct : chiTietList) {
                psChiTiet.setString(1, donHang.getDonhangId());
                psChiTiet.setString(2, ct.getRauId());
                psChiTiet.setInt(3, ct.getSoLuong());
                psChiTiet.setDouble(4, ct.getDonGia());
                psChiTiet.addBatch();
            }
            psChiTiet.executeBatch();

            conn.commit(); // OK
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Lấy danh sách đơn hàng theo khách hàng
    public List<DonHang> getDonHangByKhachHang(String khachHangId) {

        List<DonHang> list = new ArrayList<>();
        String sql =
                "SELECT * FROM don_hang WHERE khach_hang_id = ? ORDER BY ngay_dat DESC";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, khachHangId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DonHang dh = new DonHang();
                dh.setDonhangId(rs.getString("don_hang_id"));
                dh.setKhachHangId(rs.getString("khach_hang_id"));
                dh.setNgayDat(rs.getTimestamp("ngay_dat"));
                dh.setTongTien(rs.getDouble("tong_tien"));
                dh.setTrangThai(rs.getString("trang_thai"));

                list.add(dh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hủy đơn hàng
    public boolean huyDonHang(String donHangId) {

        String sql =
                "UPDATE don_hang SET trang_thai = 'HUY' " +
                "WHERE don_hang_id = ? AND trang_thai = 'CHO_XU_LY'";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, donHangId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
