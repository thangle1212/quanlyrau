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