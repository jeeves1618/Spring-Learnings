SELECT 
    *
FROM
    hb_book_manager.book_author;
    
delete from hb_book_manager.book_author;

ALTER TABLE hb_book_manager.book DROP FOREIGN KEY book_ibfk_1;

ALTER TABLE hb_book_manager.book_detail add column book_id int(11) default 0;

use `hb_book_manager`;

ALTER TABLE `book_detail` 
  ADD FOREIGN KEY (`book_id`)  REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE hb_book_manager.book drop column author_detail_id;
