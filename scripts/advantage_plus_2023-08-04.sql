# ************************************************************
# Antares - SQL Client
# Version 0.7.0
# 
# https://antares-sql.app/
# https://github.com/antares-sql/antares
# 
# Host: localhost (MySQL Community Server  5.7.14)
# Database: advantage_plus
# Generation time: 2023-08-04T14:53:17+00:00
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table audit_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `audit_log`;

CREATE TABLE `audit_log` (
  `record_id` varchar(255) NOT NULL,
  `estate_zone` varchar(50) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(25) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `ip_address` varchar(100) DEFAULT NULL,
  `last_modified_by` varchar(25) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `ui` varchar(100) DEFAULT NULL,
  `user_activity` varchar(255) DEFAULT NULL,
  `user_role` varchar(25) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_audit_log_region` (`region`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `audit_log` WRITE;
/*!40000 ALTER TABLE `audit_log` DISABLE KEYS */;

INSERT INTO `audit_log` (`record_id`, `estate_zone`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `full_name`, `ip_address`, `last_modified_by`, `last_modified_date`, `ui`, `user_activity`, `user_role`, `username`, `region`) VALUES
	("011184d65eeb41c48e8b15be5cce852d/Sat29Jul20231003AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:03:18", 0, NULL, NULL, "Dauda Ainoo", "80.217.52.218", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("01cb3af662184998b725e24ffa267d33/Mon31Jul2023904PM", "KUMASI_ZONE", "dainoo", "2023-07-31 21:04:58", 0, NULL, NULL, "Dauda Ainoo", "144.56.218.81", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("02feb2be13a24d9f8556414c08818ee8/Sat29Jul2023950PM", "KUMASI_ZONE", "dainoo", "2023-07-29 21:50:57", 0, NULL, NULL, "Dauda Ainoo", "236.0.248.61", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("037e8b8d23014e999e27eeddf89a88fe/Wed2Aug2023331AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:31:59", 0, NULL, NULL, "Dauda Ainoo", "61.95.170.208", NULL, NULL, "SETTINGS", "Updated C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("042f19cc81ba42f99c8f68e9e043c7de/Mon31Jul2023703AM", "KUMASI_ZONE", "dainoo", "2023-07-31 07:03:06", 0, NULL, NULL, "Dauda Ainoo", "199.126.212.138", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("062e074752fe4f30874ead6add13704e/Tue1Aug2023233AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:33:16", 0, NULL, NULL, "Dauda Ainoo", "75.1.66.23", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0661c6fa79a143e18413835b9351840a/Tue1Aug2023927AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:27:19", 0, NULL, NULL, "Dauda Ainoo", "120.140.76.51", NULL, NULL, "SETTINGS", "created block C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("073dcd9687284391bc322bcfdb6e5bde/Tue1Aug2023152AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:52:06", 0, NULL, NULL, "Dauda Ainoo", "184.15.204.192", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("07b4256641804e2587fb5e3d001de915/Thu3Aug2023539PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:39:54", 0, NULL, NULL, "Dauda Ainoo", "202.9.68.68", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0859fa1cfdb8492982812146a0b00538/Sat29Jul20231004AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:04:43", 0, NULL, NULL, "Dauda Ainoo", "253.227.175.33", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("091bb91de9344267a500eb6c1d88d4fb/Tue1Aug2023746AM", "KUMASI_ZONE", "dainoo", "2023-08-01 07:46:04", 0, NULL, NULL, "Dauda Ainoo", "239.77.24.140", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0bc1d654ae7a4c55bb13134c76fc2a90/Sat29Jul20231027AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:27:25", 0, NULL, NULL, "Dauda Ainoo", "115.154.236.77", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0c578425a5204035806ae24b8d189f6b/Sun30Jul2023152PM", "KUMASI_ZONE", "dainoo", "2023-07-30 13:52:20", 0, NULL, NULL, "Dauda Ainoo", "242.225.76.246", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0cca660a22d84c3c96c896ab50f5067d/Sat29Jul20231001AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:01:27", 0, NULL, NULL, "Dauda Ainoo", "245.173.46.201", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0ea79e8f201a4e96bf6715a869d361b1/Mon31Jul2023604AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:04:07", 0, NULL, NULL, "Dauda Ainoo", "23.16.166.79", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("0ee9165d59cf4bcaaaad9eefd16ccbe5/Mon31Jul2023556AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:56:02", 0, NULL, NULL, "Dauda Ainoo", "29.170.191.243", NULL, NULL, "SETTINGS", "created estate Rigel Moreno", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("101024fc8e7d41b49377f92fa97ebde7/Tue25Jul2023725AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:25:19", 0, NULL, NULL, "Dauda Ainoo", "16.227.249.167", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("10215df72c614891b49a0206689badc3/Tue1Aug2023225AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:25:10", 0, NULL, NULL, "Dauda Ainoo", "129.251.92.6", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("10d8ec520af442cd9c3d86a1cb324e5e/Tue1Aug2023253AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:53:18", 0, NULL, NULL, "Dauda Ainoo", "114.21.217.81", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("111b6812f24d4d7da031e9ed3c43105c/Sat29Jul20231019AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:19:57", 0, NULL, NULL, "Dauda Ainoo", "246.155.135.122", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("119e48c5b0b043689bab547abf332adf/Mon31Jul2023525AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:25:05", 0, NULL, NULL, "Dauda Ainoo", "87.53.216.114", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("11a671be99544a5da0429a14575e0438/Wed26Jul2023512AM", "KUMASI_ZONE", "dainoo", "2023-07-26 05:12:31", 0, NULL, NULL, "Dauda Ainoo", "125.249.74.198", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1449bb1fb27049ab8b47dddeae42531b/Tue1Aug2023245AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:45:40", 0, NULL, NULL, "Dauda Ainoo", "198.112.26.68", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("14ea0b54ac774b51b8b11353731230ea/Tue1Aug2023252AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:52:58", 0, NULL, NULL, "Dauda Ainoo", "119.14.117.129", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("151ac2e36ef44a4ca3445f1a8faf07d0/Sat29Jul20231159AM", "KUMASI_ZONE", "dainoo", "2023-07-29 11:59:41", 0, NULL, NULL, "Dauda Ainoo", "135.251.166.217", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("153454e77ae64576a4095e137f29a20f/Tue1Aug2023255AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:55:57", 0, NULL, NULL, "Dauda Ainoo", "61.240.109.131", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1837c06543da4132a191eff05be10467/Tue1Aug2023247AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:47:25", 0, NULL, NULL, "Dauda Ainoo", "201.73.132.152", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("18957428fb184a26987c09a29c5a0276/Sat29Jul20231031AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:31:13", 0, NULL, NULL, "Dauda Ainoo", "94.87.52.202", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1ae51afbc81e4b78912b1ae452fb8927/Sat29Jul20231031AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:31:21", 0, NULL, NULL, "Dauda Ainoo", "20.212.70.133", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1b1b4b914ae34779bf612bc2df36f4f4/Sat29Jul20231003AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:03:03", 0, NULL, NULL, "Dauda Ainoo", "139.122.175.206", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1beee8d9de4e4f20a8aa04346fa89697/Mon31Jul2023512AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:12:11", 0, NULL, NULL, "Dauda Ainoo", "161.181.83.165", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1c92b50ec743416e801e4231f44df810/Tue1Aug2023930AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:30:24", 0, NULL, NULL, "Dauda Ainoo", "58.89.72.93", NULL, NULL, "SETTINGS", "created block C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1d60802eaade4d1298a55c1afa0bb028/Thu3Aug2023900AM", "KUMASI_ZONE", "dainoo", "2023-08-03 09:00:44", 0, NULL, NULL, "Dauda Ainoo", "100.93.100.85", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1d609f0d5ee34f62b5832fb1948070f3/Fri4Aug20231032AM", "KUMASI_ZONE", "dainoo", "2023-08-04 10:32:11", 0, NULL, NULL, "Dauda Ainoo", "229.201.77.115", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1d8a5300152147e2a1b94abc41e642c4/Tue1Aug2023135AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:35:19", 0, NULL, NULL, "Dauda Ainoo", "69.167.15.56", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1df27407c3d24572b13a34f406aa90c9/Tue1Aug2023714AM", "KUMASI_ZONE", "dainoo", "2023-08-01 07:14:09", 0, NULL, NULL, "Dauda Ainoo", "61.65.219.234", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1e2097ab3e2a421a9e4ac32eb510520f/Sat29Jul20231018AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:18:09", 0, NULL, NULL, "Dauda Ainoo", "78.147.193.217", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1e896bd2b31c4ed7b30137ca97e5d16a/Tue1Aug2023251AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:51:07", 0, NULL, NULL, "Dauda Ainoo", "88.10.66.15", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1ef92a4a1c1643d3bb44aaad7d1e4871/Mon31Jul2023552AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:52:38", 0, NULL, NULL, "Dauda Ainoo", "167.61.29.85", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("1f2c9fc3c42f45b1ad9abcd03876f3cc/Wed2Aug2023341AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:41:40", 0, NULL, NULL, "Dauda Ainoo", "86.129.102.66", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("20e3b3e6261749bdbae369a559571625/Fri4Aug2023956AM", "KUMASI_ZONE", "dainoo", "2023-08-04 09:56:57", 0, NULL, NULL, "Dauda Ainoo", "108.80.252.171", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("22ba01d4e09042f28273c7667098d806/Sat29Jul2023947AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:47:15", 0, NULL, NULL, "Dauda Ainoo", "58.244.83.55", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("22bc6d23fe3f4f3d856fde84a5c59f5b/Sat29Jul20231018AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:18:46", 0, NULL, NULL, "Dauda Ainoo", "209.23.54.17", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("22d52015295643298aa4321cd6a9a4ad/Mon31Jul2023837AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:37:33", 0, NULL, NULL, "Dauda Ainoo", "105.169.210.209", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("22fb60dbfd094c958232624bec8afc04/Sat29Jul20231006AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:06:18", 0, NULL, NULL, "Dauda Ainoo", "15.38.81.16", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("239e5d90ca4f42ff85ae4d8e6e7617fa/Sat29Jul20231001AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:01:00", 0, NULL, NULL, "Dauda Ainoo", "230.228.53.24", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("25b5df89fb474bd79e6124fc281f50fe/Mon31Jul2023849AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:49:49", 0, NULL, NULL, "Dauda Ainoo", "171.202.133.192", NULL, NULL, "SETTINGS", "Updated SOUTH EFFIAKUMA", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("2af46613ec1240f2953e1ccff87a23f9/Mon31Jul2023707AM", "KUMASI_ZONE", "dainoo", "2023-07-31 07:07:11", 0, NULL, NULL, "Dauda Ainoo", "36.146.14.40", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("2e4f4ff2acc74195b89d17597606bdf5/Thu3Aug20231012PM", "KUMASI_ZONE", "dainoo", "2023-08-03 22:12:19", 0, NULL, NULL, "Dauda Ainoo", "27.29.151.43", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("2e7a76873e30453da8716cb85c6bf674/Sat29Jul20231026AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:26:44", 0, NULL, NULL, "Dauda Ainoo", "35.176.150.41", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("2edf546e9df84fd28ededfea1e38c8dc/Thu3Aug2023556PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:56:30", 0, NULL, NULL, "Dauda Ainoo", "93.142.209.127", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("2f65d595acf94e7fa95d3d2e53a65491/Fri4Aug2023956AM", "KUMASI_ZONE", "dainoo", "2023-08-04 09:56:12", 0, NULL, NULL, "Dauda Ainoo", "170.72.101.70", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("31778e652a0c4fb9bfec20d2fa6c9bc4/Tue1Aug2023250AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:50:34", 0, NULL, NULL, "Dauda Ainoo", "116.88.99.69", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("33744bf410c845ca92e90a38c8c42d8c/Tue1Aug2023234AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:34:53", 0, NULL, NULL, "Dauda Ainoo", "246.157.14.132", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("33afcacf6af54a22b137b9366ccd88da/Mon31Jul2023847AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:47:45", 0, NULL, NULL, "Dauda Ainoo", "19.217.10.58", NULL, NULL, "SETTINGS", "created estate SOUTH KWEIKUMA", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("33f70df9b3fb48eda13aab68fa4842d5/Mon31Jul2023605AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:05:17", 0, NULL, NULL, "Dauda Ainoo", "148.59.124.131", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("343b60d4408142c7a38c3002b17ea5af/Tue1Aug2023214AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:14:19", 0, NULL, NULL, "Dauda Ainoo", "100.42.102.121", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3502395ccfeb43a7882550b2febbf261/Fri4Aug20231210PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:10:50", 0, NULL, NULL, "Dauda Ainoo", "15.234.46.53", NULL, NULL, "SETTINGS", "created estate Macaulay Solomon", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("360e6ca52523455e96a28a0d640edc64/Sat29Jul20231030AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:30:42", 0, NULL, NULL, "Dauda Ainoo", "37.229.39.133", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("36a64d28b843457a827c9aadfaa9e44b/Tue25Jul2023730AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:30:03", 0, NULL, NULL, "Dauda Ainoo", "33.35.48.157", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("37c5fa7e2a8d493ab5645386ec8cfb30/Tue1Aug2023248AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:48:33", 0, NULL, NULL, "Dauda Ainoo", "132.227.66.149", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3817c3944e0f40fb9a12d21cb706a1dc/Sat29Jul2023946PM", "KUMASI_ZONE", "dainoo", "2023-07-29 21:46:36", 0, NULL, NULL, "Dauda Ainoo", "14.122.134.166", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3a468a99740b49de889d1843f147c82a/Wed26Jul2023537AM", "KUMASI_ZONE", "dainoo", "2023-07-26 05:37:49", 0, NULL, NULL, "Dauda Ainoo", "53.6.23.55", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3ab52e6386ac4e0da5b2045696ea0267/Wed26Jul2023530AM", "KUMASI_ZONE", "dainoo", "2023-07-26 05:30:52", 0, NULL, NULL, "Dauda Ainoo", "236.205.173.29", NULL, NULL, "USER_ACCOUNT_LIST", "Updated Suki Murray", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3ae5c63bdc1f484fb216a6730777cc4a/Mon31Jul2023555AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:55:11", 0, NULL, NULL, "Dauda Ainoo", "196.174.88.233", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3b6a6f4f55ae413b8e238b0bdd79186b/Thu3Aug2023627PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:27:44", 0, NULL, NULL, "Dauda Ainoo", "178.130.187.240", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3ce310c905334397a77d2efd25348344/Mon31Jul2023535AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:35:49", 0, NULL, NULL, "Dauda Ainoo", "49.83.66.137", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("3da81cc89dab47f4877cf4d630756008/Tue1Aug2023904AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:04:16", 0, NULL, NULL, "Dauda Ainoo", "105.150.93.200", NULL, NULL, "SETTINGS", "created block C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("40a324013aa14d2699c62fbb00962dd4/Tue1Aug2023925AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:25:48", 0, NULL, NULL, "Dauda Ainoo", "119.107.64.27", NULL, NULL, "SETTINGS", "created block A", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("415d0aa1c5d94185af3afa960ad7fb97/Tue1Aug2023152AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:52:20", 0, NULL, NULL, "Dauda Ainoo", "147.241.145.129", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4502f98c299d448ebb1b48c77dd94b84/Mon31Jul2023555AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:55:09", 0, NULL, NULL, "Dauda Ainoo", "177.205.209.10", NULL, NULL, "SETTINGS", "created estate Jonah Graves", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("45effc1f45704c8eb957c7ee556c4dc5/Fri4Aug20231213PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:13:28", 0, NULL, NULL, "Dauda Ainoo", "48.247.151.224", NULL, NULL, "SETTINGS", "created estate Sharon Dillard", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4affe5262d7c411b98b0976977bbc018/Tue1Aug2023222AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:22:33", 0, NULL, NULL, "Dauda Ainoo", "31.226.171.6", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4ce5468f5e104a858c6349a062bcd7a0/Tue1Aug2023239AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:39:25", 0, NULL, NULL, "Dauda Ainoo", "113.156.62.205", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4e6a07554b5241c7bbe3133b4418a4c6/Tue1Aug2023238AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:38:33", 0, NULL, NULL, "Dauda Ainoo", "98.94.147.236", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4e99b485a5ff4d42bd8dd23f5e67ad09/Fri4Aug20231258PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:58:12", 0, NULL, NULL, "Dauda Ainoo", "252.192.166.222", NULL, NULL, "SETTINGS", "Updated Kenyon Marquez", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4eb27a3b0fe94dd2b52b3ba9524d66d9/Tue1Aug2023232AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:32:32", 0, NULL, NULL, "Dauda Ainoo", "197.235.37.171", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("4ebb152870a041cba9cec2213061547f/Tue1Aug2023223AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:23:54", 0, NULL, NULL, "Dauda Ainoo", "12.117.112.159", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("51063f2a66b14e94b73c856e7a6eb9bd/Thu3Aug2023936PM", "KUMASI_ZONE", "dainoo", "2023-08-03 21:36:18", 0, NULL, NULL, "Dauda Ainoo", "94.146.127.200", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("51c0e490da094eb3bfae693923569e5e/Sat29Jul2023948AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:48:16", 0, NULL, NULL, "Dauda Ainoo", "54.208.108.104", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("533c68581dd14566b8e084324fb415c0/Tue1Aug2023144AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:44:37", 0, NULL, NULL, "Dauda Ainoo", "155.222.75.190", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("55bc999dc2c649c58725ec13128f0fa7/Tue1Aug2023256AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:56:32", 0, NULL, NULL, "Dauda Ainoo", "26.218.137.70", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("563e4ae5636841faa5e2336345c3428b/Tue1Aug2023219AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:19:18", 0, NULL, NULL, "Dauda Ainoo", "188.180.49.250", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("56827ee14f664d36a120286a14800bb4/Sat29Jul2023945PM", "KUMASI_ZONE", "dainoo", "2023-07-29 21:45:42", 0, NULL, NULL, "Dauda Ainoo", "104.205.228.166", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("56a466fb5b3b403f846de303464d418c/Tue1Aug2023135AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:35:23", 0, NULL, NULL, "Dauda Ainoo", "176.45.128.41", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("57202f1bfdff4651a70501194b4c59fd/Tue1Aug2023243AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:43:07", 0, NULL, NULL, "Dauda Ainoo", "75.244.54.170", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("57a9d55208204d948f1b850f2365f39d/Wed2Aug2023342AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:42:45", 0, NULL, NULL, "Dauda Ainoo", "143.51.81.75", NULL, NULL, "SETTINGS", "Updated A", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("57b7a9df2360484c97f97feb87407ff7/Fri4Aug20231144AM", "KUMASI_ZONE", "dainoo", "2023-08-04 11:44:19", 0, NULL, NULL, "Dauda Ainoo", "184.152.73.30", NULL, NULL, "SETTINGS", "created estate Jerome Travis", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("580fcaeca6b643cebe44ad045359af09/Tue1Aug2023653AM", "KUMASI_ZONE", "dainoo", "2023-08-01 06:53:59", 0, NULL, NULL, "Dauda Ainoo", "1.45.94.185", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("5a33e04b7997408aacec506f82989474/Sat29Jul20231024AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:24:52", 0, NULL, NULL, "Dauda Ainoo", "176.41.147.233", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("5bbc24044ba94b829e193c54339c8f2a/Tue1Aug2023215AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:15:06", 0, NULL, NULL, "Dauda Ainoo", "188.214.106.109", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("5bcee29c9abd42c9898dfef4499c3b87/Mon31Jul2023708AM", "KUMASI_ZONE", "dainoo", "2023-07-31 07:08:51", 0, NULL, NULL, "Dauda Ainoo", "81.49.184.2", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("5f9871241aea43c79420e7c8c2b1b9c1/Mon31Jul2023906PM", "KUMASI_ZONE", "dainoo", "2023-07-31 21:06:17", 0, NULL, NULL, "Dauda Ainoo", "192.236.128.42", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6295ee2a4b7c4180838db0d378c0c057/Mon31Jul2023554AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:54:53", 0, NULL, NULL, "Dauda Ainoo", "214.119.197.43", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("62c4fbed20324870b6a9f0e7d9ad9b12/Tue1Aug2023842AM", "KUMASI_ZONE", "dainoo", "2023-08-01 08:42:21", 0, NULL, NULL, "Dauda Ainoo", "98.134.140.199", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("63ae6ed4bf78420882c8f640816e73c3/Mon31Jul2023834AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:34:01", 0, NULL, NULL, "Dauda Ainoo", "189.140.28.210", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6428a2fc42454f8bad0538abdb771169/Mon31Jul2023606AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:06:22", 0, NULL, NULL, "Dauda Ainoo", "225.112.190.218", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("64d34c933c9f40929f1c694f5a43db0f/Tue1Aug2023928AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:28:06", 0, NULL, NULL, "Dauda Ainoo", "20.81.13.176", NULL, NULL, "SETTINGS", "created block A", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("656a233e313641a9b7f3d4156fe32364/Tue1Aug2023152AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:52:23", 0, NULL, NULL, "Dauda Ainoo", "208.72.104.96", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("658395721c824b5b81356111eef9c686/Mon31Jul2023528AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:28:36", 0, NULL, NULL, "Dauda Ainoo", "251.51.246.252", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6788223b85b543c7aa722b5f221ed2c3/Tue1Aug2023714AM", "KUMASI_ZONE", "dainoo", "2023-08-01 07:14:05", 0, NULL, NULL, "Dauda Ainoo", "83.44.140.85", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("67ae81967cb049da8fa2f87893b6fb3a/Tue1Aug2023207AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:07:24", 0, NULL, NULL, "Dauda Ainoo", "90.246.104.177", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6939378b6dd44911a2b752e2eece1e01/Sat29Jul20231159AM", "KUMASI_ZONE", "dainoo", "2023-07-29 11:59:38", 0, NULL, NULL, "Dauda Ainoo", "228.206.87.210", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("69ae53384fa94e21936b3332997e07c9/Mon31Jul2023610AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:10:21", 0, NULL, NULL, "Dauda Ainoo", "103.248.160.97", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6a4d183df4464b15a62c358e9916c67e/Tue25Jul20231000PM", "KUMASI_ZONE", "dainoo", "2023-07-25 22:00:29", 0, NULL, NULL, "Dauda Ainoo", "252.97.250.206", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6b3a3ab080d74eb39ba8db1f566a13f8/Tue25Jul2023717AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:17:20", 0, NULL, NULL, "Dauda Ainoo", "102.101.48.56", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6bc289a532914ebe8e56fc6c009b185a/Sat29Jul20231000AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:00:21", 0, NULL, NULL, "Dauda Ainoo", "122.203.186.226", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6c8792afe2834d87aa92ee45a394caff/Mon31Jul2023527AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:27:56", 0, NULL, NULL, "Dauda Ainoo", "36.108.148.188", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6ea58915779d4c938b6144512d991703/Tue1Aug2023251AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:51:22", 0, NULL, NULL, "Dauda Ainoo", "227.145.179.151", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6ef44ab01ffb46eeb0b8ba7affbd7016/Fri4Aug20231148AM", "KUMASI_ZONE", "dainoo", "2023-08-04 11:48:52", 0, NULL, NULL, "Dauda Ainoo", "245.190.248.31", NULL, NULL, "SETTINGS", "created estate Belle Farley", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("6f34ffc76c6e4941bfceec13dd7ca1e7/Mon31Jul2023708AM", "KUMASI_ZONE", "dainoo", "2023-07-31 07:08:54", 0, NULL, NULL, "Dauda Ainoo", "247.49.154.108", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7146debb67494a40a15a98d6772d4cdf/Thu3Aug2023641PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:41:22", 0, NULL, NULL, "Dauda Ainoo", "142.175.187.82", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("71a4e89bc9a440578076bf16c7cbfacf/Thu3Aug2023517PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:17:40", 0, NULL, NULL, "Dauda Ainoo", "3.137.239.19", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("73dea3776e914e228607998e5260d4af/Sat29Jul2023958AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:58:03", 0, NULL, NULL, "Dauda Ainoo", "201.140.71.31", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("75e42a8517ff4b45ac11c116fbd99d8f/Sun30Jul2023152PM", "KUMASI_ZONE", "dainoo", "2023-07-30 13:52:17", 0, NULL, NULL, "Dauda Ainoo", "53.88.2.211", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7680fa0d4c444caca6c853313b828b99/Sat29Jul20231031AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:31:50", 0, NULL, NULL, "Dauda Ainoo", "42.253.204.134", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7701118334b242639db2519379bfa9ae/Wed2Aug2023341AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:41:52", 0, NULL, NULL, "Dauda Ainoo", "68.239.111.67", NULL, NULL, "SETTINGS", "Updated C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("771cdb512a6d4c7fa4f7a21d0a236804/Tue1Aug2023926AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:26:29", 0, NULL, NULL, "Dauda Ainoo", "164.103.180.39", NULL, NULL, "SETTINGS", "created block B", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("77dca3da974e4a95b4f803108017ca84/Sun30Jul20231107PM", "KUMASI_ZONE", "dainoo", "2023-07-30 23:07:58", 0, NULL, NULL, "Dauda Ainoo", "83.198.152.3", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("787b0b116eec4eb38d6970081d991b2c/Tue1Aug2023220AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:20:11", 0, NULL, NULL, "Dauda Ainoo", "27.29.252.226", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("79228f21a4914810861b4a640e5162f9/Tue1Aug2023233AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:34:00", 0, NULL, NULL, "Dauda Ainoo", "63.178.189.148", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7980df7611bb4fe0b904746fe1f654b0/Tue1Aug2023220AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:20:56", 0, NULL, NULL, "Dauda Ainoo", "35.242.225.185", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7987ae5cfcb24fc39c59964cd3df0476/Tue1Aug2023253AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:53:45", 0, NULL, NULL, "Dauda Ainoo", "197.142.103.18", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7a7a639e544e4d6cb16669b9eefd3a38/Tue25Jul2023717AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:17:34", 0, NULL, NULL, "Dauda Ainoo", "109.97.81.192", NULL, NULL, "SETTINGS", "Updated Simone Maldonado", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7a805ccb1ad542b58d4bba88dddd4c7f/Fri4Aug20231114AM", "KUMASI_ZONE", "dainoo", "2023-08-04 11:14:32", 0, NULL, NULL, "Dauda Ainoo", "112.252.114.142", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7ac0b64005c441fb85ade1c10930b6e2/Mon31Jul2023837AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:37:10", 0, NULL, NULL, "Dauda Ainoo", "76.126.56.17", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7bdffed411d2416a96037dfb94da1498/Tue25Jul20231000PM", "KUMASI_ZONE", "dainoo", "2023-07-25 22:00:20", 0, NULL, NULL, "Dauda Ainoo", "90.4.89.181", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7df11c3870fc4498a8b53a5756b0720c/Mon31Jul2023808AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:08:49", 0, NULL, NULL, "Dauda Ainoo", "181.21.60.166", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7e3b5daa214249388178d79f4f892acb/Mon31Jul2023849AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:49:11", 0, NULL, NULL, "Dauda Ainoo", "137.61.1.181", NULL, NULL, "SETTINGS", "created estate ADIEMBRA", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("7ee1588475174d6185970b4b69a93bda/Fri4Aug2023123PM", "KUMASI_ZONE", "dainoo", "2023-08-04 13:23:51", 0, NULL, NULL, "Dauda Ainoo", "115.227.230.172", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("80226aaf56434317b6bb30d5882e5539/Thu3Aug2023537PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:37:45", 0, NULL, NULL, "Dauda Ainoo", "234.26.23.190", NULL, NULL, "SETTINGS", "created block D", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("806be78ad6f14ed5ba9c348937707301/Sat29Jul20231037AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:37:47", 0, NULL, NULL, "Dauda Ainoo", "32.216.233.214", NULL, NULL, "SETTINGS", "Updated BC", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("819f536c66c74b5ea474ccd5a49862b5/Tue25Jul2023805AM", "KUMASI_ZONE", "dainoo", "2023-07-25 08:05:40", 0, NULL, NULL, "Dauda Ainoo", "76.39.173.88", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("823129dba07a4f84a320682eba75248b/Tue1Aug2023250AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:50:13", 0, NULL, NULL, "Dauda Ainoo", "185.245.231.63", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8391d08922ad47acb46ff716e5668977/Mon31Jul2023823AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:23:28", 0, NULL, NULL, "Dauda Ainoo", "78.196.14.204", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("842fa7ad5e3642f4986b6249134df7de/Tue1Aug2023250AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:50:48", 0, NULL, NULL, "Dauda Ainoo", "167.57.16.46", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8629d247da944011868148ea6c8d2fae/Tue1Aug2023211AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:11:18", 0, NULL, NULL, "Dauda Ainoo", "108.112.160.132", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("866b5a7a2ed042c1bf0c7bdfb2d177c2/Mon31Jul2023837AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:37:14", 0, NULL, NULL, "Dauda Ainoo", "93.227.232.88", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("89ab7dbf4e38446d8652a5716ded86ec/Thu27Jul20231025AM", "KUMASI_ZONE", "dainoo", "2023-07-27 10:25:30", 0, NULL, NULL, "Dauda Ainoo", "189.49.140.222", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8a9ad3e526ea45afb06c167cf3bd325c/Mon31Jul2023602AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:02:54", 0, NULL, NULL, "Dauda Ainoo", "192.38.88.6", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8a9cd0a6ca8d4912848c39fecdd93097/Tue1Aug2023256AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:56:09", 0, NULL, NULL, "Dauda Ainoo", "71.200.185.230", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8ae56034c9274c9085a20e3f06911b3b/Sat29Jul20231002AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:02:19", 0, NULL, NULL, "Dauda Ainoo", "158.166.2.38", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8e2fc72c670e4430aa21ff386a869505/Thu3Aug2023600PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:00:39", 0, NULL, NULL, "Dauda Ainoo", "117.250.156.64", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8e90bd5db6364a5e89fb62e9b4758c5c/Mon31Jul2023850AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:50:39", 0, NULL, NULL, "Dauda Ainoo", "249.182.42.204", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8f09a3c39dd84cb0b2736e8dafffa6dd/Mon31Jul2023703AM", "KUMASI_ZONE", "dainoo", "2023-07-31 07:03:10", 0, NULL, NULL, "Dauda Ainoo", "111.233.218.176", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("8f67e5cd021046de8245569596f95381/Tue25Jul2023716AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:16:26", 0, NULL, NULL, "Dauda Ainoo", "171.175.89.149", NULL, NULL, "SETTINGS", "Updated Halla Allison", NULL, "dainoo", "ASHANTI_REGION"),
	("92c113291a4a45ed8ff4c153c4b28fab/Tue1Aug2023156AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:56:02", 0, NULL, NULL, "Dauda Ainoo", "142.183.65.74", NULL, NULL, "SETTINGS", "created estate KETAN", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("943ccfd20021493da165627abe9b63ff/Fri4Aug2023957AM", "KUMASI_ZONE", "dainoo", "2023-08-04 09:57:16", 0, NULL, NULL, "Dauda Ainoo", "57.86.165.42", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("94c31ef5970542c98264d004ff101035/Mon31Jul2023849AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:49:52", 0, NULL, NULL, "Dauda Ainoo", "42.29.179.66", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("94d252a4fe7f4c168eda8203e26d31f9/Tue1Aug2023855AM", "KUMASI_ZONE", "dainoo", "2023-08-01 08:55:59", 0, NULL, NULL, "Dauda Ainoo", "2.195.219.203", NULL, NULL, "SETTINGS", "created estateId a", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("956bc055b7804bb8ba20e6c3edd4b08e/Tue1Aug2023236AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:36:45", 0, NULL, NULL, "Dauda Ainoo", "19.231.158.43", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9592b1c6a56e4e9eb9a7b0631cb2b179/Thu3Aug2023542PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:42:22", 0, NULL, NULL, "Dauda Ainoo", "53.144.208.66", NULL, NULL, "SETTINGS", "Updated A", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("961a4b3dfa954148b2f6b9ad6585d2f1/Fri4Aug20231211PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:11:49", 0, NULL, NULL, "Dauda Ainoo", "254.98.33.215", NULL, NULL, "SETTINGS", "created estate Anthony Parrish", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("96570ad5c07f400a93313e1111c9c2c9/Wed26Jul2023537AM", "KUMASI_ZONE", "dainoo", "2023-07-26 05:37:46", 0, NULL, NULL, "Dauda Ainoo", "8.142.219.188", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("980c10923cf24d90bca236a633bece02/Sun30Jul2023230PM", "KUMASI_ZONE", "dainoo", "2023-07-30 14:30:40", 0, NULL, NULL, "Dauda Ainoo", "58.124.1.16", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9962a0b3fe1d4ed881f984c785527748/Sat29Jul20231015AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:15:09", 0, NULL, NULL, "Dauda Ainoo", "59.10.227.7", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("998e9c2d3e0946fe8ae7d63514fb8da6/Tue1Aug2023251AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:51:37", 0, NULL, NULL, "Dauda Ainoo", "79.119.224.11", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9a47d83326d84453b0ef1c7cc3d2de29/Tue1Aug2023928AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:28:34", 0, NULL, NULL, "Dauda Ainoo", "124.86.4.77", NULL, NULL, "SETTINGS", "created block B", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9a6918b2a72546d1a3cbc2ce1f4962c9/Tue1Aug2023230AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:30:52", 0, NULL, NULL, "Dauda Ainoo", "154.11.245.250", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9af59f49717b40b9908ddbbc8260633a/Fri4Aug2023103PM", "KUMASI_ZONE", "dainoo", "2023-08-04 13:03:41", 0, NULL, NULL, "Dauda Ainoo", "206.206.208.10", NULL, NULL, "SETTINGS", "Updated Kenyon Marquez", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9b393b3c845f484fbc78b2d6e7cdc2bf/Fri28Jul20231059AM", "KUMASI_ZONE", "dainoo", "2023-07-28 10:59:23", 0, NULL, NULL, "Dauda Ainoo", "38.11.184.142", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9be09fe7d77b4503899b02c058bc9ea2/Sun30Jul20231105PM", "KUMASI_ZONE", "dainoo", "2023-07-30 23:05:12", 0, NULL, NULL, "Dauda Ainoo", "168.116.47.106", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9e0064ed81ec43049f4383ea8a8fb6fa/Sun30Jul20231107PM", "KUMASI_ZONE", "dainoo", "2023-07-30 23:07:54", 0, NULL, NULL, "Dauda Ainoo", "86.154.102.0", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9ea44a65f5a143629ed3c88c2c4b6d95/Sat29Jul20231011AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:11:07", 0, NULL, NULL, "Dauda Ainoo", "202.223.83.6", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("9f47891527d045a69d639e8dc761a2ec/Tue1Aug2023255AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:55:16", 0, NULL, NULL, "Dauda Ainoo", "45.219.185.126", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a0516a363df145838dff9e46f579d47d/Sat29Jul2023957AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:57:37", 0, NULL, NULL, "Dauda Ainoo", "172.6.237.44", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a0bc246ce62e433fa1ec5f8580915d93/Tue25Jul2023717AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:17:36", 0, NULL, NULL, "Dauda Ainoo", "241.198.134.135", NULL, NULL, "SETTINGS", "Viewed departments page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a0c7a67d7f684c20ab281f667c1bdec1/Tue1Aug2023141AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:41:42", 0, NULL, NULL, "Dauda Ainoo", "11.137.75.218", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a11cec11cd484d10ab1393b70fa8d78b/Tue1Aug2023238AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:38:12", 0, NULL, NULL, "Dauda Ainoo", "59.176.193.156", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a1c10579e4f64f64a7c74e423c97fbfe/Tue1Aug2023211AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:11:51", 0, NULL, NULL, "Dauda Ainoo", "124.164.130.153", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a4e94a7ead544e81aea2c3f44545ae1f/Fri4Aug20231212PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:12:51", 0, NULL, NULL, "Dauda Ainoo", "209.184.120.114", NULL, NULL, "SETTINGS", "created estate Kenyon Marquez", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a7a5df8581724f80964c8285b53e491c/Mon31Jul2023559AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:59:39", 0, NULL, NULL, "Dauda Ainoo", "92.36.121.189", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("a91fd80eefce4d8ab128f991368ffdfa/Tue1Aug2023140AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:40:37", 0, NULL, NULL, "Dauda Ainoo", "162.173.41.60", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("aa71c18861bd40e7a1ad29a7f6c6d085/Thu3Aug2023537PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:37:21", 0, NULL, NULL, "Dauda Ainoo", "64.112.250.159", NULL, NULL, "SETTINGS", "created block AB", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("abcc6d3e4bf94f2bb9d082024a7aec69/Tue1Aug2023231AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:31:26", 0, NULL, NULL, "Dauda Ainoo", "11.210.188.127", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ae31cd74ad6748bf95810caabe2b4c09/Fri4Aug20231213PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:13:55", 0, NULL, NULL, "Dauda Ainoo", "174.169.116.182", NULL, NULL, "SETTINGS", "created estate Glenna Porter", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("b0936861af474825b0e2543cf03768ef/Thu3Aug2023638PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:38:36", 0, NULL, NULL, "Dauda Ainoo", "81.86.52.238", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("b11953420cd2443190139ac97f9b9424/Tue25Jul2023716AM", "KUMASI_ZONE", "dainoo", "2023-07-25 07:16:27", 0, NULL, NULL, "Dauda Ainoo", "56.238.169.41", NULL, NULL, "SETTINGS", "Viewed departments page", NULL, "dainoo", "ASHANTI_REGION"),
	("b13eb81b3d1f4dde8c829a97c425ba3d/Mon31Jul2023553AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:53:01", 0, NULL, NULL, "Dauda Ainoo", "127.141.132.211", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("b4beb02eae1c4a94a2b52aec048197ba/Thu3Aug2023638PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:38:01", 0, NULL, NULL, "Dauda Ainoo", "168.158.237.93", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("b5bf63afd410488783e84d73ab2b3fcc/Sat29Jul20231008AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:08:03", 0, NULL, NULL, "Dauda Ainoo", "121.8.234.76", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ba073a407c9c483a982d058f105dfe4f/Tue1Aug2023254AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:54:19", 0, NULL, NULL, "Dauda Ainoo", "189.189.1.151", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("bc967ff76bfa43218d0f1b43ca380e45/Fri4Aug2023103PM", "KUMASI_ZONE", "dainoo", "2023-08-04 13:03:20", 0, NULL, NULL, "Dauda Ainoo", "109.229.119.57", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("be36aa4f470e4c129c91d547e52277af/Sat29Jul20231010AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:10:01", 0, NULL, NULL, "Dauda Ainoo", "203.252.218.70", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("bfc031caac6d4df48a6f5998a7a5c80f/Sun30Jul20231048PM", "KUMASI_ZONE", "dainoo", "2023-07-30 22:48:42", 0, NULL, NULL, "Dauda Ainoo", "100.11.102.120", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("bfc93c4f36b749aaabb6235d5c334417/Mon31Jul2023849AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:49:13", 0, NULL, NULL, "Dauda Ainoo", "34.71.33.158", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c0bdda828dcf45e08b85efeb15ed5f3f/Fri4Aug20231146AM", "KUMASI_ZONE", "dainoo", "2023-08-04 11:46:48", 0, NULL, NULL, "Dauda Ainoo", "210.42.89.171", NULL, NULL, "SETTINGS", "created estate Remedios Rowland", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c1caf3c7b9b34afd9c096d7c9320a8e6/Sat29Jul20231016AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:16:33", 0, NULL, NULL, "Dauda Ainoo", "204.238.163.76", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c22f5eaf6ade4f3699c82d2f74949412/Tue1Aug2023246AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:46:38", 0, NULL, NULL, "Dauda Ainoo", "220.166.138.124", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c3020015bc2e41148455ffe169ac2f3a/Mon31Jul2023637AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:37:34", 0, NULL, NULL, "Dauda Ainoo", "170.65.159.68", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c407e7268af545299f783add215c5fed/Mon31Jul2023556AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:56:04", 0, NULL, NULL, "Dauda Ainoo", "247.0.52.1", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c454e1d8390040229fc6e95f12b5633f/Mon31Jul2023608AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:08:40", 0, NULL, NULL, "Dauda Ainoo", "34.116.28.41", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c4b120375c8c458b9afbfa6566f8b737/Tue1Aug2023241AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:41:05", 0, NULL, NULL, "Dauda Ainoo", "80.27.129.130", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c5d5fad74aab47a69f7338bd19a80f00/Sat29Jul20231029AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:29:56", 0, NULL, NULL, "Dauda Ainoo", "145.117.124.177", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c6cd750e36c142788f67f4e91ffabdd3/Sat29Jul2023945PM", "KUMASI_ZONE", "dainoo", "2023-07-29 21:45:29", 0, NULL, NULL, "Dauda Ainoo", "97.235.95.18", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c7c16cc44b5048998b76c7aca553958b/Tue1Aug2023145AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:45:18", 0, NULL, NULL, "Dauda Ainoo", "86.181.40.253", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c7eb1d64116c441f901833ef8411c0d0/Tue1Aug2023254AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:54:34", 0, NULL, NULL, "Dauda Ainoo", "150.60.249.220", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c8b730a6c3874e318d0be5ac48b35a28/Sat29Jul20231025AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:25:31", 0, NULL, NULL, "Dauda Ainoo", "37.21.205.96", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("c97f02c8776f4119b35daeb1d5236cc3/Tue1Aug2023221AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:21:27", 0, NULL, NULL, "Dauda Ainoo", "254.135.111.136", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ca25980eb9164c78ade449834272a733/Tue1Aug2023252AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:52:13", 0, NULL, NULL, "Dauda Ainoo", "46.219.43.108", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("cb062c8dbf0d47c59d62e8378825a66b/Mon31Jul2023548AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:48:59", 0, NULL, NULL, "Dauda Ainoo", "147.38.137.155", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("cb52325ebe20445baf2588d18ea88a6d/Thu3Aug2023554PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:54:31", 0, NULL, NULL, "Dauda Ainoo", "218.148.152.29", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("cb5674046b524943afd344948202e980/Wed26Jul2023507AM", "KUMASI_ZONE", "dainoo", "2023-07-26 05:07:07", 0, NULL, NULL, "Dauda Ainoo", "58.84.93.113", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("cd76dc616f8c4245b4c65f43c776a03c/Fri4Aug2023107PM", "KUMASI_ZONE", "dainoo", "2023-08-04 13:07:53", 0, NULL, NULL, "Dauda Ainoo", "90.95.2.218", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d00a98b565fe4e9d8c8f821a68eb37b3/Sun30Jul20231048PM", "KUMASI_ZONE", "dainoo", "2023-07-30 22:48:38", 0, NULL, NULL, "Dauda Ainoo", "225.43.148.132", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d10cff327555497db52f67b5acb3fdce/Sat29Jul20231011AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:11:42", 0, NULL, NULL, "Dauda Ainoo", "37.120.13.103", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d27cec3bd8cb4813b121da9051aa25b7/Sat29Jul20231014AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:14:33", 0, NULL, NULL, "Dauda Ainoo", "166.145.194.63", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d2e0d29f2e4b42f6b205e57356d745f6/Thu3Aug2023552PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:52:09", 0, NULL, NULL, "Dauda Ainoo", "39.40.158.27", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d5d972b323de49939738a192cacf0ed8/Thu3Aug2023537PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:37:50", 0, NULL, NULL, "Dauda Ainoo", "9.153.182.57", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d72f0cd0b8f341c984d27c6f30573dcd/Sat29Jul2023948AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:48:09", 0, NULL, NULL, "Dauda Ainoo", "45.118.248.55", NULL, NULL, "SETTINGS", "Viewed zones page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d76c4abe5745473b8abc119cc62752e5/Fri4Aug20231212PM", "KUMASI_ZONE", "dainoo", "2023-08-04 12:12:33", 0, NULL, NULL, "Dauda Ainoo", "56.126.142.148", NULL, NULL, "SETTINGS", "created estate Lareina Blackwell", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d77402f46afe43f38c91bfaa28709008/Tue1Aug2023236AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:36:14", 0, NULL, NULL, "Dauda Ainoo", "207.72.131.230", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("d856940f3ff14365a26efd184c46e338/Mon31Jul2023614AM", "KUMASI_ZONE", "dainoo", "2023-07-31 06:14:21", 0, NULL, NULL, "Dauda Ainoo", "78.25.51.172", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("dc2fb9fa674e4fd4ab02aed9062d003f/Tue1Aug2023256AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:56:58", 0, NULL, NULL, "Dauda Ainoo", "214.63.172.189", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("dc5a26339af44368afbb62373864eb85/Mon31Jul2023846AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:46:41", 0, NULL, NULL, "Dauda Ainoo", "154.120.56.59", NULL, NULL, "SETTINGS", "created estate NORTH EFFIAKUMA", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("dcfa6560d94749fc8a7b0b8ac97d41d0/Tue1Aug2023152AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:52:18", 0, NULL, NULL, "Dauda Ainoo", "9.26.118.173", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("dee737b3200a4e60abc6442d21e6820c/Sat29Jul2023958AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:58:56", 0, NULL, NULL, "Dauda Ainoo", "240.253.25.232", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e0214dd7863f44b9beba9a374aa61488/Mon31Jul2023757PM", "KUMASI_ZONE", "dainoo", "2023-07-31 19:57:55", 0, NULL, NULL, "Dauda Ainoo", "88.248.225.3", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e158d36ff89c4ccd8bba36c881e90a8f/Mon31Jul2023823AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:23:24", 0, NULL, NULL, "Dauda Ainoo", "77.244.231.104", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e15ef3c227444dfc988c3dc25f2b7ec8/Thu3Aug2023537PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:37:34", 0, NULL, NULL, "Dauda Ainoo", "113.156.110.211", NULL, NULL, "SETTINGS", "created block A", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e181faadf45b474fa82b62a9d9e167a1/Sat29Jul2023950AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:50:14", 0, NULL, NULL, "Dauda Ainoo", "249.215.104.128", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e1ccc80ed4a6424a92064ecb7a40a97b/Sat29Jul20231019PM", "KUMASI_ZONE", "dainoo", "2023-07-29 22:19:50", 0, NULL, NULL, "Dauda Ainoo", "103.87.103.136", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e304bd9a483040c6bc30e82881fabb4a/Thu3Aug2023936PM", "KUMASI_ZONE", "dainoo", "2023-08-03 21:36:24", 0, NULL, NULL, "Dauda Ainoo", "188.143.4.103", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e4618479740e4b35b2c4fc98df5a1f4a/Mon31Jul2023852AM", "KUMASI_ZONE", "dainoo", "2023-07-31 08:52:44", 0, NULL, NULL, "Dauda Ainoo", "58.250.124.36", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e4c034f5149142ab9ec8cc3eff494875/Wed2Aug2023324AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:24:49", 0, NULL, NULL, "Dauda Ainoo", "140.172.219.144", NULL, NULL, "SETTINGS", "Updated B", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e7873fbd92144286a6f3babdb04e7a1f/Fri4Aug2023955AM", "KUMASI_ZONE", "dainoo", "2023-08-04 09:55:05", 0, NULL, NULL, "Dauda Ainoo", "129.133.138.118", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e7ade8ba41ca4aaca63f209491f27a94/Mon31Jul2023904PM", "KUMASI_ZONE", "dainoo", "2023-07-31 21:04:47", 0, NULL, NULL, "Dauda Ainoo", "206.185.35.182", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("e9434ed748184a849367cf33b26fa53e/Tue1Aug2023254AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:54:48", 0, NULL, NULL, "Dauda Ainoo", "84.245.5.220", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ea7140d74f2548a2b3b1a5cfedb957e8/Sat29Jul20231029AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:29:20", 0, NULL, NULL, "Dauda Ainoo", "243.119.85.94", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ea7342b1b2f24e10a3512f747774547c/Sat29Jul2023948AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:48:13", 0, NULL, NULL, "Dauda Ainoo", "47.50.234.204", NULL, NULL, "SETTINGS", "Viewed regions page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("eaacb2f4fd3f42a6a3a417273074469c/Mon31Jul2023559AM", "KUMASI_ZONE", "dainoo", "2023-07-31 05:59:09", 0, NULL, NULL, "Dauda Ainoo", "157.105.142.55", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("eb422d8ac8504e65bb8a8f84a01fae24/Sat29Jul2023106PM", "KUMASI_ZONE", "dainoo", "2023-07-29 13:06:55", 0, NULL, NULL, "Dauda Ainoo", "60.78.226.144", NULL, NULL, "LOGOUT", "Logged out", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("ec9ff23f95974ecdaa9742c2f4cc9807/Sat29Jul20231012AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:12:19", 0, NULL, NULL, "Dauda Ainoo", "183.65.111.254", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f053301775f141c982c6233d17100c3f/Thu3Aug2023518PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:18:14", 0, NULL, NULL, "Dauda Ainoo", "58.74.48.143", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f1eecb53a43e4457a0db21073e7dbc03/Tue1Aug2023201AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:01:56", 0, NULL, NULL, "Dauda Ainoo", "53.209.250.62", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f2f3b5da43b246faac67662ece611f77/Tue1Aug2023136AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:36:40", 0, NULL, NULL, "Dauda Ainoo", "39.229.102.188", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f3e50a1f159c4c29a9f1c16d4239ad7d/Sat29Jul20231008AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:08:56", 0, NULL, NULL, "Dauda Ainoo", "224.4.184.130", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f549dc8dd7ee4590a95dc9f8b528b55a/Sat29Jul20231018AM", "KUMASI_ZONE", "dainoo", "2023-07-29 10:18:05", 0, NULL, NULL, "Dauda Ainoo", "65.204.205.228", NULL, NULL, "SETTINGS", "Viewed zones page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f6e358641f394cfab7b8d30ee24124fa/Sat29Jul2023956AM", "KUMASI_ZONE", "dainoo", "2023-07-29 09:56:16", 0, NULL, NULL, "Dauda Ainoo", "172.85.189.199", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f965b3567b0943129c60cb602cf95915/Sun30Jul2023152PM", "KUMASI_ZONE", "dainoo", "2023-07-30 13:52:13", 0, NULL, NULL, "Dauda Ainoo", "132.32.221.228", NULL, NULL, "LOGIN", "Logged in", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("f98269eb16e04d689eacf19ec3b50365/Tue1Aug2023153AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:53:06", 0, NULL, NULL, "Dauda Ainoo", "168.82.10.240", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("faa0f09863e2466d898896dc150bd686/Thu3Aug2023559PM", "KUMASI_ZONE", "dainoo", "2023-08-03 17:59:52", 0, NULL, NULL, "Dauda Ainoo", "122.105.225.186", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fbf681ca76364055a771ad2c38728afa/Wed2Aug2023317AM", "KUMASI_ZONE", "dainoo", "2023-08-02 03:17:17", 0, NULL, NULL, "Dauda Ainoo", "54.144.246.78", NULL, NULL, "SETTINGS", "Updated C", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fc61423cd36840e8a6ed046e1233b723/Thu3Aug2023634PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:34:58", 0, NULL, NULL, "Dauda Ainoo", "116.158.214.97", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fd5a8d83f68f45a5b9076e714d1cef65/Tue1Aug2023200AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:00:49", 0, NULL, NULL, "Dauda Ainoo", "166.90.246.22", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fdf9e1c6c13e4727b1fa47f19c5ce622/Tue1Aug2023235AM", "KUMASI_ZONE", "dainoo", "2023-08-01 02:35:41", 0, NULL, NULL, "Dauda Ainoo", "199.94.203.170", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fe03804dc3dc423996c201c67e7eff08/Thu3Aug2023601PM", "KUMASI_ZONE", "dainoo", "2023-08-03 18:01:12", 0, NULL, NULL, "Dauda Ainoo", "175.229.126.13", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fe8179e9ef524a3e8ad939aee00e04e0/Tue1Aug2023903AM", "KUMASI_ZONE", "dainoo", "2023-08-01 09:03:50", 0, NULL, NULL, "Dauda Ainoo", "19.225.193.157", NULL, NULL, "SETTINGS", "created block B", "ACCOUNTANT", "dainoo", "ASHANTI_REGION"),
	("fe85136f216c4b20b6ca254f5c66dce5/Tue1Aug2023144AM", "KUMASI_ZONE", "dainoo", "2023-08-01 01:44:01", 0, NULL, NULL, "Dauda Ainoo", "201.170.186.75", NULL, NULL, "SETTINGS", "Viewed estates page", "ACCOUNTANT", "dainoo", "ASHANTI_REGION");

/*!40000 ALTER TABLE `audit_log` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `record_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;

INSERT INTO `department` (`record_id`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `department_name`, `last_modified_by`, `last_modified_date`, `remarks`) VALUES
	("ADMINISTRATION", "dainoo", "2023-07-16 07:15:43", 0, NULL, NULL, "Administration", NULL, NULL, "Oversees the general running of the SHC"),
	("COMPLIANCE", "dainoo", "2023-07-16 07:19:29", 0, NULL, NULL, "Compliance", NULL, NULL, "Enforces regulations and conducts of SHC"),
	("COMPUTERS", "dainoo", "2023-07-16 07:13:30", 0, NULL, NULL, "Computers", NULL, NULL, "Computers and accessories"),
	("ESTATE_OFFICE", "dainoo", "2023-07-16 07:17:25", 0, NULL, NULL, "Estate Office", NULL, "2023-07-16 07:17:25", "Oversees the  running of estate related actvities"),
	("FINANCE", "dainoo", "2023-07-16 07:18:35", 0, NULL, NULL, "Finance", NULL, NULL, "Handles the finaces of SHC"),
	("HAKEEM_VAUGHAN", "dainoo", "2023-07-25 07:12:26", 0, NULL, NULL, "Hakeem Vaughan", NULL, NULL, "Non sit enim tenetu"),
	("HALLA_ALLISON", "sabonay", "2023-07-18 05:31:05", 0, NULL, NULL, "Halla Allison", "dainoo", "2023-07-25 07:16:26", "Ea ratione aut volup"),
	("ORLI_NICHOLSON", "sabonay", "2023-07-18 05:25:50", 0, NULL, NULL, "Orli Nicholson", NULL, NULL, "Cumque tempor modi n"),
	("SIMONE_MALDONADO", "sabonay", "2023-07-18 05:34:51", 0, NULL, NULL, "Simone Maldonado", "dainoo", "2023-07-25 07:17:34", "In veritatis consect"),
	("TEST", "sabonay", "2023-07-16 07:24:27", 1, "sabonay", "2023-07-16 07:31:43", "test updated", "sabonay", "2023-07-16 07:30:37", "This is for updated testing");

/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table estate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estate`;

CREATE TABLE `estate` (
  `record_id` varchar(255) NOT NULL,
  `additional_desc` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `date_initialized` date DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `estate_class` varchar(255) DEFAULT NULL,
  `estate_location` varchar(255) DEFAULT NULL,
  `estate_name` varchar(255) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `land_size` double DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_estate_region` (`region`),
  CONSTRAINT `FK_estate_region` FOREIGN KEY (`region`) REFERENCES `region` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `estate` WRITE;
/*!40000 ALTER TABLE `estate` DISABLE KEYS */;

INSERT INTO `estate` (`record_id`, `additional_desc`, `created_by`, `created_date`, `date_initialized`, `deleted`, `deleted_by`, `deleted_date`, `estate_class`, `estate_location`, `estate_name`, `expiration_date`, `land_size`, `last_modified_by`, `last_modified_date`, `region`) VALUES
	("ADB", "This is nice place", "dainoo", "2023-07-31 08:49:11", "2022-05-19", 0, NULL, NULL, "THIRD_CLASS", "ADIEMBRA", "ADIEMBRA", "2025-01-01", 1000, NULL, NULL, "TAKORADI"),
	("KET", "Consequuntur proiden", "dainoo", "2023-08-01 01:56:02", "2020-01-01", 0, NULL, NULL, "SECOND_CLASS", "Harum consectetur qu", "KETAN", "2027-12-01", 359089, NULL, NULL, "TAKORADI"),
	("NEK", "", "dainoo", "2023-07-31 08:46:41", "2023-07-02", 0, NULL, NULL, "SECOND_CLASS", "NORTH EFFIAKUMA", "NORTH EFFIAKUMA", "2023-07-31", 50000, NULL, NULL, "TAKORADI"),
	("SEK", "This is a beautiful estate", NULL, NULL, "2021-01-01", 0, NULL, NULL, "FIRST_CLASS", "Main street", "SOUTH EFFIAKUMA", "2029-01-01", 2000, "dainoo", "2023-07-31 08:49:49", "TAKORADI"),
	("SKK", NULL, "dainoo", "2023-07-31 08:47:44", "2021-02-02", 0, NULL, NULL, "FIRST_CLASS", "SOUTH KWEIKUMA", "SOUTH KWEIKUMA", "2029-01-01", 70000, NULL, NULL, "TAKORADI");

/*!40000 ALTER TABLE `estate` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table estate_block
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estate_block`;

CREATE TABLE `estate_block` (
  `record_id` varchar(255) NOT NULL,
  `block_name` varchar(255) DEFAULT NULL,
  `block_size` double DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `estate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_estate_block_estate` (`estate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `estate_block` WRITE;
/*!40000 ALTER TABLE `estate_block` DISABLE KEYS */;

INSERT INTO `estate_block` (`record_id`, `block_name`, `block_size`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `last_modified_by`, `last_modified_date`, `remarks`, `estate`) VALUES
	("ADB/A", "A", 834, "dainoo", "2023-08-01 09:28:05", 0, NULL, NULL, "dainoo", "2023-08-02 03:42:45", "Fresh one", "ADB"),
	("ADB/B", "B", 876, "dainoo", "2023-08-01 09:28:05", 0, NULL, NULL, "dainoo", "2023-08-02 03:24:49", "nice one", "ADB"),
	("ADB/C", "C", 3553, "dainoo", "2023-08-01 09:28:34", 0, NULL, NULL, "dainoo", "2023-08-02 03:31:59", "This is a fresh block", "ADB"),
	("KET/A", "A", 87657, "dainoo", "2023-08-01 09:25:48", 0, NULL, NULL, NULL, NULL, "Nice one", "KET"),
	("KET/B", "B", 6893, "dainoo", "2023-08-01 09:26:29", 0, NULL, NULL, NULL, NULL, "This is working", "KET"),
	("KET/C", "C", 9767, "dainoo", "2023-08-01 09:27:19", 0, NULL, NULL, "dainoo", "2023-08-02 03:41:52", "This is a great block", "KET"),
	("SKK/A", "A", 9762, "dainoo", "2023-08-03 17:37:34", 0, NULL, NULL, "dainoo", "2023-08-03 17:42:22", "This is a well planned block", "SKK"),
	("SKK/AB", "AB", 7946, "dainoo", "2023-08-03 17:37:21", 0, NULL, NULL, NULL, NULL, "This is a good block", "SKK"),
	("SKK/D", "D", 75406, "dainoo", "2023-08-03 17:37:45", 0, NULL, NULL, "dainoo", "2023-08-03 17:38:17", "Fresh block in the buidling", "SKK");

/*!40000 ALTER TABLE `estate_block` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table estate_property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estate_property`;

CREATE TABLE `estate_property` (
  `record_id` varchar(255) NOT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  `property_number` varchar(255) DEFAULT NULL,
  `estate_block` varchar(255) DEFAULT NULL,
  `property_category` varchar(255) DEFAULT NULL,
  `blocked` tinyint(1) DEFAULT '0',
  `property_land_size` double DEFAULT NULL,
  `property_usage` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `INDEX_M55X` (`estate_block`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `estate_property` WRITE;
/*!40000 ALTER TABLE `estate_property` DISABLE KEYS */;

INSERT INTO `estate_property` (`record_id`, `property_name`, `property_number`, `estate_block`, `property_category`, `blocked`, `property_land_size`, `property_usage`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `last_modified_by`, `last_modified_date`) VALUES
	("ADB-ADB/B-91", "Anthony Parrish", "91", "ADB/B", "HOUSE", 0, 80, "MIXED_USE", "dainoo", "2023-08-04 12:11:49", 0, NULL, NULL, NULL, NULL),
	("ADB-ADB/B-99", "Kenyon Marquez", "99", "ADB/B", "LAND", 0, 861, "COMMERCIAL", "dainoo", "2023-08-04 12:11:49", 0, NULL, NULL, "dainoo", "2023-08-04 13:03:41"),
	("KET-KET/C-10", "Macaulay Solomon", "10", "KET/C", "LAND", 0, 60, "RESIDENTIAL", "dainoo", "2023-08-04 12:10:50", 0, NULL, NULL, NULL, NULL),
	("SKK-SKK/A-90", "Sharon Dillard", "90", "SKK/A", "HOUSE", 0, 34, "INSTITUTIONAL", "dainoo", "2023-08-04 12:13:28", 0, NULL, NULL, NULL, NULL),
	("SKK-SKK/AB-32", "Glenna Porter", "32", "SKK/AB", "HOUSE", 0, 95, "COMMERCIAL", "dainoo", "2023-08-04 12:13:55", 0, NULL, NULL, NULL, NULL),
	("SKK-SKK/AB-99", "Lareina Blackwell", "99", "SKK/AB", "HOUSE", 0, 31, "COMMERCIAL", "dainoo", "2023-08-04 12:12:33", 0, NULL, NULL, NULL, NULL);

/*!40000 ALTER TABLE `estate_property` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table estate_zone
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estate_zone`;

CREATE TABLE `estate_zone` (
  `record_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contactNo` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `zone_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `estate_zone` WRITE;
/*!40000 ALTER TABLE `estate_zone` DISABLE KEYS */;

INSERT INTO `estate_zone` (`record_id`, `address`, `contactNo`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `last_modified_by`, `last_modified_date`, `remarks`, `zone_name`) VALUES
	("ACCRA_ZONE", "P O Box X 67 Cantoment", "0245283455", "sbortey", "2023-07-03 18:41:02", 0, NULL, NULL, "sabonay", "2023-07-03 19:13:56", "This comprises of Volta and Oti regions", "Accra Zone"),
	("ERIN_HOLLAND", "Vel praesentium magn", "542352171", "sabonay", "2023-07-03 21:19:12", 0, NULL, NULL, "sabonay", "2023-07-03 21:21:28", "Ad error et corporis", "Erin Holland"),
	("KUMASI_ZONE", "P O Box X 15 FNT", "123456567", "sbortey", "2023-07-03 18:38:25", 0, NULL, NULL, "sabonay", "2023-07-03 19:08:09", "This comprises of Kumasi,Sunyani and Eastern regions", "Kumasi Zone"),
	("MECHELLE_UNDERWOOD", "Voluptatem aute qui ", "Est duis odio ut po", "sabonay", "2023-07-03 21:20:14", 0, NULL, NULL, NULL, NULL, "Et cupiditate incidi", "Mechelle Underwood"),
	("RISA_BOND", "Molestiae reprehende", "Totam Nam reiciendis", "sabonay", "2023-07-03 21:20:04", 0, NULL, NULL, NULL, NULL, "Voluptatem nulla cu", "Risa Bond");

/*!40000 ALTER TABLE `estate_zone` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table region
# ------------------------------------------------------------

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `record_id` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `region_name` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_region_zone` (`zone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;

INSERT INTO `region` (`record_id`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `last_modified_by`, `last_modified_date`, `region_name`, `remarks`, `zone`) VALUES
	("AHAFO_REGION", "sabonay", "2023-07-07 18:20:28", 0, NULL, NULL, NULL, NULL, "AHAFO_REGION", NULL, "MECHELLE_UNDERWOOD"),
	("ASHANTI_REGION", "sabonay", "2023-07-06 06:07:51", 0, NULL, NULL, "sabonay", "2023-07-07 18:17:04", "Ashanti Region", NULL, "KUMASI_ZONE"),
	("BONO_REGION", "sabonay", "2023-07-06 06:08:42", 0, NULL, NULL, "sabonay", "2023-07-07 18:17:10", "Bono Region", NULL, "KUMASI_ZONE"),
	("EASTERN_REGION", "sabonay", "2023-07-06 06:01:45", 0, NULL, NULL, NULL, NULL, "Eastern Region", NULL, "ACCRA_ZONE"),
	("GREATER_ACCRA", "sabonay", "2023-07-07 18:13:26", 0, NULL, NULL, NULL, "2023-07-07 18:13:26", "GREATER_ACCRA", NULL, "ACCRA_ZONE"),
	("SUNYANI", "sabonay", "2023-07-06 06:08:28", 0, NULL, NULL, NULL, NULL, "Sunyani", NULL, "KUMASI_ZONE"),
	("TAKORADI", "sabonay", "2023-07-06 06:08:12", 0, NULL, NULL, NULL, NULL, "Takoradi", NULL, "KUMASI_ZONE"),
	("VOLTA_REGION", "sabonay", "2023-07-06 06:01:10", 0, NULL, NULL, NULL, NULL, "Volta Region", NULL, "ACCRA_ZONE");

/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table staff
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `record_id` varchar(255) NOT NULL,
  `access_level` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `office_contact_no` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `other_names` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `reset_password` tinyint(1) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `account_created_by` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `account_created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_staff_region` (`region`),
  KEY `FK_staff_department` (`department`),
  KEY `record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;

INSERT INTO `staff` (`record_id`, `access_level`, `address`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `dob`, `email`, `first_name`, `gender`, `last_login_date`, `last_modified_by`, `last_modified_date`, `last_name`, `mobile_no`, `office_contact_no`, `department`, `region`, `other_names`, `username`, `reset_password`, `password`, `status`, `user_role`, `account_created_by`, `account_created_date`) VALUES
	("aainoo", "PENDING", "Porro culpa veniam", "sabonay", "2023-07-23 15:45:34", 0, NULL, NULL, NULL, "zypu@mailinator.com", "Anisha", "FEMALE", NULL, NULL, NULL, "Ainoo", "8989898988989", "382", "COMPLIANCE", "EASTERN_REGION", NULL, "aainoo", 1, "A1o1o4", "ACTIVE", "AUDITOR", "sabonay", "2023-07-23 16:15:29"),
	("arocha", "PENDING", "Sint nihil provident", "sabonay", "2023-07-23 15:58:31", 0, NULL, NULL, NULL, "tesixi@mailinator.com", "Adrienne", "FEMALE", NULL, NULL, NULL, "Rocha", "+1 (731) 578-8202", "+1 (829) 783-9003", "ADMINISTRATION", "ASHANTI_REGION", NULL, "arocha", 1, "O4a8AO", "RESET", "ESTATE_OFFICER", "sabonay", "2023-07-23 16:18:09"),
	("asanders", "PENDING", "Dolores tempore eos", "sabonay", "2023-07-23 19:43:10", 0, NULL, NULL, NULL, "hadorogugu@mailinator.com", "Amal", "FEMALE", NULL, NULL, NULL, "Sanders", "+1 (572) 187-6482", "+1 (879) 852-9187", "ADMINISTRATION", "ASHANTI_REGION", NULL, "asanders", 1, "d22097", "NEW", "ACCOUNTANT", "sabonay", "2023-07-23 19:44:19"),
	("brasmussen", NULL, "Ea duis porro repudi", "sabonay", "2023-07-23 19:38:28", 0, NULL, NULL, NULL, "vasufyfam@mailinator.com", "Barry", "FEMALE", NULL, NULL, NULL, "Rasmussen", "+1 (115) 765-5957", "+1 (698) 743-8371", "ADMINISTRATION", "AHAFO_REGION", NULL, "brasmussen", 0, NULL, "NEW", NULL, NULL, NULL),
	("cjacobs", "PENDING", "Veniam eum obcaecat", "sabonay", "2023-07-20 06:37:24", 0, NULL, NULL, NULL, "fuvufoxydu@mailinator.com", "Charles", "MALE", NULL, NULL, NULL, "Jacobs", "5439000000099", "448", "COMPUTERS", "BONO_REGION", NULL, "cjacobs", 1, "assc58", "NEW", "DIRECTOR", "sabonay", "2023-07-23 15:24:47"),
	("dainoo", "PENDING", "Ipsa lorem eum Nam ", "sabonay", "2023-07-19 06:41:49", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "FEMALE", "2023-08-04 13:03:19", NULL, NULL, "Ainoo", "0207111758", "570", "ADMINISTRATION", "ASHANTI_REGION", NULL, "dainoo", 1, "123456", "ACTIVE", "ACCOUNTANT", "sabonay", "2023-07-23 18:19:34"),
	("dainoo2", "PENDING", "Mollitia fuga Dicta", "sabonay", "2023-07-19 06:42:16", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "MALE", NULL, NULL, NULL, "Ainoo", "0207111758", "833", "COMPUTERS", "ASHANTI_REGION", NULL, "dainoo2", 1, "1oNaN5", "NEW", "AUDITOR", "sabonay", "2023-07-23 18:21:05"),
	("dainoo3", NULL, "A esse nostrum non a", "sabonay", "2023-07-19 06:44:42", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "FEMALE", NULL, NULL, NULL, "Ainoo", "0207111758", "817", "ESTATE_OFFICE", "ASHANTI_REGION", NULL, "dainoo3", 1, "N834Oa", "INACTIVE", NULL, NULL, NULL),
	("mdrake", NULL, "Enim quae sed minus ", "sabonay", "2023-07-23 16:03:17", 0, NULL, NULL, NULL, "pawa@mailinator.com", "Mollie", "FEMALE", NULL, NULL, NULL, "Drake", "+1 (693) 637-1993", "+1 (191) 686-4442", "COMPUTERS", "BONO_REGION", NULL, "mdrake", 0, NULL, "NEW", NULL, NULL, NULL),
	("mgarner", NULL, "In et ipsum laborios", "sabonay", "2023-07-23 15:51:36", 0, NULL, NULL, NULL, "favowoxedu@mailinator.com", "Macy", "FEMALE", NULL, NULL, NULL, "Garner", "87787887787878", "976900909090", "ESTATE_OFFICE", "TAKORADI", NULL, "mgarner", 0, NULL, "NEW", NULL, NULL, NULL),
	("mluna", NULL, "Iste dolore debitis ", "sabonay", "2023-07-23 18:14:47", 0, NULL, NULL, "2023-07-23", "pubij@mailinator.com", "Madeline", "MALE", NULL, NULL, NULL, "Luna", "+1 (509) 711-1907", "+1 (962) 877-6964", "COMPUTERS", "ASHANTI_REGION", NULL, "mluna", 0, NULL, "NEW", NULL, NULL, NULL),
	("rrosales", NULL, "Aut nostrum duis ven", "sabonay", "2023-07-19 06:45:17", 0, NULL, NULL, NULL, "zejofas@mailinator.com", "Robin", "MALE", NULL, NULL, NULL, "Rosales", "396456465765756", "903", "ADMINISTRATION", "ASHANTI_REGION", NULL, "rrosales", 0, NULL, "INACTIVE", NULL, NULL, NULL),
	("smurray", NULL, "Officiis quia fugiat", "sabonay", "2023-07-23 15:56:17", 1, "dainoo", "2023-07-26 05:30:52", NULL, "synehato@mailinator.com", "Suki", "MALE", NULL, NULL, NULL, "Murray", "+1 (182) 546-5095", "+1 (829) 719-4079", "ESTATE_OFFICE", "GREATER_ACCRA", NULL, "smurray", 0, NULL, "NEW", NULL, NULL, NULL),
	("tharrington", NULL, "Quas fuga Dolor pro", "sabonay", "2023-07-23 18:18:16", 0, NULL, NULL, NULL, "majynunesy@mailinator.com", "Tate", "MALE", NULL, NULL, NULL, "Harrington", "+1 (529) 278-3933", "+1 (591) 604-5282", "ADMINISTRATION", "ASHANTI_REGION", NULL, "tharrington", 0, NULL, "NEW", NULL, NULL, NULL);

/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of views
# ------------------------------------------------------------

# Creating temporary tables to overcome VIEW dependency errors


# Dump of routines
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS getAllEstates*/;;
/*!50003 SET @OLD_SQL_MODE=@@SQL_MODE*/;;
/*!50003 SET SQL_MODE="IGNORE_SPACE,ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getAllEstates`()
BEGIN
Select * from estate;
END*/;;
DELIMITER ;
/*!50003 SET SQL_MODE=@OLD_SQL_MODE*/;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

# Dump completed on 2023-08-04T14:53:18+00:00
