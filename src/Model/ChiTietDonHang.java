/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

public class ChiTietDonHang {
    private int chiTietId;
    private String donHangId;
    private String rauId;
    private int soLuong;
    private double donGia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int chiTietId, String donHangId, String rauId, int soLuong, double donGia) {
        this.chiTietId = chiTietId;
        this.donHangId = donHangId;
        this.rauId = rauId;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getChiTietId() {
        return chiTietId;
    }

    public void setChiTietId(int chiTietId) {
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
