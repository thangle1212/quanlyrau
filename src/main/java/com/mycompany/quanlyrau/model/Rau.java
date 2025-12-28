package com.mycompany.quanlyrau.model;

public class Rau {
    private String rauId;
    private String tenRau;
    private double gia;
    private int soLuong;
    private String donViTinh;
    private String trangThai; // CON_HANG, HET_HANG
    private String nccId;

    public Rau() {
    }

    public Rau(String rauId, String tenRau, double gia, int soLuong, String donViTinh, String trangThai, String nccId) {
        this.rauId = rauId;
        this.tenRau = tenRau;
        this.gia = gia;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.trangThai = trangThai;
        this.nccId = nccId;
    }

    public String getRauId() {
        return rauId;
    }

    public void setRauId(String rauId) {
        this.rauId = rauId;
    }

    public String getTenRau() {
        return tenRau;
    }

    public void setTenRau(String tenRau) {
        this.tenRau = tenRau;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNccId() {
        return nccId;
    }

    public void setNccId(String nccId) {
        this.nccId = nccId;
    }
}
