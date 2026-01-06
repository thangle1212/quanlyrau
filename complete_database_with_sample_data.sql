-- Complete database schema and sample data for vegetable store management system

DROP DATABASE IF EXISTS quanlybanrau;
CREATE DATABASE quanlybanrau;
USE quanlybanrau;

CREATE TABLE tai_khoan (
    user_id VARCHAR(10) PRIMARY KEY,
    ten_dang_nhap VARCHAR(50) NOT NULL UNIQUE,
    mat_khau VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','CUSTOMER') NOT NULL
);

CREATE TABLE khach_hang (
    khach_hang_id VARCHAR(10) PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    ho_ten VARCHAR(100),
    ngay_sinh varchar(100),
    dia_chi VARCHAR(255),
    sdt VARCHAR(15),
    email VARCHAR(100),

    CONSTRAINT fk_kh_user
    FOREIGN KEY (user_id) REFERENCES tai_khoan(user_id)
    ON DELETE CASCADE
);

CREATE TABLE nha_cung_cap (
    ncc_id VARCHAR(10) PRIMARY KEY,
    ten_ncc VARCHAR(100) NOT NULL,
    dia_chi VARCHAR(255),
    sdt VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE rau (
    rau_id VARCHAR(10) PRIMARY KEY,
    ten_rau VARCHAR(100) NOT NULL,
    gia DECIMAL(10,2) NOT NULL,
    so_luong INT NOT NULL,
    don_vi_tinh VARCHAR(50),
    trang_thai ENUM('CON_HANG','HET_HANG') DEFAULT 'CON_HANG',
    ncc_id VARCHAR(10),

    CONSTRAINT fk_rau_ncc
    FOREIGN KEY (ncc_id) REFERENCES nha_cung_cap(ncc_id)
    ON DELETE SET NULL
);

CREATE TABLE don_hang (
    don_hang_id VARCHAR(10) PRIMARY KEY,
    khach_hang_id VARCHAR(10) NOT NULL,
    ngay_dat DATETIME DEFAULT CURRENT_TIMESTAMP,
    tong_tien DECIMAL(12,2),
    trang_thai ENUM('CHO_XU_LY','VAN_CHUYEN','DA_GIAO','HUY') DEFAULT 'CHO_XU_LY',

    CONSTRAINT fk_dh_kh
    FOREIGN KEY (khach_hang_id) REFERENCES khach_hang(khach_hang_id)
    ON DELETE CASCADE
);

CREATE TABLE chi_tiet_don_hang (
    chi_tiet_id VARCHAR(10) PRIMARY KEY,
    don_hang_id VARCHAR(10) NOT NULL,
    rau_id VARCHAR(10) NOT NULL,
    so_luong INT NOT NULL,
    don_gia DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_ctdh_dh
    FOREIGN KEY (don_hang_id) REFERENCES don_hang(don_hang_id)
    ON DELETE CASCADE,

    CONSTRAINT fk_ctdh_rau
    FOREIGN KEY (rau_id) REFERENCES rau(rau_id)
    ON DELETE CASCADE
);

-- Insert sample accounts
INSERT INTO tai_khoan (user_id, ten_dang_nhap, mat_khau, role) VALUES
('U001', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN'), -- MD5 hash for 'admin'
('U002', 'customer1', 'customer123', 'CUSTOMER'),
('U003', 'customer2', 'customer456', 'CUSTOMER'),
('U004', 'customer3', 'customer789', 'CUSTOMER');

-- Insert sample customers
INSERT INTO khach_hang (khach_hang_id, user_id, ho_ten, ngay_sinh, dia_chi, sdt, email) VALUES
('KH001', 'U002', 'Nguyễn Văn A', '1990-05-15', '123 Đường ABC, Quận 1, TP.HCM', '0912345678', 'nguyenvana@email.com'),
('KH002', 'U003', 'Trần Thị B', '1985-11-22', '456 Đường XYZ, Quận 3, TP.HCM', '0923456789', 'tranthib@email.com'),
('KH003', 'U004', 'Lê Văn C', '1992-03-10', '789 Đường DEF, Quận 5, TP.HCM', '0934567890', 'levanc@email.com');

-- Insert sample suppliers
INSERT INTO nha_cung_cap (ncc_id, ten_ncc, dia_chi, sdt, email) VALUES
('NCC001', 'Nông Trại Sạch ABC', 'Thị Trấn Xuân Lộc, Huyện Xuân Lộc, Đồng Nai', '0987654321', 'nongtraiabc@email.com'),
('NCC002', 'Hợp Tác Xã Rau Xanh', 'Thị Trấn Trảng Bom, Huyện Trảng Bom, Đồng Nai', '0976543210', 'htxrauxanh@email.com'),
('NCC003', 'Công Ty TNHH Rau Sạch Việt', 'Khu Công Nghiệp Biên Hòa, TP. Biên Hòa', '0965432109', 'rausachviet@email.com');

-- Insert sample vegetables
INSERT INTO rau (rau_id, ten_rau, gia, so_luong, don_vi_tinh, trang_thai, ncc_id) VALUES
('R001', 'Rau muống', 15000.00, 50, 'kg', 'CON_HANG', 'NCC001'),
('R002', 'Cải ngọt', 20000.00, 30, 'kg', 'CON_HANG', 'NCC001'),
('R003', 'Rau dền', 18000.00, 25, 'kg', 'CON_HANG', 'NCC002'),
('R004', 'Xà lách', 25000.00, 20, 'kg', 'CON_HANG', 'NCC002'),
('R005', 'Rau thơm', 12000.00, 40, 'kg', 'CON_HANG', 'NCC003'),
('R006', 'Cải xanh', 17000.00, 35, 'kg', 'CON_HANG', 'NCC003'),
('R007', 'Rau ngót', 16000.00, 30, 'kg', 'CON_HANG', 'NCC001'),
('R008', 'Bắp cải', 22000.00, 25, 'kg', 'CON_HANG', 'NCC002');

-- Insert sample orders
INSERT INTO don_hang (don_hang_id, khach_hang_id, ngay_dat, tong_tien, trang_thai) VALUES
('DH001', 'KH001', '2024-12-01 10:30:00', 85000.00, 'DA_GIAO'),
('DH002', 'KH002', '2024-12-05 14:15:00', 67000.00, 'DA_GIAO'),
('DH003', 'KH001', '2024-12-10 09:45:00', 120000.00, 'VAN_CHUYEN'),
('DH004', 'KH003', '2024-12-15 16:20:00', 45000.00, 'CHO_XU_LY'),
('DH005', 'KH002', '2024-12-20 11:10:00', 95000.00, 'CHO_XU_LY');

-- Insert order details
INSERT INTO chi_tiet_don_hang (chi_tiet_id, don_hang_id, rau_id, so_luong, don_gia) VALUES
('CT001', 'DH001', 'R001', 2, 15000.00),
('CT002', 'DH001', 'R002', 1, 20000.00),
('CT003', 'DH001', 'R004', 1, 25000.00),
('CT004', 'DH001', 'R005', 2, 12000.00),
('CT005', 'DH002', 'R003', 1, 18000.00),
('CT006', 'DH002', 'R006', 2, 17000.00),
('CT007', 'DH002', 'R007', 1, 16000.00),
('CT008', 'DH003', 'R001', 3, 15000.00),
('CT009', 'DH003', 'R004', 2, 25000.00),
('CT010', 'DH003', 'R008', 1, 22000.00),
('CT011', 'DH004', 'R002', 1, 20000.00),
('CT012', 'DH004', 'R005', 2, 12000.00),
('CT013', 'DH005', 'R003', 2, 18000.00),
('CT014', 'DH005', 'R006', 1, 17000.00),
('CT015', 'DH005', 'R007', 1, 16000.00),
('CT016', 'DH005', 'R008', 2, 22000.00);

-- Create admin user for login
-- Username: admin, Password: admin (MD5 hash: 21232f297a57a5a743894a0e4a801fc3)