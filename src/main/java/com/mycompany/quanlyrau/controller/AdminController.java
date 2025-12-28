package com.mycompany.quanlyrau.controller;

import com.mycompany.quanlyrau.dao.DonHangDAO;
import com.mycompany.quanlyrau.dao.NhaCungCapDAO;
import com.mycompany.quanlyrau.dao.RauDAO;
import com.mycompany.quanlyrau.model.DonHang;
import com.mycompany.quanlyrau.model.NhaCungCap;
import com.mycompany.quanlyrau.model.Rau;
import com.mycompany.quanlyrau.view.RauPanel;

import java.util.Date;
import java.util.List;

public class AdminController {

    private final RauDAO rauDAO;
    private final NhaCungCapDAO nhaCungCapDAO;
    private final DonHangDAO donHangDAO;
    private RauPanel rauPanel;

    public AdminController() {
        this.rauDAO = new RauDAO();
        this.nhaCungCapDAO = new NhaCungCapDAO();
        this.donHangDAO = new DonHangDAO();
    }

    public void bindRauPanel(RauPanel panel) {
        this.rauPanel = panel;

        // nạp dữ liệu ban đầu
        refreshRauData();
        refreshNccOptions();

        // lắng nghe nút
        panel.addThemListener(e -> handleThemRau());
        panel.addSuaListener(e -> handleSuaRau());
        panel.addXoaListener(e -> handleXoaRau());
        panel.addLamMoiListener(e -> {
            refreshRauData();
            refreshNccOptions();
            panel.clearForm();
        });

        // chọn dòng trong bảng -> đổ form
        panel.getTableSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = panel.getTableSelectionModel().getMinSelectionIndex();
            panel.setFormFromTableRow(row);
        });
    }

    private void handleThemRau() {
        try {
            Rau r = rauPanel.getRauFromForm();
            if (r.getRauId() == null || r.getRauId().isEmpty()) {
                rauPanel.showMessage("Mã rau không được để trống");
                return;
            }
            if (rauDAO.existsById(r.getRauId())) {
                rauPanel.showMessage("Mã rau đã tồn tại, vui lòng nhập mã khác");
                return;
            }
            if (rauDAO.insert(r)) {
                rauPanel.showMessage("Thêm rau thành công");
                refreshRauData();
                rauPanel.clearForm();
            } else {
                rauPanel.showMessage("Thêm rau thất bại");
            }
        } catch (Exception ex) {
            rauPanel.showMessage("Dữ liệu không hợp lệ");
        }
    }

    private void handleSuaRau() {
        try {
            Rau r = rauPanel.getRauFromForm();
            if (rauDAO.update(r)) {
                rauPanel.showMessage("Sửa rau thành công");
                refreshRauData();
            } else {
                rauPanel.showMessage("Sửa rau thất bại");
            }
        } catch (Exception ex) {
            rauPanel.showMessage("Dữ liệu không hợp lệ");
        }
    }

    private void handleXoaRau() {
        String id = rauPanel.getSelectedRauId();
        if (id == null || id.isEmpty()) {
            rauPanel.showMessage("Chọn/nhập mã rau để xóa");
            return;
        }
        if (rauDAO.delete(id)) {
            rauPanel.showMessage("Xóa rau thành công");
            refreshRauData();
            rauPanel.clearForm();
        } else {
            rauPanel.showMessage("Xóa rau thất bại");
        }
    }

    private void refreshRauData() {
        if (rauPanel != null) {
            rauPanel.setRauData(rauDAO.findAll());
        }
    }

    private void refreshNccOptions() {
        if (rauPanel != null) {
            rauPanel.setNccOptions(nhaCungCapDAO.findAll());
        }
    }

    // Rau
    public List<Rau> getAllRau() {
        return rauDAO.findAll();
    }

    public boolean addRau(Rau r) {
        return rauDAO.insert(r);
    }

    public boolean updateRau(Rau r) {
        return rauDAO.update(r);
    }

    public boolean deleteRau(String id) {
        return rauDAO.delete(id);
    }

    public boolean isRauIdExists(String id) {
        return rauDAO.existsById(id);
    }

    // Nha cung cap
    public List<NhaCungCap> getAllNhaCungCap() {
        return nhaCungCapDAO.findAll();
    }

    public boolean addNhaCungCap(NhaCungCap n) {
        return nhaCungCapDAO.insert(n);
    }

    public boolean updateNhaCungCap(NhaCungCap n) {
        return nhaCungCapDAO.update(n);
    }

    public boolean deleteNhaCungCap(String id) {
        return nhaCungCapDAO.delete(id);
    }

    public boolean isNhaCungCapIdExists(String id) {
        return nhaCungCapDAO.existsById(id);
    }

    // Don hang
    public List<DonHang> getAllDonHang() {
        return donHangDAO.findAll();
    }

    // cập nhật trạng thái đơn hàng bất kỳ
    public boolean capNhatTrangThaiDonHang(String donHangId, String trangThaiMoi) {
        return donHangDAO.updateTrangThai(donHangId, trangThaiMoi);
    }

    // Thống kê
    public int getSoDonDaDat() {
        return donHangDAO.countDonDaDat();
    }

    public int getSoDonDaHuy() {
        return donHangDAO.countDonDaHuy();
    }

    public double getDoanhThuTheoNgay(Date ngay) {
        return donHangDAO.doanhThuTheoNgay(ngay);
    }
}
