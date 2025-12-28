package DAO;

import Model.Rau;
import DAO.DBconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Rau;

public class RauDAO {

    // Lấy tất cả rau (cho KHÁCH HÀNG xem)
    public List<Rau> getAllRau() {
        List<Rau> list = new ArrayList<>();
        String sql = "SELECT * FROM rau";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rau rau = new Rau();
                rau.setRauID(rs.getString("rau_id"));
                rau.setTenRau(rs.getString("ten_rau"));
                rau.setGia(rs.getDouble("gia"));
                rau.setSoLuong(rs.getInt("so_luong"));
                rau.setDonViTinh(rs.getString("don_vi_tinh"));
                rau.setTrangThai(rs.getString("trang_thai"));
                rau.setNccId(rs.getString("ncc_id"));

                list.add(rau);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy rau theo ID (dùng khi mua rau)
    public Rau getRauById(String rauId) {
        String sql = "SELECT * FROM rau WHERE rau_id = ?";
        Rau rau = null;

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rauId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rau = new Rau();
                rau.setRauID(rs.getString("rau_id"));
                rau.setTenRau(rs.getString("ten_rau"));
                rau.setGia(rs.getDouble("gia"));
                rau.setSoLuong(rs.getInt("so_luong"));
                rau.setDonViTinh(rs.getString("don_vi_tinh"));
                rau.setTrangThai(rs.getString("trang_thai"));
                rau.setNccId(rs.getString("ncc_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rau;
    }
}
