/*
SQLyog Ultimate v11.22 (32 bit)
MySQL - 5.5.15 : Database - mobilesys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mobilesys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mobilesys`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(50) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `is_account_enabled` bit(1) NOT NULL,
  `is_system` bit(1) NOT NULL,
  `loginDate` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`createDate`,`email`,`is_account_enabled`,`is_system`,`loginDate`,`name`,`password`,`username`) values ('4028477650eb1d260150eb2216b00001','2015-11-09 15:24:51','','','\0','2015-11-09 15:24:51','测试','96e79218965eb72c92a549dd5a330112','111111'),('ff8081813b93049c013b932553810003','2014-07-03 11:50:00','','','','2014-07-08 12:57:43','系统管理员','21232f297a57a5a743894a0e4a801fc3','admin');

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `admin_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`),
  KEY `FK2902EF66F2FA3162` (`role_id`),
  KEY `FK2902EF664172CCF2` (`admin_id`),
  CONSTRAINT `FK2902EF664172CCF2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  CONSTRAINT `FK2902EF66F2FA3162` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_role` */

insert  into `admin_role`(`admin_id`,`role_id`) values ('ff8081813b93049c013b932553810003','0731dcsoft2010031200000000000016'),('4028477650eb1d260150eb2216b00001','0731dcsoft2010031200000000000017');

/*Table structure for table `phoneno` */

DROP TABLE IF EXISTS `phoneno`;

CREATE TABLE `phoneno` (
  `id` varchar(50) NOT NULL,
  `phoneno` varchar(11) NOT NULL,
  `phoneName` varchar(20) DEFAULT NULL,
  `phoneNoStatu` varchar(1) DEFAULT NULL,
  `aid` varchar(50) DEFAULT NULL,
  `statu` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `aid` (`aid`),
  CONSTRAINT `phoneno_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `phoneno` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(50) NOT NULL,
  `authority_list` text NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`authority_list`,`description`,`is_system`,`name`) values ('0731dcsoft2010031200000000000016','[\"ROLE_ADMIN\",\"ROLE_BASE\"]','拥有后台管理最高权限','','管理员'),('0731dcsoft2010031200000000000017','[\"ROLE_OPERATION\",\"ROLE_BASE\"]','手机业务员','','业务员'),('0731dcsoft2010031200000000000018','[\"ROLE_MAINTION\",\"ROLE_BASE\"]','手机维护员','','维护员');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
