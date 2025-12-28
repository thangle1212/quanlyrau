package com.mycompany.quanlyrau.view;

import com.mycompany.quanlyrau.controller.AdminController;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {

    private final AdminController controller;

    public AdminMainFrame(AdminController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Quản lý bán rau - ADMIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        RauPanel rauPanel = new RauPanel();
        controller.bindRauPanel(rauPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản lý rau", rauPanel);
        tabbedPane.addTab("Quản lý NCC", new NhaCungCapPanel(controller));
        tabbedPane.addTab("Quản lý đơn hàng", new DonHangPanel(controller));
        tabbedPane.addTab("Thống kê", new ThongKePanel(controller));

        add(tabbedPane, BorderLayout.CENTER);
    }
}
