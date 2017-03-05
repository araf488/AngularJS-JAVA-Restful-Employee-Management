-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hrmanagement
--

CREATE DATABASE IF NOT EXISTS hrmanagement;
USE hrmanagement;

--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `ename` varchar(45) NOT NULL default '',
  `full_name` varchar(100) NOT NULL default '',
  `email` varchar(45) NOT NULL default '',
  `mobile` varchar(45) NOT NULL default '',
  `address` varchar(200) NOT NULL default '',
  `gender` varchar(45) NOT NULL default '',
  `salary` double NOT NULL default '0',
  `hire_date` date NOT NULL default '0000-00-00',
  `rank` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`,`ename`,`full_name`,`email`,`mobile`,`address`,`gender`,`salary`,`hire_date`,`rank`) VALUES 
 (1,'mmh','Md. Mehedi Hasan','mmh@gmail.com','123456789','Arambag, Dhaka','Male',22000,'2011-02-01','Sr. Officer'),
 (2,'msr','Md. Saidul Haque','mshaque@gmail.com','999922258222','Uttara, Dhaka','Male',18000,'2016-06-01','Trainee. Officer'),
 (3,'mms','Sadia Sultana','ssm_sadia@gmail.com','01963524855','Arambag, Dhaka','Female',18000,'2016-08-01','Trainee. Officer'),
 (4,'pph','Md. Tamimul Islam Shaker','tsmShaker@yahoo.com','01711025632','Dhanmondi, Dhaka','Male',35000,'2013-09-01','Manager'),
 (5,'ssh','Syada Humaira Aktar','humaira_225ak@tuatanota.com','01925639874','Motijheel, Dhaka','Female',20000,'2015-02-01','Officer'),
 (6,'mms','Mst. Nahida Muniya','nhi.mun14@gmail.co','01685244586','Uttara, Dhaka','Female',18000,'2016-05-01','Trainee. Officer');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `pname` varchar(100) NOT NULL,
  `model` varchar(45) NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  `p_price` double NOT NULL,
  `s_price` double NOT NULL,
  `p_date` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`,`pname`,`model`,`qty`,`p_price`,`s_price`,`p_date`) VALUES 
 (1,'Apple Mouse','APPM 1025',15,3500,4200,'2017-01-16'),
 (2,'Dell 19.5\'\' Monitor','DDlM-232356M#k',25,5600,6500,'2017-02-09'),
 (3,'A4Tech Combo Keyboard & Mouse','A4#dfd-25896',20,2100,2600,'2016-06-02'),
 (4,'WD Ultra Slim SSD 512GB','WD#USSD512-MF54',50,11500,12800,'2015-09-12'),
 (5,'Intel Core i5 Processor','6th Generation 6100U',15,11500,12800,'2017-01-16'),
 (6,'Intel MotherBoard 6th Gen.','H81 Combo Micromy',25,4800,5500,'2016-12-12'),
 (7,'Asus Laptop','AASD#2589-365GQ',10,31500,34500,'2016-11-09');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `salary`
--

DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `ename` varchar(100) NOT NULL,
  `month` varchar(45) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `salary` double NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` (`id`,`ename`,`month`,`year`,`salary`,`status`) VALUES 
 (1,'Md. Mehedi Hasan','January',2017,22000,'Paid'),
 (2,'Md. Mehedi Hasan','February',2017,22000,'Paid'),
 (3,'Sadia Sultana','February',2017,18000,'Paid'),
 (4,'Md. Tamimul Islam Shaker','February',2017,35000,'Paid'),
 (5,'Md. Mehedi Hasan','March',2017,22000,'Paid'),
 (6,'Md. Tamimul Islam Shaker','March',2017,35000,'Paid');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL default '',
  `pass` varchar(45) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
