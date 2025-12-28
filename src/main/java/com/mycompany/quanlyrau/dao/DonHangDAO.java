package com.mycompany.quanlyrau.dao;

import com.mycompany.quanlyrau.model.DonHang;

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
}
