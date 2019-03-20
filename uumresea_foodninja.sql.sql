-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 20, 2019 at 09:43 PM
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
  `ORDERID` int(10) NOT NULL,
  `FOODID` varchar(10) NOT NULL,
  `USERID` varchar(40) NOT NULL,
  `QUANTITY` varchar(10) NOT NULL,
  `PRICE` varchar(10) NOT NULL,
  `FOODNAME` varchar(30) NOT NULL,
  `STATUS` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CART`
--

INSERT INTO `CART` (`ORDERID`, `FOODID`, `USERID`, `QUANTITY`, `PRICE`, `FOODNAME`, `STATUS`) VALUES
(1, '2', 'slumberjer@gmail.com', '10', '4.50', 'Mee Goreng Mamak', 'not complete');

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
(1, 'Mee Goreng Mamak', '5.00', '50', '1'),
(2, 'Laksa Perak', '4.50', '20', '3'),
(3, 'Roti Canai', '2.00', '100', '2'),
(4, 'Laksam', '2.50', '30', '1');

-- --------------------------------------------------------

--
-- Table structure for table `RESTAURANT`
--

CREATE TABLE `RESTAURANT` (
  `RESTID` int(10) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `LOCATION` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RESTAURANT`
--

INSERT INTO `RESTAURANT` (`RESTID`, `NAME`, `PHONE`, `ADDRESS`, `LOCATION`) VALUES
(1, 'Buluh Madu', '01934558488', 'Jalan Changlun', 'Changlun'),
(2, 'Makcik Kari', '0156766456', 'Jalan Sintok', 'Sintok'),
(3, 'Laksa Changlun', '01944674445', 'Pekan Changlun', 'Changlun');

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `EMAIL` varchar(100) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `LOCATION` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `LOCATION`) VALUES
('abc@gmail.com', '5975676ae179641188b2bde3c8d545d8334991f6', '0194702439', 'Ahmad Hanis', 'Changlun'),
('abcd@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '01234545959', 'John', 'Changlun'),
('slumberjer@gmail.com', '1b64dad048eda4f2a22621490c0ea7a1db37ad43', '0194702493', 'Hanis', 'All'),
('ahmad@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', '01934455765', 'Ahmad', 'All'),
('qq1819301928@gmail.com', '0c8134c9a330eac5a89c4f18bcfe77e4780be309', '01135747336', 'Yang', 'Sintok');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CART`
--
ALTER TABLE `CART`
  ADD PRIMARY KEY (`ORDERID`);

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
-- AUTO_INCREMENT for table `CART`
--
ALTER TABLE `CART`
  MODIFY `ORDERID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `FOODS`
--
ALTER TABLE `FOODS`
  MODIFY `FOODID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `RESTAURANT`
--
ALTER TABLE `RESTAURANT`
  MODIFY `RESTID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
