-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2025 at 06:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlrau`
--

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_don_hang`
--

CREATE TABLE `chi_tiet_don_hang` (
  `chi_tiet_id` varchar(10) NOT NULL,
  `don_hang_id` varchar(10) NOT NULL,
  `rau_id` varchar(10) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `don_gia` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `don_hang`
--

CREATE TABLE `don_hang` (
  `don_hang_id` varchar(10) NOT NULL,
  `khach_hang_id` varchar(10) NOT NULL,
  `ngay_dat` datetime DEFAULT current_timestamp(),
  `tong_tien` decimal(12,2) DEFAULT NULL,
  `trang_thai` enum('CHO_XU_LY','VAN_CHUYEN','DA_GIAO','HUY') DEFAULT 'CHO_XU_LY'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `don_hang`
--

INSERT INTO `don_hang` (`don_hang_id`, `khach_hang_id`, `ngay_dat`, `tong_tien`, `trang_thai`) VALUES
('DH001', 'KH001', '2025-12-27 10:00:00', 150000.00, 'CHO_XU_LY'),
('DH002', 'KH001', '2025-12-27 11:30:00', 250000.00, 'HUY'),
('DH003', 'KH002', '2025-12-27 14:15:00', 320000.00, 'VAN_CHUYEN'),
('DH004', 'KH002', '2025-12-26 09:45:00', 180000.00, 'HUY');

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `khach_hang_id` varchar(10) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `ho_ten` varchar(100) DEFAULT NULL,
  `ngay_sinh` varchar(100) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `sdt` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`khach_hang_id`, `user_id`, `ho_ten`, `ngay_sinh`, `dia_chi`, `sdt`, `email`) VALUES
('KH001', 'U002', 'Nguyễn Văn A', '2000-01-01', 'Hà Nội', '0123456789', 'a@example.com'),
('KH002', 'U003', 'Trần Thị B', '2001-02-02', 'Hồ Chí Minh', '0987654321', 'b@example.com');

-- --------------------------------------------------------

--
-- Table structure for table `nha_cung_cap`
--

CREATE TABLE `nha_cung_cap` (
  `ncc_id` varchar(10) NOT NULL,
  `ten_ncc` varchar(100) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `sdt` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nha_cung_cap`
--

INSERT INTO `nha_cung_cap` (`ncc_id`, `ten_ncc`, `dia_chi`, `sdt`, `email`) VALUES
('122', 'linh', 'nam dinh', '01234', 'linh@gmail.com'),
('123', 'hop', 'nam dinh', '0123', 'hop@gmail.com'),
('124', 'giang', 'nam dinh', '01234567', 'giang@gmail.com'),
('128', 'linh', 'nam dinh', '01234', 'linh@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `rau`
--

CREATE TABLE `rau` (
  `rau_id` varchar(10) NOT NULL,
  `ten_rau` varchar(100) NOT NULL,
  `gia` decimal(10,2) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `don_vi_tinh` varchar(50) DEFAULT NULL,
  `trang_thai` enum('CON_HANG','HET_HANG') DEFAULT 'CON_HANG',
  `ncc_id` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rau`
--

INSERT INTO `rau` (`rau_id`, `ten_rau`, `gia`, `so_luong`, `don_vi_tinh`, `trang_thai`, `ncc_id`) VALUES
('1', 'rau muong', 3000.00, 30, 'vnd', 'CON_HANG', '123'),
('2', 'rau muong', 3000.00, 30, 'vnd', 'CON_HANG', '123'),
('3', 'rau cai', 9000.00, 80, 'vnd', 'CON_HANG', '123'),
('4', 'rau ca', 9000.00, 80, 'vnd', 'CON_HANG', '123'),
('5', 'rau ca na', 1234.00, 90, '90', 'CON_HANG', '124');

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `user_id` varchar(10) NOT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `role` enum('ADMIN','CUSTOMER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`user_id`, `ten_dang_nhap`, `mat_khau`, `role`) VALUES
('U001', 'admin1', '123456', 'ADMIN'),
('U002', 'kh001', '123456', 'CUSTOMER'),
('U003', 'kh002', '123456', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD PRIMARY KEY (`chi_tiet_id`),
  ADD KEY `fk_ctdh_dh` (`don_hang_id`),
  ADD KEY `fk_ctdh_rau` (`rau_id`);

--
-- Indexes for table `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`don_hang_id`),
  ADD KEY `fk_dh_kh` (`khach_hang_id`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`khach_hang_id`),
  ADD KEY `fk_kh_user` (`user_id`);

--
-- Indexes for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  ADD PRIMARY KEY (`ncc_id`);

--
-- Indexes for table `rau`
--
ALTER TABLE `rau`
  ADD PRIMARY KEY (`rau_id`),
  ADD KEY `fk_rau_ncc` (`ncc_id`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chi_tiet_don_hang`
--
ALTER TABLE `chi_tiet_don_hang`
  ADD CONSTRAINT `fk_ctdh_dh` FOREIGN KEY (`don_hang_id`) REFERENCES `don_hang` (`don_hang_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ctdh_rau` FOREIGN KEY (`rau_id`) REFERENCES `rau` (`rau_id`) ON DELETE CASCADE;

--
-- Constraints for table `don_hang`
--
ALTER TABLE `don_hang`
  ADD CONSTRAINT `fk_dh_kh` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`khach_hang_id`) ON DELETE CASCADE;

--
-- Constraints for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD CONSTRAINT `fk_kh_user` FOREIGN KEY (`user_id`) REFERENCES `tai_khoan` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `rau`
--
ALTER TABLE `rau`
  ADD CONSTRAINT `fk_rau_ncc` FOREIGN KEY (`ncc_id`) REFERENCES `nha_cung_cap` (`ncc_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
