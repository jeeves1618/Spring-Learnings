SELECT * FROM hb_book_manager.book order by id;

alter table hb_book_manager.book add constraint bookname unique (book_title);

delete from hb_book_manager.book;

delete from hb_book_manager.book_author;