-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2021 at 02:55 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_miniprojectv1`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `idProduct` varchar(100) NOT NULL,
  `product` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `idProvider` varchar(100) NOT NULL,
  `idType` int(100) NOT NULL,
  `stock` int(100) NOT NULL,
  `harga` double NOT NULL,
  `expiredDate` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idProduct`, `product`, `value`, `idProvider`, `idType`, `stock`, `harga`, `expiredDate`) VALUES
('54ccd38b-0ebb-40dc-8c13-a9ee9ba0a1e1', 'Simpati-Selamat', '3GB', 'ID004', 1, 100, 40000, '2022-02-16'),
('9e2de324-84cd-4f05-9402-e2c6a52ff131', 'XL-Selamatkan Bumi', '60K', 'ID001', 1, 70, 20000, '2022/02/22'),
('c317786e-3888-4ce2-bc8d-3bdba48c9692', 'IM3 Semakin di depan', '25K', 'ID005', 1, 30, 27000, '2021-11-11'),
('c87a8db0-22f7-4a3f-a476-7d9d44f40169', 'XL-Senangnya Dalam Hati', '60K', 'ID001', 1, 70, 20000, '2022/02/22'),
('caa0b600-5b39-4eb6-b418-f1b0c416f6fb', 'XL-Senangnya Nyata', '60K', 'ID001', 1, 70, 20000, '2022/02/22'),
('d666c177-8c44-4704-ac7f-896f005e8b56', 'XL-HotRod 4G Plus', '50K', 'ID001', 2, 80, 40000, '2022/02/10'),
('fbf57268-6e4a-420d-bf47-b1a12c0b8bad', 'IM3 Peduli', '25K', 'ID005', 1, 30, 27000, '2021-11-11');

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE `provider` (
  `idProvider` varchar(100) NOT NULL,
  `provider` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`idProvider`, `provider`) VALUES
('ID001', 'XL'),
('ID002', 'AXIS'),
('ID003', 'Indosat'),
('ID004', 'Simpati'),
('ID005', 'IM3'),
('ID006', 'SmartFren'),
('ID007', 'By U');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `idTransaction` varchar(100) NOT NULL,
  `transactionDate` varchar(100) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`idTransaction`, `transactionDate`, `phoneNumber`) VALUES
('TRX - 1e18a803-c7e9-4fd2-b335-f0132d1c5e9d', 'Sun Feb 21 14:31:24 ICT 2021', '08112233445'),
('TRX - 41366891-d802-4701-ba33-33591145a348', 'Sun Feb 21 14:32:34 ICT 2021', '08112233467'),
('TRX - a23ab664-cec8-40f9-b5e2-4d84dc241b4a', 'Sun Feb 21 14:57:53 ICT 2021', '08888888881'),
('TRX - b0006c0a-f17d-409c-a9c4-38a3ed92b4f3', 'Sat Feb 20 19:50:25 ICT 2021', '08787947569');

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `idTransactionDetail` varchar(100) NOT NULL,
  `idTransaction` varchar(100) NOT NULL,
  `idProduct` varchar(100) NOT NULL,
  `qty` int(100) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`idTransactionDetail`, `idTransaction`, `idProduct`, `qty`, `total`) VALUES
('TRD - 31bdf10b-52a9-4776-9db5-67cf02d5070c', 'TRX - a23ab664-cec8-40f9-b5e2-4d84dc241b4a', '9e2de324-84cd-4f05-9402-e2c6a52ff131', 2, 0),
('TRD - 61f11eea-314f-4c60-b6bb-3c6e589452ca', 'TRX - 41366891-d802-4701-ba33-33591145a348', '9e2de324-84cd-4f05-9402-e2c6a52ff131', 2, 0),
('TRD - 74f4be97-b749-4ea9-927c-74abbc8f016b', 'TRX - b0006c0a-f17d-409c-a9c4-38a3ed92b4f3', 'd666c177-8c44-4704-ac7f-896f005e8b56', 4, 0),
('TRD - e8de85c5-1454-471e-9007-388873237692', 'TRX - 1e18a803-c7e9-4fd2-b335-f0132d1c5e9d', '9e2de324-84cd-4f05-9402-e2c6a52ff131', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `idType` int(10) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`idType`, `type`) VALUES
(1, 'Pulsa'),
(2, 'Paket Data');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idProduct`),
  ADD KEY `FK_ProductType` (`idType`),
  ADD KEY `FK_ProductProvider` (`idProvider`);

--
-- Indexes for table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`idProvider`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idTransaction`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`idTransactionDetail`),
  ADD KEY `FK_TransactionDetailTransaction` (`idTransaction`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`idType`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `idType` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(100) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_ProductProvider` FOREIGN KEY (`idProvider`) REFERENCES `provider` (`idProvider`),
  ADD CONSTRAINT `FK_ProductType` FOREIGN KEY (`idType`) REFERENCES `type` (`idType`);

--
-- Constraints for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD CONSTRAINT `FK_TransactionDetailTransaction` FOREIGN KEY (`idTransaction`) REFERENCES `transaction` (`idTransaction`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
