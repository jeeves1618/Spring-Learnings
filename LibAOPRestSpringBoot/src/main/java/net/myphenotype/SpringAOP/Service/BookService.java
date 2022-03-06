package net.myphenotype.SpringAOP.Service;


import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.DAO.BookDao;
import net.myphenotype.SpringAOP.Entity.Authors;
import net.myphenotype.SpringAOP.Entity.Book;
import net.myphenotype.SpringAOP.Entity.BookExpanded;
import net.myphenotype.SpringAOP.Entity.BookSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    @Autowired
    BookExpanded bookExpanded;

    @Autowired
    BookSummary bookSummary;

    @Autowired
    Authors authors;

    @Autowired
    RupeeFormatter rf;

    ResourceBundle properties  = ResourceBundle.getBundle("BookProperties");
    String currencyFormat = properties.getString("currencyFormat");
    DecimalFormat ft = new DecimalFormat(currencyFormat);
    final int MAX_AUTHOR_COUNT = 4;

    public Iterable<BookExpanded> listBooks(){
        Iterable<Book> bookList = bookDao.listBooks();
        List<BookExpanded> bookExpandedList = new ArrayList<>();

        int i = 0;
        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);

        for (Book tempBook: bookList){
            if(tempBook.getBookTitle().contains(":"))
                bookExpanded.setBookTitle(tempBook.getBookTitle().substring(0,tempBook.getBookTitle().indexOf(":")));
            else
                bookExpanded.setBookTitle(tempBook.getBookTitle());
            bookExpanded.setBookGenre((tempBook.getBookGenre()));
            bookExpanded.setAuthorFirstName(tempBook.getAuthorFirstName());
            bookExpanded.setAuthorLastName(tempBook.getAuthorLastName());
            bookExpanded.setPublisherName(tempBook.getPublisherName());
            bookExpanded.setDateOfPurchase(tempBook.getDateOfPurchase());
            bookExpanded.setCostOfPurchase(tempBook.getCostOfPurchase());
            bookExpanded.setCurrencyCode(tempBook.getCurrencyCode());
            bookExpanded.setId(tempBook.getId());
            bookExpanded.setCostInLocalCurrency(costInLocalCurrency(tempBook.getCostOfPurchase(), tempBook.getCurrencyCode()));
            bookExpanded.setCostInLocalCurrencyFmtd(costInLocalCurrencyFmtd(bookExpanded.getCostInLocalCurrency()));
            bookExpanded.setShoppingChannel(tempBook.getBookDetail().getShoppingChannel());
            bookExpanded.setTypeOfBinding(tempBook.getBookDetail().getTypeOfBinding());
            bookExpanded.setIsbNumber(tempBook.getBookDetail().getIsbNumber());
            log.info("Debug = " + tempBook.getId());
            bookExpanded = appendAuthors(bookExpanded,tempBook.getId());
            bookSummary.setTotalCost(bookSummary.getTotalCost()+bookExpanded.getCostInLocalCurrency());
            bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);

            bookExpandedList.add(bookExpanded);
            bookExpanded = new BookExpanded();
            i++;
        }
        bookSummary.setTotalCostOfBooks(rf.formattedRupee(ft.format(bookSummary.getTotalCost())));
        log.info("Total Number = " + bookSummary.getNumberOfBooks());
        log.info("Total Cost = " + bookSummary.getTotalCostOfBooks());
        return bookExpandedList;
    }

    public List<BookExpanded> getBooksByPartialName(String theSearchName) {
        List<Book> bookList = bookDao.getBooksByPartialName(theSearchName);
        List<BookExpanded> bookExpandedList = new ArrayList<>();
        int i = 0;
        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);

        for (Book tempBook: bookList){
            if(tempBook.getBookTitle().contains(":"))
                bookExpanded.setBookTitle(tempBook.getBookTitle().substring(0,tempBook.getBookTitle().indexOf(":")));
            else
                bookExpanded.setBookTitle(tempBook.getBookTitle());
            bookExpanded.setBookGenre((tempBook.getBookGenre()));
            bookExpanded.setAuthorFirstName(tempBook.getAuthorFirstName());
            bookExpanded.setAuthorLastName(tempBook.getAuthorLastName());
            bookExpanded.setPublisherName(tempBook.getPublisherName());
            bookExpanded.setDateOfPurchase(tempBook.getDateOfPurchase());
            bookExpanded.setCostOfPurchase(tempBook.getCostOfPurchase());
            bookExpanded.setCurrencyCode(tempBook.getCurrencyCode());
            bookExpanded.setId(tempBook.getId());
            bookExpanded.setCostInLocalCurrency(costInLocalCurrency(tempBook.getCostOfPurchase(), tempBook.getCurrencyCode()));
            bookExpanded.setCostInLocalCurrencyFmtd(costInLocalCurrencyFmtd(bookExpanded.getCostInLocalCurrency()));
            bookExpanded = appendAuthors(bookExpanded,tempBook.getId());
            bookSummary.setTotalCost(bookSummary.getTotalCost()+bookExpanded.getCostInLocalCurrency());
            bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);
            bookExpandedList.add(bookExpanded);
            bookExpanded = new BookExpanded();
            i++;
        }
        bookSummary.setTotalCostOfBooks(rf.formattedRupee(ft.format(bookSummary.getTotalCost())));
        log.info("Total Number = " + bookSummary.getNumberOfBooks());
        log.info("Total Cost = " + bookSummary.getTotalCostOfBooks());
        return bookExpandedList;
    }

    public void saveBook(Book book) {
        List<Authors> authorsList = new ArrayList<>();
        int authorCounter = 1;
        if(book.getAuthorsFirstName3().equals(null)) log.info("Null");
        if(book.getAuthorsFirstName3().isEmpty()) log.info("Empty");
        if(book.getAuthorsFirstName3().isBlank()) log.info("Blank");
        if(book.getAuthorsFirstName3().length() == 0) log.info("Spaces?");
        if(book.getAuthorsFirstName1().length() > 0 || book.getAuthorsLastName1().length() > 0)
        {
            log.info("setting Author" + authorCounter++ + " as " + book.getAuthorsFirstName1());
            authors.setBook(book);
            book.setAuthorFirstName(book.getAuthorsFirstName1());
            book.setAuthorLastName(book.getAuthorsLastName1());
            authors.setAuthorsFirstName(book.getAuthorFirstName());
            authors.setAuthorsLastName(book.getAuthorLastName());
            authorsList.add(authors);
            authors = new Authors();
        }

        if(book.getAuthorsFirstName2().length() > 0 || book.getAuthorsLastName2().length() > 0)
        {
            log.info("setting Author" + authorCounter++ + " as " + book.getAuthorsFirstName2());
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName2());
            authors.setAuthorsLastName(book.getAuthorsLastName2());
            authorsList.add(authors);
            authors = new Authors();
        }

        if(book.getAuthorsFirstName3().length() > 0 || book.getAuthorsLastName3().length() > 0)
        {
            log.info("setting Author" + authorCounter++ + " as " + book.getAuthorsFirstName3());
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName3());
            authors.setAuthorsLastName(book.getAuthorsLastName3());
            authorsList.add(authors);
            authors = new Authors();
        }

        if(book.getAuthorsFirstName4().length() > 0 || book.getAuthorsLastName4().length() > 0)
        {
            log.info("setting Author" + authorCounter++ + " as " + book.getAuthorsFirstName4());
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName4());
            authors.setAuthorsLastName(book.getAuthorsLastName4());
            authorsList.add(authors);
        }
        if(book.getBookTitle().length() > 90)
            book.setBookTitle(book.getBookTitle().substring(0,90));
        book.setAuthorsList(authorsList);
        bookDao.saveBook(book);
    }

    public Book getBookbyID(int theID) {
        Book book = bookDao.getBookbyID(theID);
        book = appendAuthors(book,theID);
        return book;
    }

    public BookExpanded getBook(int theID) {
        Book tempBook = bookDao.getBookbyID(theID);
        if (tempBook == null){
            bookExpanded = null;
            return bookExpanded;
        }
        tempBook = appendAuthors(tempBook,theID);

        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);
        if(tempBook.getBookTitle().contains(":"))
            bookExpanded.setBookTitle(tempBook.getBookTitle().substring(0,tempBook.getBookTitle().indexOf(":")));
        else
            bookExpanded.setBookTitle(tempBook.getBookTitle());
        bookExpanded.setBookGenre((tempBook.getBookGenre()));
        bookExpanded.setAuthorFirstName(tempBook.getAuthorFirstName());
        bookExpanded.setAuthorLastName(tempBook.getAuthorLastName());
        bookExpanded.setPublisherName(tempBook.getPublisherName());
        bookExpanded.setDateOfPurchase(tempBook.getDateOfPurchase());
        bookExpanded.setCostOfPurchase(tempBook.getCostOfPurchase());
        bookExpanded.setCurrencyCode(tempBook.getCurrencyCode());
        bookExpanded.setId(tempBook.getId());
        bookExpanded.setCostInLocalCurrency(costInLocalCurrency(tempBook.getCostOfPurchase(), tempBook.getCurrencyCode()));
        bookExpanded.setCostInLocalCurrencyFmtd(costInLocalCurrencyFmtd(bookExpanded.getCostInLocalCurrency()));
        bookExpanded = appendAuthors(bookExpanded,tempBook.getId());
        bookSummary.setTotalCost(bookSummary.getTotalCost()+bookExpanded.getCostInLocalCurrency());
        bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);
        return bookExpanded;
    }

    public void deleteBookById(int theID) {
        bookDao.deleteBookById(theID);
    }

    private double costInLocalCurrency(double cost, String currencyCode){
        Double rate =  Double.parseDouble(properties.getString(currencyCode));
        return cost * rate;
    }

    private String costInLocalCurrencyFmtd(double costInLocalCurrency){

        return rf.formattedRupee(ft.format(costInLocalCurrency));
    }

    private BookExpanded appendAuthors(BookExpanded bookExpanded, int theID){
        List<Authors> authorsList = new ArrayList<>();
        int authorCounter=1;
        bookExpanded.setAuthorsFirstName2(" ");
        bookExpanded.setAuthorsLastName2(" ");
        bookExpanded.setAuthorsFirstName3(" ");
        bookExpanded.setAuthorsLastName3(" ");
        bookExpanded.setAuthorsFirstName4(" ");
        bookExpanded.setAuthorsLastName4(" ");
        authorsList = bookDao.getAuthorsByBookId(theID);
        log.info("Retrieved Authors : " + authorsList);
        for(Authors author: authorsList){
            if(authorCounter == 1) {
                bookExpanded.setAuthorFirstName(author.getAuthorsFirstName());
                bookExpanded.setAuthorLastName(author.getAuthorsLastName());
                bookExpanded.setAuthorsFirstName1(author.getAuthorsFirstName());
                bookExpanded.setAuthorsLastName1(author.getAuthorsLastName());
                log.info("fetching Author" + authorCounter + " as " + bookExpanded.getAuthorsFirstName1());
            }

            if(authorCounter == 2) {
                bookExpanded.setAuthorsFirstName2(author.getAuthorsFirstName());
                bookExpanded.setAuthorsLastName2(author.getAuthorsLastName());
                log.info("fetching Author" + authorCounter + " as " + bookExpanded.getAuthorsFirstName2());
            }

            if(authorCounter == 3) {
                bookExpanded.setAuthorsFirstName3(author.getAuthorsFirstName());
                bookExpanded.setAuthorsLastName3(author.getAuthorsLastName());
                log.info("fetching Author" + authorCounter + " as " + bookExpanded.getAuthorsFirstName3());
            }

            if(authorCounter == 4) {
                bookExpanded.setAuthorsFirstName4(author.getAuthorsFirstName());
                bookExpanded.setAuthorsLastName4(author.getAuthorsLastName());
                log.info("fetching Author" + authorCounter + " as " + bookExpanded.getAuthorsFirstName4());
            }
            authorCounter++;
        }
        return bookExpanded;
    }

    private Book appendAuthors(Book book, int theID){
        List<Authors> authorsList = new ArrayList<>();
        int authorCounter=1;
        book.setAuthorsFirstName2(" ");
        book.setAuthorsLastName2(" ");
        book.setAuthorsFirstName3(" ");
        book.setAuthorsLastName3(" ");
        book.setAuthorsFirstName4(" ");
        book.setAuthorsLastName4(" ");
        authorsList = bookDao.getAuthorsByBookId(theID);
        log.info("Retrieved Authors : " + authorsList);
        for(Authors author: authorsList){
            if(authorCounter == 1) {
                book.setAuthorFirstName(author.getAuthorsFirstName());
                book.setAuthorLastName(author.getAuthorsLastName());
                book.setAuthorsFirstName1(author.getAuthorsFirstName());
                book.setAuthorsLastName1(author.getAuthorsLastName());
                log.info("setting Author" + authorCounter + " as " + book.getAuthorsFirstName1());
            }

            if(authorCounter == 2) {
                book.setAuthorsFirstName2(author.getAuthorsFirstName());
                book.setAuthorsLastName2(author.getAuthorsLastName());
                log.info("setting Author" + authorCounter + " as " + book.getAuthorsFirstName2());
            }

            if(authorCounter == 3) {
                book.setAuthorsFirstName3(author.getAuthorsFirstName());
                book.setAuthorsLastName3(author.getAuthorsLastName());
                log.info("setting Author" + authorCounter + " as " + book.getAuthorsFirstName3());
            }

            if(authorCounter == 4) {
                book.setAuthorsFirstName4(author.getAuthorsFirstName());
                book.setAuthorsLastName4(author.getAuthorsLastName());
                log.info("setting Author" + authorCounter + " as " + book.getAuthorsFirstName4());
            }
            authorCounter++;
        }
        return book;
    }
}
