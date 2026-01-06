package Controllers;

import DAO.DonHangDAO;
import DAO.NhaCungCapDAO;
import DAO.RauDAO;
import DAO.UserDAO;
import Models.DonHang;
import Models.NhaCungCap;
import Models.Rau;
import Models.User;
import Views.Admin.DonHangPanel;
import Views.Admin.NhaCungCapPanel;
import Views.Admin.RauPanel;
import Views.Admin.ThongKePanel;

import java.util.Date;
import java.util.List;

public class AdminController {

    private final RauDAO rauDAO;
    private final NhaCungCapDAO nhaCungCapDAO;
    private final DonHangDAO donHangDAO;
    private final UserDAO userDAO;
    private RauPanel rauPanel;
    private NhaCungCapPanel nhaCungCapPanel;
    private DonHangPanel donHangPanel;
    private ThongKePanel thongKePanel;

    public AdminController() {
        this.rauDAO = new RauDAO();
        this.nhaCungCapDAO = new NhaCungCapDAO();
        this.donHangDAO = new DonHangDAO();
        this.userDAO = new UserDAO();
    }

    // =====================
    // QUẢN LÝ RAU
    // =====================
    public void bindRauPanel(RauPanel panel) {
        this.rauPanel = panel;

        // nạp dữ liệu ban đầu
        refreshRauData();
        refreshNccOptions();

        // lắng nghe nút
        panel.addThemListener(e -> handleThemRau());
        panel.addSuaListener(e -> handleSuaRau());
        panel.addXoaListener(e -> handleXoaRau());
        panel.addTimKiemListener(e -> handleTimKiemRau());
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

    private String tinhTrangThaiRauTheoSoLuong(int soLuong) {
        return soLuong <= 0 ? "HET_HANG" : "CON_HANG";
    }

    private void handleThemRau() {
        try {
            Rau r = rauPanel.getRauFromForm();
            r.setTrangThai(tinhTrangThaiRauTheoSoLuong(r.getSoLuong()));
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
            r.setTrangThai(tinhTrangThaiRauTheoSoLuong(r.getSoLuong()));
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

    private void handleTimKiemRau() {
        String keyword = rauPanel.getSearchKeyword();

        if (keyword == null || keyword.isEmpty()) {
            refreshRauData();
            return;
        }

        List<Rau> result = rauDAO.findByIdOrTen(keyword);

        rauPanel.setRauData(result);
        if (result == null || result.isEmpty()) {
            rauPanel.showMessage("Không tìm thấy rau phù hợp");
        }
    }

    // Rau (API dùng nơi khác)
    public List<Rau> getAllRau() {
        return rauDAO.findAll();
    }

    public boolean addRau(Rau r) {
        if (r != null) {
            r.setTrangThai(tinhTrangThaiRauTheoSoLuong(r.getSoLuong()));
        }
        return rauDAO.insert(r);
    }

    public boolean updateRau(Rau r) {
        if (r != null) {
            r.setTrangThai(tinhTrangThaiRauTheoSoLuong(r.getSoLuong()));
        }
        return rauDAO.update(r);
    }

    public boolean deleteRau(String id) {
        return rauDAO.delete(id);
    }

    public boolean isRauIdExists(String id) {
        return rauDAO.existsById(id);
    }

    // =====================
    // QUẢN LÝ ĐƠN HÀNG
    // =====================
    public void bindDonHangPanel(DonHangPanel panel) {
        this.donHangPanel = panel;

        refreshDonHangData();
        panel.addCapNhatTrangThaiListener(e -> handleCapNhatTrangThaiDonHang());
    }

    private void refreshDonHangData() {
        if (donHangPanel != null) {
            donHangPanel.setDonHangData(donHangDAO.findAll());
        }
    }

    private void handleCapNhatTrangThaiDonHang() {
        String id = donHangPanel.getSelectedDonHangId();
        if (id == null || id.isEmpty()) {
            donHangPanel.showMessage("Chọn một đơn hàng");
            return;
        }
        String trangThaiMoi = donHangPanel.getTrangThaiMoi();
        if (trangThaiMoi == null || trangThaiMoi.isEmpty()) {
            donHangPanel.showMessage("Chọn trạng thái mới");
            return;
        }
        if (donHangDAO.updateTrangThai(id, trangThaiMoi)) {
            donHangPanel.showMessage("Cập nhật trạng thái thành công");
            refreshDonHangData();
        } else {
            donHangPanel.showMessage("Cập nhật trạng thái thất bại");
        }
    }

    // Don hang (API dùng nơi khác)
    public List<DonHang> getAllDonHang() {
        return donHangDAO.findAll();
    }

    public boolean capNhatTrangThaiDonHang(String donHangId, String trangThaiMoi) {
        return donHangDAO.updateTrangThai(donHangId, trangThaiMoi);
    }

    // =====================
    // QUẢN LÝ NHÀ CUNG CẤP
    // =====================
    public void bindNhaCungCapPanel(NhaCungCapPanel panel) {
        this.nhaCungCapPanel = panel;

        refreshNhaCungCapData();

        panel.addThemListener(e -> handleThemNhaCungCap());
        panel.addSuaListener(e -> handleSuaNhaCungCap());
        panel.addXoaListener(e -> handleXoaNhaCungCap());
        panel.addTimKiemListener(e -> handleTimKiemNhaCungCap());
        panel.addLamMoiListener(e -> {
            refreshNhaCungCapData();
            panel.clearForm();
        });

        panel.getTableSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = panel.getTableSelectionModel().getMinSelectionIndex();
            panel.setFormFromTableRow(row);
        });
    }

    private void refreshNhaCungCapData() {
        if (nhaCungCapPanel != null) {
            nhaCungCapPanel.setNhaCungCapData(nhaCungCapDAO.findAll());
        }
    }

    private void handleThemNhaCungCap() {
        try {
            NhaCungCap n = nhaCungCapPanel.getNhaCungCapFromForm();
            if (n.getNccId() == null || n.getNccId().isEmpty()) {
                nhaCungCapPanel.showMessage("Mã NCC không được để trống");
                return;
            }
            if (nhaCungCapDAO.existsById(n.getNccId())) {
                nhaCungCapPanel.showMessage("Mã nhà cung cấp đã tồn tại, vui lòng nhập mã khác");
                return;
            }
            if (nhaCungCapDAO.insert(n)) {
                nhaCungCapPanel.showMessage("Thêm NCC thành công");
                refreshNhaCungCapData();
                nhaCungCapPanel.clearForm();
            } else {
                nhaCungCapPanel.showMessage("Thêm NCC thất bại");
            }
        } catch (Exception ex) {
            nhaCungCapPanel.showMessage("Dữ liệu không hợp lệ");
        }
    }

    private void handleSuaNhaCungCap() {
        try {
            NhaCungCap n = nhaCungCapPanel.getNhaCungCapFromForm();
            if (nhaCungCapDAO.update(n)) {
                nhaCungCapPanel.showMessage("Sửa NCC thành công");
                refreshNhaCungCapData();
            } else {
                nhaCungCapPanel.showMessage("Sửa NCC thất bại");
            }
        } catch (Exception ex) {
            nhaCungCapPanel.showMessage("Dữ liệu không hợp lệ");
        }
    }

    private void handleXoaNhaCungCap() {
        String id = nhaCungCapPanel.getSelectedNccId();
        if (id == null || id.isEmpty()) {
            // fallback: nếu user nhập mã vào form
            NhaCungCap n = nhaCungCapPanel.getNhaCungCapFromForm();
            id = n == null ? null : n.getNccId();
        }
        if (id == null || id.isEmpty()) {
            nhaCungCapPanel.showMessage("Chọn/nhập mã NCC để xóa");
            return;
        }
        if (nhaCungCapDAO.delete(id)) {
            nhaCungCapPanel.showMessage("Xóa NCC thành công");
            refreshNhaCungCapData();
            nhaCungCapPanel.clearForm();
        } else {
            nhaCungCapPanel.showMessage("Xóa NCC thất bại");
        }
    }

    private void handleTimKiemNhaCungCap() {
        String keyword = nhaCungCapPanel.getSearchKeyword();
        if (keyword == null || keyword.isEmpty()) {
            refreshNhaCungCapData();
            return;
        }
        List<NhaCungCap> result = nhaCungCapDAO.findByIdOrTen(keyword);
        nhaCungCapPanel.setNhaCungCapData(result);
        if (result == null || result.isEmpty()) {
            nhaCungCapPanel.showMessage("Không tìm thấy nhà cung cấp phù hợp");
        }
    }

    // Nha cung cap (API dùng nơi khác)
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

    public List<NhaCungCap> findNhaCungCapByIdOrTen(String keyword) {
        return nhaCungCapDAO.findByIdOrTen(keyword);
    }

    // =====================
    // THỐNG KÊ
    // =====================
    public void bindThongKePanel(ThongKePanel panel) {
        this.thongKePanel = panel;

        refreshTongQuanThongKe();

        panel.addTinhListener(e -> handleTinhDoanhThu());
        panel.addLamMoiListener(e -> {
            refreshTongQuanThongKe();
            panel.resetThongKe();
        });
    }

    private void refreshTongQuanThongKe() {
        if (thongKePanel != null) {
            thongKePanel.setTongQuan(donHangDAO.countDonDaDat(), donHangDAO.countDonDaHuy());
        }
    }

    private void handleTinhDoanhThu() {
        try {
            Date d = thongKePanel.getSelectedDate();
            double dt = donHangDAO.doanhThuTheoNgay(d);
            thongKePanel.setDoanhThu(dt);
        } catch (Exception ex) {
            thongKePanel.showMessage("Ngày không hợp lệ, định dạng dd-MM-yyyy");
        }
    }

    // Thống kê (API dùng nơi khác)
    public int getSoDonDaDat() {
        return donHangDAO.countDonDaDat();
    }

    public int getSoDonDaHuy() {
        return donHangDAO.countDonDaHuy();
    }

    public double getDoanhThuTheoNgay(Date ngay) {
        return donHangDAO.doanhThuTheoNgay(ngay);
    }

    // =====================
    // QUẢN LÝ TÀI KHOẢN
    // =====================
    public List<User> getAllAccounts() {
        return userDAO.findAll();
    }

    public boolean addAccount(User user) {
        return userDAO.insert(user);
    }

    public boolean updateAccount(User user) {
        return userDAO.update(user);
    }

    public boolean updateAccountWithoutPassword(User user) {
        return userDAO.updateWithoutPassword(user);
    }

    public boolean changePassword(String userId, String newPassword) {
        return userDAO.changePassword(userId, newPassword);
    }

    public boolean deleteAccount(String userId) {
        return userDAO.delete(userId);
    }

    public boolean isAccountIdExists(String userId) {
        return userDAO.existsById(userId);
    }
}
