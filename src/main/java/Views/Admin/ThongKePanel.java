package Views.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThongKePanel extends JPanel {
    private JLabel lblSoDonDat, lblSoDonHuy, lblDoanhThu;
    private JSpinner spinnerNgay;
    private JButton btnTinh;
    private JButton btnLamMoi;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public ThongKePanel() {
        initUI();
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
        btnTinh = new JButton("Tính doanh thu");
        btnLamMoi = new JButton("Làm mới");
        row2.add(btnTinh);
        row2.add(btnLamMoi);
        lblDoanhThu = new JLabel("   Doanh thu: 0");
        row2.add(lblDoanhThu);
        top.add(row2);

        add(top, BorderLayout.NORTH);

    }

    public void setTongQuan(int soDonDat, int soDonHuy) {
        lblSoDonDat.setText("Số đơn đã đặt: " + soDonDat);
        lblSoDonHuy.setText("   Số đơn đã hủy: " + soDonHuy);
    }

    public Date getSelectedDate() {
        return (Date) spinnerNgay.getValue();
    }

    public void setDoanhThu(double doanhThu) {
        lblDoanhThu.setText("   Doanh thu: " + doanhThu);
    }

    public void resetThongKe() {
        spinnerNgay.setValue(new Date());
        lblDoanhThu.setText("   Doanh thu: 0");
    }

    public void addTinhListener(ActionListener l) { btnTinh.addActionListener(l); }
    public void addLamMoiListener(ActionListener l) { btnLamMoi.addActionListener(l); }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
