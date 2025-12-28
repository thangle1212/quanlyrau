package com.mycompany.quanlyrau.dao;

import com.mycompany.quanlyrau.model.Rau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RauDAO {

    public List<Rau> findAll() {
        List<Rau> list = new ArrayList<>();
        String sql = "SELECT * FROM rau";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rau r = new Rau(
                        rs.getString("rau_id"),
                        rs.getString("ten_rau"),
                        rs.getDouble("gia"),
                        rs.getInt("so_luong"),
                        rs.getString("don_vi_tinh"),
                        rs.getString("trang_thai"),
                        rs.getString("ncc_id")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Rau r) {
        String sql = "INSERT INTO rau(rau_id, ten_rau, gia, so_luong, don_vi_tinh, trang_thai, ncc_id) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getRauId());
            ps.setString(2, r.getTenRau());
            ps.setDouble(3, r.getGia());
            ps.setInt(4, r.getSoLuong());
            ps.setString(5, r.getDonViTinh());
            ps.setString(6, r.getTrangThai());
            ps.setString(7, r.getNccId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Rau r) {
        String sql = "UPDATE rau SET ten_rau=?, gia=?, so_luong=?, don_vi_tinh=?, trang_thai=?, ncc_id=? WHERE rau_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getTenRau());
            ps.setDouble(2, r.getGia());
            ps.setInt(3, r.getSoLuong());
            ps.setString(4, r.getDonViTinh());
            ps.setString(5, r.getTrangThai());
            ps.setString(6, r.getNccId());
            ps.setString(7, r.getRauId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String rauId) {
        String sql = "DELETE FROM rau WHERE rau_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rauId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsById(String rauId) {
        String sql = "SELECT COUNT(*) FROM rau WHERE rau_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rauId);
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
}
