package DAO;

import Database.DBConnection;
import Models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DonHangDAO {

    public List<DonHang> findAll() {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM don_hang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("ngay_dat");
                LocalDateTime ngayDat = ts == null ? null : ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                DonHang d = new DonHang(
                        rs.getString("don_hang_id"),
                        rs.getString("khach_hang_id"),
                        ngayDat,
                        rs.getDouble("tong_tien"),
                        rs.getString("trang_thai")
                );
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DonHang selectById(String donHangId) {
        String sql = "SELECT * FROM don_hang WHERE don_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, donHangId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp ts = rs.getTimestamp("ngay_dat");
                    LocalDateTime ngayDat = ts == null ? null : ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return new DonHang(
                            rs.getString("don_hang_id"),
                            rs.getString("khach_hang_id"),
                            ngayDat,
                            rs.getDouble("tong_tien"),
                            rs.getString("trang_thai")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DonHang> selectByKhachHangId(String khachHangId) {
        List<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM don_hang WHERE khach_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHangId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Timestamp ts = rs.getTimestamp("ngay_dat");
                    LocalDateTime ngayDat = ts == null ? null : ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    DonHang d = new DonHang(
                            rs.getString("don_hang_id"),
                            rs.getString("khach_hang_id"),
                            ngayDat,
                            rs.getDouble("tong_tien"),
                            rs.getString("trang_thai")
                    );
                    list.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(DonHang donHang, List<ChiTietDonHang> chiTietDonHangs) {
        String sqlDonHang = "INSERT INTO don_hang(don_hang_id, khach_hang_id, ngay_dat, tong_tien, trang_thai) VALUES(?,?,?,?,?)";
        String sqlChiTiet = "INSERT INTO chi_tiet_don_hang(chi_tiet_id, don_hang_id, rau_id, so_luong, don_gia) VALUES(?,?,?,?,?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return false;
            }

            boolean originalAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Thêm đơn hàng
            try (PreparedStatement psDonHang = conn.prepareStatement(sqlDonHang)) {
                psDonHang.setString(1, donHang.getDonHangId());
                psDonHang.setString(2, donHang.getKhachHangId());
                psDonHang.setTimestamp(3, Timestamp.valueOf(donHang.getNgayDat()));
                psDonHang.setDouble(4, donHang.getTongTien());
                psDonHang.setString(5, donHang.getTrangThai());

                if (psDonHang.executeUpdate() == 0) {
                    conn.rollback();
                    return false;
                }
            }

            // Thêm chi tiết đơn hàng
            for (ChiTietDonHang ct : chiTietDonHangs) {
                try (PreparedStatement psChiTiet = conn.prepareStatement(sqlChiTiet)) {
                    psChiTiet.setString(1, generateChiTietId()); // Generate ID for detail
                    psChiTiet.setString(2, donHang.getDonHangId());
                    psChiTiet.setString(3, ct.getRauId());
                    psChiTiet.setInt(4, ct.getSoLuong());
                    psChiTiet.setDouble(5, ct.getDonGia());

                    if (psChiTiet.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }
            }

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Restore original autocommit setting
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean update(DonHang donHang) {
        String sql = "UPDATE don_hang SET khach_hang_id=?, ngay_dat=?, tong_tien=?, trang_thai=? WHERE don_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, donHang.getKhachHangId());
            ps.setTimestamp(2, Timestamp.valueOf(donHang.getNgayDat()));
            ps.setDouble(3, donHang.getTongTien());
            ps.setString(4, donHang.getTrangThai());
            ps.setString(5, donHang.getDonHangId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTrangThai(String donHangId, String trangThaiMoi) {
        String sql = "UPDATE don_hang SET trang_thai=? WHERE don_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThaiMoi);
            ps.setString(2, donHangId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countDonDaDat() {
        String sql = "SELECT COUNT(*) FROM don_hang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countDonDaHuy() {
        String sql = "SELECT COUNT(*) FROM don_hang WHERE trang_thai='HUY'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double doanhThuTheoNgay(Date ngay) {
        String sql = "SELECT SUM(tong_tien) FROM don_hang WHERE DATE(ngay_dat) = ? AND trang_thai <> 'HUY'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(ngay.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String generateChiTietId() {
        return "CT" + System.currentTimeMillis() % 1000000; // Shorter ID
    }

    private String generateDonHangId() {
        return "DH" + System.currentTimeMillis() % 1000000; // Shorter ID
    }
}
