/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class Khachhang {
    String KhachhangID,UserID,Hoten,Ngaysinh,Diachi,sdt,email;

    public Khachhang(String KhachhangID, String UserID, String Hoten, String Ngaysinh, String Diachi, String sdt, String email) {
        this.KhachhangID = KhachhangID;
        this.UserID = UserID;
        this.Hoten = Hoten;
        this.Ngaysinh = Ngaysinh;
        this.Diachi = Diachi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getKhachhangID() {
        return KhachhangID;
    }

    public void setKhachhangID(String KhachhangID) {
        this.KhachhangID = KhachhangID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
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
