
use `lib_one_to_one_only`;


CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_title` varchar(60) DEFAULT NULL,
  `book_genre` varchar(60) DEFAULT NULL,
  `author_first_name` varchar(45) DEFAULT NULL,
  `author_last_name` varchar(45) DEFAULT NULL,
  `publisher_name` varchar(60) DEFAULT NULL,
  `date_of_purchase` date DEFAULT '1980-09-23',
  `cost_of_acquisition` double(16,2) DEFAULT '0.00',
  `currency_of_acquisition` varchar(3) DEFAULT NULL,
  `book_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookname` (`book_title`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;

CREATE TABLE `book_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shopping_channel` varchar(128) DEFAULT NULL,
  `type_of_binding` varchar(45) DEFAULT 'Hard Cover',
  `isbn` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;

ALTER TABLE `book` 
  ADD FOREIGN KEY (`book_detail_id`)  REFERENCES `book_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
;
