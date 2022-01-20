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

    public Iterable<BookExpanded> listBooks(){
        Iterable<Book> bookList = bookDao.listBooks();
        List<BookExpanded> bookExpandedList = new ArrayList<>();
        int i = 0;
        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);

        for (Book tempBook: bookList){
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
        authors.setBook(book);
        authors.setAuthorsFirstName(book.getAuthorFirstName());
        authors.setAuthorsLastName(book.getAuthorLastName());
        bookDao.saveBook(book);
    }

    public Book getBookbyID(int theID) {
        return bookDao.getBookbyID(theID);
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
}
