package com.mycompany.quanlyrau.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Đổi quanlybanrau thành qlrau theo tên database của bạn
    private static final String URL = "jdbc:mysql://localhost:3306/qlrau?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // sửa theo tài khoản MySQL của bạn
    private static final String PASSWORD = "";  // sửa theo mật khẩu MySQL của bạn

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
