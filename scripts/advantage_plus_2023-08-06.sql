# ************************************************************
# Antares - SQL Client
# Version 0.7.0
# 
# https://antares-sql.app/
# https://github.com/antares-sql/antares
# 
# Host: localhost (MySQL Community Server  5.7.14)
# Database: advantage_plus
# Generation time: 2023-08-06T21:10:15+00:00
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
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `ui` varchar(255) DEFAULT NULL,
  `user_activity` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `estate_zone` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_audit_log_estate_zone` (`estate_zone`),
  KEY `FK_audit_log_region` (`region`),
  CONSTRAINT `FK_audit_log_estate_zone` FOREIGN KEY (`estate_zone`) REFERENCES `estate_zone` (`record_id`),
  CONSTRAINT `FK_audit_log_region` FOREIGN KEY (`region`) REFERENCES `region` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





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



# Dump of table occupant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `occupant`;

CREATE TABLE `occupant` (
  `record_id` varchar(255) NOT NULL,
  `institutional` tinyint(1) DEFAULT '0',
  `occupant_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `telephone_number` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `home_town` varchar(255) DEFAULT NULL,
  `local_address` varchar(255) DEFAULT NULL,
  `id_no` varchar(255) DEFAULT NULL,
  `id_type` varchar(255) DEFAULT NULL,
  `next_of_kin` varchar(255) DEFAULT NULL,
  `nok_address` varchar(255) DEFAULT NULL,
  `nok_email` varchar(255) DEFAULT NULL,
  `nok_phone` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `occupant_type` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `supervisor_name` varchar(255) DEFAULT NULL,
  `supervisor_tel` varchar(255) DEFAULT NULL,
  `supervisor_address` varchar(255) DEFAULT NULL,
  `supervisor_email` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `occupant` WRITE;
/*!40000 ALTER TABLE `occupant` DISABLE KEYS */;

INSERT INTO `occupant` (`record_id`, `institutional`, `occupant_name`, `gender`, `mobile_no`, `telephone_number`, `date_of_birth`, `created_by`, `email_address`, `home_town`, `local_address`, `id_no`, `id_type`, `next_of_kin`, `nok_address`, `nok_email`, `nok_phone`, `nationality`, `marital_status`, `occupant_type`, `occupation`, `relationship`, `supervisor_name`, `supervisor_tel`, `supervisor_address`, `supervisor_email`, `remarks`, `deleted_by`, `created_date`, `last_modified_by`, `last_modified_date`, `deleted`, `deleted_date`) VALUES
	("mic-agu-060823-2a4a", 0, "Michelle Aguirre", "FEMALE", "+1 (182) 681-5345", NULL, "2023-03-28", "dainoo", "zokuvo@mailinator.com", "Adipisicing quo sunt", "Recusandae Quas dol", "608", "VOTERS_ID", "George Stokes", "Itaque optio quis s", "corocohube@mailinator.com", "+1 (667) 903-5811", "GHANAIAN", "SINGLE", "TENANT", "Exercitationem nemo ", "OTHERS", NULL, NULL, NULL, NULL, NULL, NULL, "2023-08-06 09:33:21", NULL, NULL, 0, NULL),
	("ryl-doy-060823-9af4", 0, "Rylee Doyle", "MALE", "+1 (945) 733-3552", "+1 (945) 733-3552", "2023-08-01", "dainoo", "fuby@mailinator.com", "Impedit magni quasi", "Nisi praesentium et ", "251", "VOTERS_ID", "Zachery Whitehead", "Aliquip impedit dol", "vajud@mailinator.com", "+1 (955) 888-5268", "GHANAIAN", "OTHERS", "LESSEE", "Libero autem volupta", "FATHER", NULL, NULL, NULL, NULL, NULL, NULL, "2023-08-06 09:48:09", NULL, NULL, 0, NULL),
	("uma-tal-060823-929e", 1, "Uma Talley", NULL, "+1 (426) 729-5969", "+1 (426) 729-5969", "2022-09-05", "dainoo", "hajizo@mailinator.com", NULL, "Iste nostrud eum a c", NULL, NULL, NULL, NULL, NULL, NULL, "GHANAIAN", NULL, "TENANT", "Ut reiciendis elit ", NULL, "Unity Curry", "+1 (172) 167-2835", "Ipsum quo nostrum di", "xotil@mailinator.com", NULL, NULL, "2023-08-06 10:52:41", NULL, NULL, 0, NULL);

/*!40000 ALTER TABLE `occupant` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table occupant_property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `occupant_property`;

CREATE TABLE `occupant_property` (
  `record_id` varchar(255) NOT NULL,
  `occupant` varchar(255) DEFAULT NULL,
  `estate_property` varchar(255) DEFAULT NULL,
  `occupation_type` varchar(255) DEFAULT NULL,
  `first_date_of_occupancy` date DEFAULT NULL,
  `last_date_of_occupancy` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `INDEX_R366` (`occupant`),
  KEY `INDEX_ZMEJ` (`estate_property`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `occupant_property` WRITE;
/*!40000 ALTER TABLE `occupant_property` DISABLE KEYS */;

