-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 03, 2019 at 03:13 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `useradmin` varchar(10) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `ttl` varchar(20) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`useradmin`, `nama`, `ttl`, `password`) VALUES
('ADMN51', 'ABD. Qohar Agus Maulana', '31-AGUSTUS-1997', 'jenengku');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `kodebuku` varchar(10) NOT NULL,
  `nama_buku` varchar(50) DEFAULT NULL,
  `jenis_buku` varchar(20) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `bukuke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kodebuku`, `nama_buku`, `jenis_buku`, `harga`, `stok`, `bukuke`) VALUES
('BKU1', 'SIDU', 'Buku Tulis', 2500, 198, 1),
('BKU2', 'Sebuah Seni Untuk Bersikap Bodo Amat', 'Novel', 30000, 199, 2),
('BKU3', 'Konspirasi Alam Semesta', 'Novel', 450000, 99, 3);

-- --------------------------------------------------------

--
-- Table structure for table `detail_struck`
--

CREATE TABLE `detail_struck` (
  `iddetail` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `kodebuku` varchar(10) NOT NULL,
  `kodestruck` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_struck`
--

INSERT INTO `detail_struck` (`iddetail`, `jumlah`, `total_harga`, `kodebuku`, `kodestruck`) VALUES
(50, 2, 5000, 'BKU1', 'STR1'),
(51, 1, 30000, 'BKU2', 'STR1'),
(52, 1, 450000, 'BKU3', 'STR1');

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE `kasir` (
  `userkasir` varchar(10) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `ttl` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `kasirke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kasir`
--

INSERT INTO `kasir` (`userkasir`, `nama`, `ttl`, `password`, `kasirke`) VALUES
('KSR2', 'Lingga Wahtu Rochim', '04/7/2000', 'jenengkudew', 2),
('KSR3', 'ABD. Qohar Agus Maulana', '08/30/1997', 'jenengmu', 3);

-- --------------------------------------------------------

--
-- Table structure for table `struk`
--

CREATE TABLE `struk` (
  `kodestruk` varchar(10) NOT NULL,
  `tanggal` varchar(10) DEFAULT NULL,
  `waktu` varchar(10) DEFAULT NULL,
  `total_bayar` int(11) NOT NULL,
  `uangbayar` int(11) NOT NULL,
  `uangkembali` int(11) NOT NULL,
  `userkasir` varchar(10) NOT NULL,
  `strukke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `struk`
--

INSERT INTO `struk` (`kodestruk`, `tanggal`, `waktu`, `total_bayar`, `uangbayar`, `uangkembali`, `userkasir`, `strukke`) VALUES
('STR1', '12/3/2019', '21:12:25', 485000, 500000, 15000, 'KSR3', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`useradmin`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kodebuku`);

--
-- Indexes for table `detail_struck`
--
ALTER TABLE `detail_struck`
  ADD PRIMARY KEY (`iddetail`),
  ADD KEY `fk_idstruck` (`kodestruck`),
  ADD KEY `fk_kodebuku` (`kodebuku`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`userkasir`);

--
-- Indexes for table `struk`
--
ALTER TABLE `struk`
  ADD PRIMARY KEY (`kodestruk`),
  ADD KEY `fk_userkasir` (`userkasir`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_struck`
--
ALTER TABLE `detail_struck`
  MODIFY `iddetail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_struck`
--
ALTER TABLE `detail_struck`
  ADD CONSTRAINT `fk_idstruck` FOREIGN KEY (`kodestruck`) REFERENCES `struk` (`kodestruk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_kodebuku` FOREIGN KEY (`kodebuku`) REFERENCES `buku` (`kodebuku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `struk`
--
ALTER TABLE `struk`
  ADD CONSTRAINT `fk_userkasir` FOREIGN KEY (`userkasir`) REFERENCES `kasir` (`userkasir`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
