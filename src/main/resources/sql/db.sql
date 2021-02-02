delimiter $$

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `address` varchar(250) DEFAULT NULL,
  `state` varchar(250) DEFAULT NULL,
  `country` varchar(250) DEFAULT NULL,
  `zipcode` varchar(250) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8$$

