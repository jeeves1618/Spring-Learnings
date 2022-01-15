SELECT * FROM hb_book_manager.book_detail;

/*delete from hb_book_manager.book_detail where id not in (select book_detail_id from hb_book_manager.book);

SELECT * FROM hb_book_manager.book_detail;*/

update hb_book_manager.book_detail set book_id = 1;