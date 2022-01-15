use `hb_book_manager`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `book_author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_first_name` varchar(45) DEFAULT NULL,
  `author_last_name` varchar(45) DEFAULT NULL,
  `about_author` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


ALTER TABLE `book` 
  ADD FOREIGN KEY (`author_detail_id`)  REFERENCES `book_author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
;

SET FOREIGN_KEY_CHECKS = 1;
21:07:41	ALTER TABLE `book`    ADD FOREIGN KEY (`author_detail_id`)  REFERENCES `book_author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION	Error Code: 1452. 
Cannot add or update a child row: a foreign key constraint fails (`hb_book_manager`.`#sql-1c78_153`, CONSTRAINT `book_ibfk_2` FOREIGN KEY (`author_detail_id`) REFERENCES `book_author` (`id`))	0.063 sec
