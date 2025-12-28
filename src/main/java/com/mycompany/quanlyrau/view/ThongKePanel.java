package com.mycompany.quanlyrau.view;

import com.mycompany.quanlyrau.controller.AdminController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThongKePanel extends JPanel {

    private final AdminController controller;
    private JLabel lblSoDonDat, lblSoDonHuy, lblDoanhThu;
        private JSpinner spinnerNgay;
        private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");

    public ThongKePanel(AdminController controller) {
        this.controller = controller;
        initUI();
        loadTongQuan();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(2, 1));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSoDonDat = new JLabel("Số đơn đã đặt: 0");
        lblSoDonHuy = new JLabel("   Số đơn đã hủy: 0");
        row1.add(lblSoDonDat);
        row1.add(lblSoDonHuy);
        top.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Ngày (dd-MM-yyyy):"));
        spinnerNgay = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerNgay, "dd-MM-yyyy");
        spinnerNgay.setEditor(editor);
        spinnerNgay.setValue(new Date());
        spinnerNgay.setPreferredSize(new Dimension(120, spinnerNgay.getPreferredSize().height));
        row2.add(spinnerNgay);
        JButton btnTinh = new JButton("Tính doanh thu");
        JButton btnLamMoi = new JButton("Làm mới");
        row2.add(btnTinh);
        row2.add(btnLamMoi);
        lblDoanhThu = new JLabel("   Doanh thu: 0");
        row2.add(lblDoanhThu);
        top.add(row2);

        add(top, BorderLayout.NORTH);

        btnTinh.addActionListener((ActionEvent e) -> tinhDoanhThu());
        btnLamMoi.addActionListener((ActionEvent e) -> lamMoiThongKe());
    }

    private void loadTongQuan() {
        int soDonDat = controller.getSoDonDaDat();
        int soDonHuy = controller.getSoDonDaHuy();
        lblSoDonDat.setText("Số đơn đã đặt: " + soDonDat);
        lblSoDonHuy.setText("   Số đơn đã hủy: " + soDonHuy);
    }

    private void tinhDoanhThu() {
        try {
            Date d = (Date) spinnerNgay.getValue();
            double dt = controller.getDoanhThuTheoNgay(d);
            lblDoanhThu.setText("   Doanh thu: " + dt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ, định dạng dd-MM-yyyy");
        }
    }

    private void lamMoiThongKe() {
        // cập nhật lại tổng quan và reset ngày + doanh thu
        loadTongQuan();
        spinnerNgay.setValue(new Date());
        lblDoanhThu.setText("   Doanh thu: 0");
    }
}
