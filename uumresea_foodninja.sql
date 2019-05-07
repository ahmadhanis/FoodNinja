-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 06, 2019 at 03:07 PM
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
('4', 'slumberjer@gmail.com', '2', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('2', 'slumberjer@gmail.com', '3', '4.50', 'Laksa Perak', 'paid', '3', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '5', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '94', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('2', 'slumberjer@gmail.com', '1', '4.50', 'Laksa Perak', 'paid', '3', '28032019-OWjohGu'),
('1', 'slumberjer@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '5', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('2', 'slumberjer@gmail.com', '1', '4.50', 'Laksa Perak', 'paid', '3', '28032019-OWjohGu'),
('1', 'slumberjer@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('4', 'slumberjer@gmail.com', '4', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('1', 'slumberjer@gmail.com', '2', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('4', 'slumberjer@gmail.com', '3', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('2', 'slumberjer@gmail.com', '1', '4.50', 'Laksa Perak', 'paid', '3', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '10', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('1', 'slumberjer@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('4', 'slumberjer@gmail.com', '1', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('4', 'slumberjer@gmail.com', '7', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '1', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('1', 'slumberjer@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('4', 'slumberjer@gmail.com', '5', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('1', 'wanzulkarnain69@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '01042019-f1zxscd'),
('1', 'wanzulkarnain69@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '01042019-f1zxscd'),
('1', 'sha@gmail.com', '6', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '05042019-BM8iT1T'),
('4', 'slumberjer@gmail.com', '1', '2.50', 'Laksam', 'paid', '1', '28032019-OWjohGu'),
('3', 'slumberjer@gmail.com', '1', '2.00', 'Roti Canai', 'paid', '2', '28032019-OWjohGu'),
('2', 'slumberjer@gmail.com', '7', '4.50', 'Laksa Perak', 'paid', '3', '28032019-OWjohGu'),
('1', 'koon', '4', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '10042019-p4OppZR'),
('4', 'e@g.com', '1', '2.50', 'Laksam', 'not paid', '1', '13042019-tS1jdWE'),
('1', 'slumberjer@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'paid', '1', '28032019-OWjohGu'),
('4', 'sharania@gmail.com', '1', '2.50', 'Laksam', 'not paid', '1', '01052019-0px2vXJ'),
('1', 'shan123@yahoo.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '04052019-PpJmOHy'),
('1', 'yeongshyhhaw@gmail.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '05052019-NXOFMP9'),
('1', 'shan123@yahoo.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '04052019-PpJmOHy'),
('3', 'shan123@yahoo.com', '3', '2.00', 'Roti Canai', 'not paid', '2', '04052019-PpJmOHy'),
('1', 'shan123@yahoo.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '04052019-PpJmOHy'),
('1', 'shan123@yahoo.com', '1', '5.00', 'Mee Goreng Mamak', 'not paid', '1', '04052019-PpJmOHy');

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
(3, 'Roti Canai', '2.00', '75', '2'),
(4, 'Laksam', '2.50', '3', '1');

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
('28032019-OWjohGu', 'slumberjer', '207.5', '2019-03-28 05:54:37.867905'),
('28032019-OWjohGu', 'slumberjer', '4.5', '2019-03-28 10:13:01.969101'),
('28032019-OWjohGu', 'slumberjer', '28.5', '2019-03-28 14:03:45.265108'),
('28032019-OWjohGu', 'slumberjer', '15.0', '2019-03-28 14:35:36.247092'),
('28032019-OWjohGu', 'slumberjer', '17.5', '2019-03-28 15:13:05.148729'),
('28032019-OWjohGu', 'slumberjer', '24.5', '2019-03-29 15:08:55.505422'),
('28032019-OWjohGu', 'slumberjer', '7.5', '2019-03-30 09:54:48.980538'),
('28032019-OWjohGu', 'slumberjer', '19.5', '2019-03-31 05:24:12.085445'),
('28032019-OWjohGu', 'slumberjer', '22.0', '2019-04-07 06:29:39.300631'),
('28032019-OWjohGu', 'slumberjer', '36.5', '2019-05-05 09:17:07.481107');

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
  `LOCATION` varchar(15) NOT NULL,
  `LATITUDE` varchar(30) NOT NULL,
  `LONGITUDE` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `LOCATION`, `LATITUDE`, `LONGITUDE`) VALUES
('slumberjer@gmail.com', '1b64dad048eda4f2a22621490c0ea7a1db37ad43', '0194702493', 'Ahmad Hanis', 'Changlun', '6.443224050268126', '100.42504772543909');

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
