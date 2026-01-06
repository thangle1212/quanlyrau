package Models;

import java.util.ArrayList;
import java.util.List;

public class GioHangManager {
    private static List<ChiTietDonHang> gioHang = new ArrayList<>();

    public static void themSanPham(Rau rau, int soLuong) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ chưa
        for (ChiTietDonHang ct : gioHang) {
            if (ct.getRauId().equals(rau.getRauId())) {
                // Nếu đã tồn tại, cập nhật số lượng
                ct.setSoLuong(ct.getSoLuong() + soLuong);
                return;
            }
        }

        // Nếu chưa tồn tại, thêm mới vào giỏ
        ChiTietDonHang chiTiet = new ChiTietDonHang();
        chiTiet.setRauId(rau.getRauId());
        chiTiet.setSoLuong(soLuong);
        chiTiet.setDonGia(rau.getGia());

        gioHang.add(chiTiet);
    }

    public static List<ChiTietDonHang> getGioHang() {
        return gioHang;
    }

    public static void clear() {
        gioHang.clear();
    }

    public static void remove(int index) {
        if (index >= 0 && index < gioHang.size()) {
            gioHang.remove(index);
        }
    }
}