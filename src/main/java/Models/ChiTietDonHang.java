package Models;

public class ChiTietDonHang {
    private String chiTietId;
    private String donHangId;
    private String rauId;
    private int soLuong;
    private double donGia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(String chiTietId, String donHangId, String rauId, int soLuong, double donGia) {
        this.chiTietId = chiTietId;
        this.donHangId = donHangId;
        this.rauId = rauId;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getChiTietId() {
        return chiTietId;
    }

    public void setChiTietId(String chiTietId) {
        this.chiTietId = chiTietId;
    }

    public String getDonHangId() {
        return donHangId;
    }

    public void setDonHangId(String donHangId) {
        this.donHangId = donHangId;
    }

    public String getRauId() {
        return rauId;
    }

    public void setRauId(String rauId) {
        this.rauId = rauId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}