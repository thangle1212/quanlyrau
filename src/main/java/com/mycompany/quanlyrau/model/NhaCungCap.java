package com.mycompany.quanlyrau.model;

public class NhaCungCap {
    private String nccId;
    private String tenNcc;
    private String diaChi;
    private String sdt;
    private String email;

    public NhaCungCap() {
    }

    public NhaCungCap(String nccId, String tenNcc, String diaChi, String sdt, String email) {
        this.nccId = nccId;
        this.tenNcc = tenNcc;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getNccId() {
        return nccId;
    }

    public void setNccId(String nccId) {
        this.nccId = nccId;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
