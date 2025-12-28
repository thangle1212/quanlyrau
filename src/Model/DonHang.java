/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.sql.Timestamp;

public class DonHang {
    private String donhangId;
    private String khachHangId;
    private Timestamp ngayDat;
    private double tongTien;
    private String trangThai;

    public DonHang() {
    }

    public DonHang(String donhangId, String khachHangId, Timestamp ngayDat, double tongTien, String trangThai) {
        this.donhangId = donhangId;
        this.khachHangId = khachHangId;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getDonhangId() {
        return donhangId;
    }

    public void setDonhangId(String donhangId) {
        this.donhangId = donhangId;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }

    public Timestamp getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Timestamp ngayDat) {
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
