package DAO;

import Database.DBConnection;
import Models.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public List<KhachHang> selectAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getString("khach_hang_id"),
                        rs.getString("user_id"),
                        rs.getString("ho_ten"),
                        rs.getString("ngay_sinh"),
                        rs.getString("dia_chi"),
                        rs.getString("sdt"),
                        rs.getString("email")
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public KhachHang selectById(String khachHangId) {
        String sql = "SELECT * FROM khach_hang WHERE khach_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHangId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                            rs.getString("khach_hang_id"),
                            rs.getString("user_id"),
                            rs.getString("ho_ten"),
                            rs.getString("ngay_sinh"),
                            rs.getString("dia_chi"),
                            rs.getString("sdt"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO khach_hang(khach_hang_id, user_id, ho_ten, ngay_sinh, dia_chi, sdt, email) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getKhachHangId());
            ps.setString(2, kh.getUserId());
            ps.setString(3, kh.getHoTen());
            ps.setString(4, kh.getNgaySinh());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getSdt());
            ps.setString(7, kh.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(KhachHang kh) {
        String sql = "UPDATE khach_hang SET user_id=?, ho_ten=?, ngay_sinh=?, dia_chi=?, sdt=?, email=? WHERE khach_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getUserId());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getNgaySinh());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSdt());
            ps.setString(6, kh.getEmail());
            ps.setString(7, kh.getKhachHangId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String khachHangId) {
        String sql = "DELETE FROM khach_hang WHERE khach_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHangId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsById(String khachHangId) {
        String sql = "SELECT COUNT(*) FROM khach_hang WHERE khach_hang_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHangId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public KhachHang selectByUserId(String userId) {
        String sql = "SELECT * FROM khach_hang WHERE user_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                            rs.getString("khach_hang_id"),
                            rs.getString("user_id"),
                            rs.getString("ho_ten"),
                            rs.getString("ngay_sinh"),
                            rs.getString("dia_chi"),
                            rs.getString("sdt"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}