INSERT INTO `occupant_property` (`record_id`, `occupant`, `estate_property`, `occupation_type`, `first_date_of_occupancy`, `last_date_of_occupancy`, `created_by`, `created_date`, `deleted`, `deleted_by`, `deleted_date`, `last_modified_by`, `last_modified_date`) VALUES
	("ADB-ADB/B-91#mic-agu-060823-2a4a", "mic-agu-060823-2a4a", "ADB-ADB/B-91", "Leasehold", NULL, NULL, "dainoo", "2023-08-06 18:53:29", 0, NULL, NULL, NULL, NULL),
	("SKK-SKK/AB-32#ryl-doy-060823-9af4", "ryl-doy-060823-9af4", "SKK-SKK/AB-32", "Rental", NULL, NULL, "dainoo", "2023-08-06 19:00:27", 0, NULL, NULL, NULL, NULL),
	("SKK-SKK/AB-99#uma-tal-060823-929e", "uma-tal-060823-929e", "SKK-SKK/AB-99", "Leasehold", NULL, NULL, "dainoo", "2023-08-06 19:08:09", 0, NULL, NULL, NULL, NULL);

/*!40000 ALTER TABLE `occupant_property` ENABLE KEYS */;
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
	("dainoo", "PENDING", "Ipsa lorem eum Nam ", "sabonay", "2023-07-19 06:41:49", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "FEMALE", "2023-08-06 18:25:15", NULL, NULL, "Ainoo", "0207111758", "570", "ADMINISTRATION", "ASHANTI_REGION", NULL, "dainoo", 1, "123456", "ACTIVE", "ACCOUNTANT", "sabonay", "2023-07-23 18:19:34"),
	("dainoo2", "PENDING", "Mollitia fuga Dicta", "sabonay", "2023-07-19 06:42:16", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "MALE", NULL, NULL, NULL, "Ainoo", "0207111758", "833", "COMPUTERS", "ASHANTI_REGION", NULL, "dainoo2", 1, "1oNaN5", "NEW", "AUDITOR", "sabonay", "2023-07-23 18:21:05"),
	("dainoo3", NULL, "A esse nostrum non a", "sabonay", "2023-07-19 06:44:42", 0, NULL, NULL, NULL, "ainoodauda@gmail.com", "Dauda", "FEMALE", NULL, NULL, NULL, "Ainoo", "0207111758", "817", "ESTATE_OFFICE", "ASHANTI_REGION", NULL, "dainoo3", 1, "N834Oa", "INACTIVE", NULL, NULL, NULL),
	("khartman", NULL, "Id proident modi na", "dainoo", "2023-08-04 15:00:47", 0, NULL, NULL, NULL, "rokin@mailinator.com", "Kai", "FEMALE", NULL, NULL, NULL, "Hartman", "+1 (158) 235-7208", "+1 (427) 724-9748", "ADMINISTRATION", "BONO_REGION", NULL, "khartman", 0, NULL, "NEW", NULL, NULL, NULL),
	("mdrake", NULL, "Enim quae sed minus ", "sabonay", "2023-07-23 16:03:17", 0, NULL, NULL, NULL, "pawa@mailinator.com", "Mollie", "FEMALE", NULL, NULL, NULL, "Drake", "+1 (693) 637-1993", "+1 (191) 686-4442", "COMPUTERS", "BONO_REGION", NULL, "mdrake", 0, NULL, "NEW", NULL, NULL, NULL),
	("mgarner", NULL, "In et ipsum laborios", "sabonay", "2023-07-23 15:51:36", 0, NULL, NULL, NULL, "favowoxedu@mailinator.com", "Macy", "FEMALE", NULL, NULL, NULL, "Garner", "87787887787878", "976900909090", "ESTATE_OFFICE", "TAKORADI", NULL, "mgarner", 0, NULL, "NEW", NULL, NULL, NULL),
	("mluna", NULL, "Iste dolore debitis ", "sabonay", "2023-07-23 18:14:47", 0, NULL, NULL, "2023-07-23", "pubij@mailinator.com", "Madeline", "MALE", NULL, NULL, NULL, "Luna", "+1 (509) 711-1907", "+1 (962) 877-6964", "COMPUTERS", "ASHANTI_REGION", NULL, "mluna", 0, NULL, "NEW", NULL, NULL, NULL),
	("mpadilla", NULL, "Dignissimos duis vel", "dainoo", "2023-08-04 15:07:19", 0, NULL, NULL, NULL, "fequpady@mailinator.com", "Mariam", "FEMALE", NULL, NULL, NULL, "Padilla", "+1 (673) 104-2212", "+1 (473) 586-4431", "ADMINISTRATION", "ASHANTI_REGION", NULL, "mpadilla", 0, NULL, "NEW", NULL, NULL, NULL),
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

# Dump completed on 2023-08-06T21:10:15+00:00
