use `hb_book_manager`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `book_detail`;

CREATE TABLE `book_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopping_channel` varchar(128) DEFAULT NULL,
  `type_of_binding` varchar(45) DEFAULT "Hard Cover",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `instructor`;

ALTER TABLE `book` ADD book_detail_id int,
  ADD FOREIGN KEY (`book_detail_id`)  REFERENCES `book_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
;

SET FOREIGN_KEY_CHECKS = 1;
