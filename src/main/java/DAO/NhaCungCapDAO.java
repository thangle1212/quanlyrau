package DAO;

import Database.DBConnection;
import Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public List<NhaCungCap> findAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhaCungCap n = new NhaCungCap(
                        rs.getString("ncc_id"),
                        rs.getString("ten_ncc"),
                        rs.getString("dia_chi"),
                        rs.getString("sdt"),
                        rs.getString("email")
                );
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(NhaCungCap n) {
        String sql = "INSERT INTO nha_cung_cap(ncc_id, ten_ncc, dia_chi, sdt, email) VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n.getNccId());
            ps.setString(2, n.getTenNcc());
            ps.setString(3, n.getDiaChi());
            ps.setString(4, n.getSdt());
            ps.setString(5, n.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(NhaCungCap n) {
        String sql = "UPDATE nha_cung_cap SET ten_ncc=?, dia_chi=?, sdt=?, email=? WHERE ncc_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n.getTenNcc());
            ps.setString(2, n.getDiaChi());
            ps.setString(3, n.getSdt());
            ps.setString(4, n.getEmail());
            ps.setString(5, n.getNccId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String nccId) {
        String sql = "DELETE FROM nha_cung_cap WHERE ncc_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nccId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsById(String nccId) {
        String sql = "SELECT COUNT(*) FROM nha_cung_cap WHERE ncc_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nccId);
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

    public List<NhaCungCap> findByIdOrTen(String keyword) {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap WHERE ncc_id = ? OR ten_ncc LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, keyword);
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhaCungCap n = new NhaCungCap(
                            rs.getString("ncc_id"),
                            rs.getString("ten_ncc"),
                            rs.getString("dia_chi"),
                            rs.getString("sdt"),
                            rs.getString("email")
                    );
                    list.add(n);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
