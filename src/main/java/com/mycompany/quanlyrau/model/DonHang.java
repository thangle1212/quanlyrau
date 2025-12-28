package com.mycompany.quanlyrau.model;

import java.time.LocalDateTime;

public class DonHang {
    private String donHangId;
    private String khachHangId;
    private LocalDateTime ngayDat;
    private double tongTien;
    private String trangThai; // CHO_XU_LY, VAN_CHUYEN, DA_GIAO, HUY

    public DonHang() {
    }

    public DonHang(String donHangId, String khachHangId, LocalDateTime ngayDat, double tongTien, String trangThai) {
        this.donHangId = donHangId;
        this.khachHangId = khachHangId;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getDonHangId() {
        return donHangId;
    }

    public void setDonHangId(String donHangId) {
        this.donHangId = donHangId;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
