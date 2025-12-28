package Model;

import java.util.ArrayList;
import java.util.List;

public class GioHangManager {

    private static final List<ChiTietDonHang> gioHang = new ArrayList<>();

    public static List<ChiTietDonHang> getGioHang() {
        return gioHang;
    }

    public static void clear() {
        gioHang.clear();
    }

    public static void them(ChiTietDonHang ct) {
        for (ChiTietDonHang item : gioHang) {
            if (item.getRauId().equals(ct.getRauId())) {
                item.setSoLuong(item.getSoLuong() + ct.getSoLuong());
                System.out.println("Tăng số lượng: " + ct.getRauId() + " lên " + item.getSoLuong());
                return;
            }
        }
        gioHang.add(ct);
        System.out.println("Thêm mới: " + ct.getRauId() + " số lượng " + ct.getSoLuong());
    }
    
    // Thêm phương thức mới để thêm từ Rau
    public static void themSanPham(Rau rau, int soLuong) {
        // Kiểm tra nếu đã có trong giỏ
        for (ChiTietDonHang item : gioHang) {
            if (item.getRauId().equals(rau.getRauID())) {
                item.setSoLuong(item.getSoLuong() + soLuong);
                System.out.println("Tăng số lượng: " + rau.getTenRau() + " lên " + item.getSoLuong());
                return;
            }
        }
        
        // Nếu chưa có, thêm mới
        ChiTietDonHang ct = new ChiTietDonHang();
        ct.setRauId(rau.getRauID());
        ct.setSoLuong(soLuong);
        ct.setDonGia(rau.getGia());
        gioHang.add(ct);
        
        System.out.println("Thêm mới vào giỏ: " + rau.getTenRau() + " x" + soLuong);
        System.out.println("Tổng số sản phẩm trong giỏ: " + gioHang.size());
    }
}