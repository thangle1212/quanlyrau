package Controllers;

import DAO.RauDAO;
import Models.Rau;
import java.util.List;

public class RauController {
    private RauDAO rauDAO = new RauDAO();

    private String tinhTrangThaiRauTheoSoLuong(int soLuong) {
        return soLuong <= 0 ? "HET_HANG" : "CON_HANG";
    }

    public List<Rau> getDanhSachRau() {
        return rauDAO.findAll();
    }

    public Rau getRauid(String rauId) {
        return rauDAO.selectById(rauId);
    }

    public boolean updateSoLuong(String rauId, int soLuongMua) {
        if (rauId == null || rauId.trim().isEmpty()) return false;
        if (soLuongMua <= 0) return false;

        Rau rau = rauDAO.selectById(rauId);
        if (rau == null) return false;

        int tonKho = rau.getSoLuong();
        if (soLuongMua > tonKho) return false;

        rau.setSoLuong(tonKho - soLuongMua);
        rau.setTrangThai(tinhTrangThaiRauTheoSoLuong(rau.getSoLuong()));
        return rauDAO.update(rau);
    }

    public boolean hoanSoLuong(String rauId, int soLuongTra) {
        if (rauId == null || rauId.trim().isEmpty()) return false;
        if (soLuongTra <= 0) return false;

        Rau rau = rauDAO.selectById(rauId);
        if (rau == null) return false;

        rau.setSoLuong(rau.getSoLuong() + soLuongTra);
        rau.setTrangThai(tinhTrangThaiRauTheoSoLuong(rau.getSoLuong()));
        return rauDAO.update(rau);
    }
}