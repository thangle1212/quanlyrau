# Quản Lý Rau - Vegetable Store Management System

## Mô tả (Description)
Hệ thống quản lý cửa hàng rau sạch, bao gồm các chức năng quản lý khách hàng, nhà cung cấp, rau củ và đơn hàng.

A vegetable store management system that includes functions for managing customers, suppliers, vegetables, and orders.

## Cài đặt (Installation)

### 1. Cài đặt MySQL (MySQL Installation)
- Cài đặt MySQL Server trên máy tính của bạn
- Đảm bảo MySQL đang chạy (service đang active)

### 2. Tạo cơ sở dữ liệu (Create Database)
- Sử dụng một trong hai file SQL sau để tạo cơ sở dữ liệu:

**Tùy chọn 1: Schema + Dữ liệu mẫu (Schema + Sample Data)**
```bash
mysql -u root -p < complete_database_with_sample_data.sql
```

**Tùy chọn 2: Chỉ Schema (Schema Only)**
```bash
mysql -u root -p < quanlybanrau.sql
```

Sau đó thêm dữ liệu mẫu:
```bash
mysql -u root -p < sample_data.sql
```

### 3. Cấu hình kết nối cơ sở dữ liệu (Database Connection Configuration)
- Cập nhật thông tin kết nối trong file `src/main/java/Database/DBConnection.java`
- Đảm bảo thông tin username, password và tên database đúng với cấu hình MySQL của bạn
- **Tên database mặc định:** `quanlybanrau`
- Nếu bạn sử dụng tên database khác, hãy cập nhật trong file `DBConnection.java`

### 4. Chạy ứng dụng (Run Application)
- Trong NetBeans: Mở project và nhấn "Run"
- Hoặc sử dụng Maven:
```bash
mvn clean compile exec:java -Dexec.mainClass="Quanlyrau"
```

## Tài khoản mặc định (Default Account)
- **Username:** admin
- **Password:** admin

## Cấu trúc cơ sở dữ liệu (Database Structure)
- `tai_khoan`: Bảng lưu thông tin tài khoản người dùng
- `khach_hang`: Bảng lưu thông tin khách hàng
- `nha_cung_cap`: Bảng lưu thông tin nhà cung cấp
- `rau`: Bảng lưu thông tin các loại rau
- `don_hang`: Bảng lưu thông tin đơn hàng
- `chi_tiet_don_hang`: Bảng lưu chi tiết các đơn hàng

## Chức năng chính (Main Features)
- Đăng nhập/đăng xuất
- Quản lý khách hàng
- Quản lý nhà cung cấp
- Quản lý rau củ
- Quản lý đơn hàng
- Thống kê báo cáo