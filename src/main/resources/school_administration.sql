-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2020 at 06:13 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school_administration`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `full_address` varchar(255) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `province` varchar(255) NOT NULL,
  `sub_district` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `address_id`, `city`, `district`, `full_address`, `postal_code`, `province`, `sub_district`) VALUES
(1, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(3, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(4, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(5, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(6, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(7, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(8, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(9, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(10, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(17, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(19, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(20, NULL, 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur'),
(21, '693774', 'Bekasi', 'Jatiasih', 'Parpostel', '17770', 'Jawa Barat', 'Jatiluhur');

-- --------------------------------------------------------

--
-- Table structure for table `audience`
--

CREATE TABLE `audience` (
  `id` bigint(20) NOT NULL,
  `audience_id` varchar(255) DEFAULT NULL,
  `audience_name` varchar(100) NOT NULL,
  `birth_date` varchar(255) NOT NULL,
  `birth_place` varchar(255) NOT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `encrypt_password` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `audience`
--

INSERT INTO `audience` (`id`, `audience_id`, `audience_name`, `birth_date`, `birth_place`, `created_date`, `encrypt_password`, `gender`, `is_active`, `modified_by`, `modified_date`, `phone_number`, `address_id`) VALUES
(1, '803743775933375259891249671049', 'Azka Khairul Izza', '9 Maret 1992', 'Jakarta', '29/03/2020 19:35:07', '$2a$10$anGi8Xl8/d/ppWmNynzkzuXat6n5F7yJJlVaUN3OHIlgFQ9EGX70K', 'Lelaki', b'1', NULL, NULL, '08999076094', 1),
(3, '724664021223704268663970841382', 'Putri Ayuni Lestari', '26 Maret 1995', 'Jakarta', '29/03/2020 19:36:08', '$2a$10$nrP1MDtoZxs0ZK7bW3kR/O2VZmLhxvUnnHVv7dhsiXm3aNSWGC/kC', 'Perempuan', b'1', NULL, NULL, '08999076094', 3),
(4, '079765032165498753966065072587', 'Putri Ayuni', '26 Maret 1995', 'Jakarta', '29/03/2020 19:47:47', '$2a$10$Ndpuv7YWsZ7AsrIZqz.V2u1bTfHNWqpPtxyjnstCIPo.j22L4UCGu', 'Perempuan', b'1', NULL, NULL, '08999076094', 4),
(5, '002841955271508543182900537754', 'Putri ', '26 Maret 1995', 'Jakarta', '29/03/2020 19:49:57', '$2a$10$k09QgL3YV0Kz5mnQNYNwAeISE4GjxdKG69ERGDbLt4XUWUOTWt0nK', 'Perempuan', b'1', NULL, NULL, '08999076094', 5),
(6, '401298564562447268666063758801', 'Azura', '26 Maret 1995', 'Jakarta', '29/03/2020 19:51:34', '$2a$10$1RJaIBbYurzorB0Zobg3pu50CueY2xdr7qLM9YQ1YE8G5ZEQ6RH7.', 'Perempuan', b'1', NULL, NULL, '08999076094', 6),
(7, '692849704018394194743558640895', 'Azura Ramadhania Abdillah', '26 Maret 1995', 'Jakarta', '29/03/2020 19:54:26', '$2a$10$oL6TijGM2pE40qIKw.MsQO.ehIv1vc4If63gmpQJV4iFCJxATi4T2', 'Perempuan', b'1', NULL, NULL, '08999076094', 7),
(8, '441531336557528712302897317359', 'Azura Ramadhania', '26 Maret 1995', 'Jakarta', '29/03/2020 19:55:10', '$2a$10$x6M5vuqPhfvVPS6IOk7DiuQ21sRCy0fecMw9z7UNbyE4BffLSf5OK', 'Perempuan', b'1', NULL, NULL, '08999076094', 8),
(9, '896831913651339707899550938353', 'Ibnaty', '26 Maret 1995', 'Jakarta', '29/03/2020 19:55:40', '$2a$10$gUc57VN0zkrr7ATESi6dhOZGEus3ZSQCwq3rJEfB.JXE.aE2y3.MC', 'Perempuan', b'1', NULL, NULL, '08999076094', 9),
(10, '326623751563694790915704793572', 'Ibnaty Kireina', '26 Maret 1995', 'Jakarta', '29/03/2020 19:57:06', '$2a$10$e8M1/fHQea3xd17UbMeIJO2hEMh4MFsKABVTLtS/IjNDUIfoyFZqe', 'Perempuan', b'1', NULL, NULL, '08999076094', 10),
(12, '418511496831219947747412060502', 'Ibnaty Kireina Ayra', '26 Maret 1995', 'Jakarta', '30/03/2020 14:16:22', '$2a$10$UVMJYWbLNLwqmgoMlVCJC.gWuqrN9LW8u2itIzP7L.sIFC6v8yftu', 'Perempuan', b'1', NULL, NULL, '08999076094', 17),
(14, '714472027549993712156501915135', 'Ibnaty Kireina Ayr', '26 Maret 1995', 'Jakarta', '30/03/2020 14:18:43', '$2a$10$4Dsc6ZCoc06ZJbF/YlOf6e20hwgLCaXJDS4ddNYxjcUsqgAkfs87y', 'Perempuan', b'1', NULL, NULL, '08999076094', 19),
(15, '607061857326914504280847763777', 'Ibnaty Kireina Ay', '26 Maret 1995', 'Jakarta', '30/03/2020 14:21:06', '$2a$10$KGGfTRaWMAthxtpf3wMsXegB6uebX4cKl9rdGZ1kBLyuiNDv1OwZO', 'Perempuan', b'1', NULL, NULL, '08999076094', 20),
(16, '132829590209943090239184236070', 'Ibnaty Kireina A', '26 Maret 1995', 'Jakarta', '30/03/2020 14:27:19', '$2a$10$S.hB8GI5velPuawFIDrr8.VDjGvMpduUGlzKxM6.to6AmZir8gpNS', 'Perempuan', b'1', NULL, NULL, '08999076094', 21);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  `invoice_date` varchar(255) NOT NULL,
  `invoice_id` varchar(255) NOT NULL,
  `invoice_name` varchar(120) NOT NULL,
  `is_expired` bit(1) DEFAULT NULL,
  `is_payment` bit(1) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `nominal` varchar(255) NOT NULL,
  `qr_content` varchar(255) DEFAULT NULL,
  `audience_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `expired_date` varchar(255) NOT NULL,
  `is_expired` bit(1) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `price` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `product_name` varchar(120) NOT NULL,
  `quantity` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role_id`, `role_name`) VALUES
(1, '001', 'Admin'),
(2, '002', 'Finance'),
(3, '003', 'Audience');

-- --------------------------------------------------------

--
-- Table structure for table `sequence_id`
--

CREATE TABLE `sequence_id` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_value` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sequence_id`
--

INSERT INTO `sequence_id` (`sequence_name`, `sequence_value`) VALUES
('addressID', 21),
('audienceID', 16),
('invoiceID', 0),
('productID', 0),
('roleID', 0),
('transactionID', 0),
('userID', 2);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `amount` varchar(255) NOT NULL,
  `audience_id` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  `invoice_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `solved_date` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `trx_verified` bit(1) DEFAULT NULL,
  `user_checking` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypt_password` varchar(255) NOT NULL,
  `full_name` varchar(120) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `username` varchar(120) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `created_date`, `email`, `encrypt_password`, `full_name`, `is_active`, `jabatan`, `modified_by`, `modified_date`, `role_name`, `user_id`, `username`, `role_id`) VALUES
(1, '29/03/2020 19:36:44', 'azka.khairul@metranet.co.id', '$2a$10$qdHQbnnJu9g0ufYsnoicyOkKVY5hmyOmz.joSrYLLbecuZy0LQyjW', 'Azka Khairul Izza', b'1', 'Sekretaris', NULL, NULL, 'Finance', '386195165795128939681260262674', 'sang biang', 2),
(2, '29/03/2020 19:37:03', 'azka.khairul@metranet.co.id', '$2a$10$rS0fLOsMmSMttLxHtgp02.y/dl5E.6E26HLR5TQroRiXLFSuPPc92', 'Azka Khairul Izza', b'1', 'Director', NULL, NULL, 'Admin', '580207065825375713763182186871', 'sang bango', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `audience`
--
ALTER TABLE `audience`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_j9jtaxiw2yhpkc4qv92o54lb9` (`audience_name`),
  ADD KEY `FKodoaxt3h8e92437myimhdbntk` (`address_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm910cwmxay1cigyvirp8u3cap` (`audience_id`),
  ADD KEY `FKr006i8cut2ges4x52xnp9g68i` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_383i0awxqlq7pc33hil7afbgo` (`product_name`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sequence_id`
--
ALTER TABLE `sequence_id`
  ADD PRIMARY KEY (`sequence_name`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `audience`
--
ALTER TABLE `audience`
  ADD CONSTRAINT `FKodoaxt3h8e92437myimhdbntk` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKm910cwmxay1cigyvirp8u3cap` FOREIGN KEY (`audience_id`) REFERENCES `audience` (`id`),
  ADD CONSTRAINT `FKr006i8cut2ges4x52xnp9g68i` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
