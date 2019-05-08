-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 09, 2019 at 12:06 AM
-- Server version: 10.0.37-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uumresea_foodninja`
--

-- --------------------------------------------------------

--
-- Table structure for table `CART`
--

CREATE TABLE `CART` (
  `FOODID` varchar(10) NOT NULL,
  `USERID` varchar(40) NOT NULL,
  `QUANTITY` varchar(10) NOT NULL,
  `PRICE` varchar(10) NOT NULL,
  `FOODNAME` varchar(30) NOT NULL,
  `STATUS` varchar(20) NOT NULL,
  `RESTID` varchar(10) NOT NULL,
  `ORDERID` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CART`
--

INSERT INTO `CART` (`FOODID`, `USERID`, `QUANTITY`, `PRICE`, `FOODNAME`, `STATUS`, `RESTID`, `ORDERID`) VALUES
('12', '0194702493', '1', '5.50', 'Paneer Masalla', 'paid', '2', '08052019-5R8FLGn'),
('19', '0194702493', '1', '3.50', 'Laksa Johor', 'paid', '3', '08052019-5R8FLGn'),
('16', '0194702493', '1', '5.50', 'Nasi Rendang', 'paid', '1', '08052019-qxgbUmp'),
('11', '0194702493', '1', '4.50', 'Chickpea', 'paid', '5', '08052019-qxgbUmp'),
('12', '0194702493', '1', '5.50', 'Paneer Masalla', 'paid', '2', '08052019-qxgbUmp'),
('10', '0194702493', '1', '5.50', 'Pad Thai Noodle', 'paid', '5', '08052019-Oi6XCFF'),
('12', '0194702493', '1', '5.50', 'Paneer Masalla', 'paid', '2', '08052019-Oi6XCFF'),
('18', '0194702493', '1', '4.50', 'Laksa Lemak', 'paid', '3', '08052019-Oi6XCFF'),
('5', '0194702493', '1', '7.50', 'Pizza Cheese', 'paid', '4', '08052019-Oi6XCFF'),
('7', '0194702493', '1', '7.80', 'Paperdele', 'paid', '4', '08052019-Oi6XCFF'),
('9', '0194702493', '1', '6.50', 'Kungpao Chicken', 'paid', '5', '08052019-Oi6XCFF');

-- --------------------------------------------------------

--
-- Table structure for table `FOODS`
--

CREATE TABLE `FOODS` (
  `FOODID` int(5) NOT NULL,
  `FOODNAME` varchar(50) NOT NULL,
  `FOODPRICE` varchar(5) NOT NULL,
  `QUANTITY` varchar(5) NOT NULL,
  `RESTID` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FOODS`
--

INSERT INTO `FOODS` (`FOODID`, `FOODNAME`, `FOODPRICE`, `QUANTITY`, `RESTID`) VALUES
(1, 'Mee Goreng Mamak', '5.00', '18', '1'),
(2, 'Laksa Perak', '4.50', '7', '3'),
(3, 'Roti Canai', '2.00', '74', '2'),
(4, 'Laksam', '2.50', '3', '1'),
(5, 'Pizza Cheese', '7.50', '29', '4'),
(6, 'Bolognese Sausage', '5.50', '28', '4'),
(7, 'Paperdele', '7.80', '48', '4'),
(8, 'Polenta', '10.50', '32', '4'),
(9, 'Kungpao Chicken', '6.50', '28', '5'),
(10, 'Pad Thai Noodle', '5.50', '39', '5'),
(11, 'Chickpea', '4.50', '17', '5'),
(12, 'Paneer Masalla', '5.50', '26', '2'),
(13, 'Kheema Samosa', '4.50', '40', '2'),
(14, 'Chicken Sagwala', '4.50', '40', '2'),
(15, 'Nasi Goreng Biasa', '4.50', '29', '1'),
(16, 'Nasi Rendang', '5.50', '29', '1'),
(17, 'Nasi Lemak Sotong', '5.50', '50', '1'),
(18, 'Laksa Lemak', '4.50', '38', '3'),
(19, 'Laksa Johor', '3.50', '19', '3'),
(20, 'Laksa Penang', '5.50', '30', '3');

-- --------------------------------------------------------

--
-- Table structure for table `ORDERED`
--

CREATE TABLE `ORDERED` (
  `ORDERID` varchar(20) NOT NULL,
  `USERID` varchar(10) NOT NULL,
  `TOTAL` varchar(10) NOT NULL,
  `DATE` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ORDERED`
--

INSERT INTO `ORDERED` (`ORDERID`, `USERID`, `TOTAL`, `DATE`) VALUES
('08052019-qxgbUmp', '0194702493', '15.5', '2019-05-08 15:22:51.247162'),
('08052019-Oi6XCFF', '0194702493', '37.3', '2019-05-08 16:05:18.157234');

-- --------------------------------------------------------

--
-- Table structure for table `RESTAURANT`
--

CREATE TABLE `RESTAURANT` (
  `RESTID` int(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `LOCATION` varchar(20) NOT NULL,
  `LATITUDE` varchar(30) NOT NULL,
  `LONGITUDE` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RESTAURANT`
--

INSERT INTO `RESTAURANT` (`RESTID`, `NAME`, `PHONE`, `ADDRESS`, `LOCATION`, `LATITUDE`, `LONGITUDE`) VALUES
(1, 'Buluh Madu', '01934558488', 'Jalan Changlun', 'Changlun', '6.433105', '100.436619'),
(2, 'Makcik Kari', '0156766456', 'Jalan Sintok', 'Sintok', '6.447736', '100.524096'),
(3, 'Laksa Changlun', '01944674445', 'Pekan Changlun', 'Changlun', '6.435130', '100.430111'),
(4, 'Mama', '0426665443', 'Mualamat, UUM', 'Sintok', '6.477651', '100.509161'),
(5, 'Makcik Felda', '0194443333', 'Jalan Felda', 'Sintok', '6.493133', '100.478162');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `EMAIL` varchar(100) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `LOCATION` varchar(15) NOT NULL,
  `LATITUDE` varchar(30) NOT NULL,
  `LONGITUDE` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `LOCATION`, `LATITUDE`, `LONGITUDE`) VALUES
('slumberjer@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '0194702493', 'Ahmad Hanis', 'Changlun', '6.443224050268126', '100.42504772543909');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `FOODS`
--
ALTER TABLE `FOODS`
  ADD PRIMARY KEY (`FOODID`);

--
-- Indexes for table `RESTAURANT`
--
ALTER TABLE `RESTAURANT`
  ADD PRIMARY KEY (`RESTID`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`EMAIL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `FOODS`
--
ALTER TABLE `FOODS`
  MODIFY `FOODID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `RESTAURANT`
--
ALTER TABLE `RESTAURANT`
  MODIFY `RESTID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
