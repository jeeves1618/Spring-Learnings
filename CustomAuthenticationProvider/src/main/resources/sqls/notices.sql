CREATE TABLE `notices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(200) NOT NULL,
  `posted_by` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `notices` (`message`, `posted_by`)
 VALUES ('I am gonna make him an offer he cannot refuse', 'Vito Corleone');
 
 INSERT INTO `notices` (`message`, `posted_by`)
 VALUES ('Not all those who wander are lost', 'JRR Tolkien');