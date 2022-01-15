package Librarian.BookManager.Service;

import Librarian.BookManager.DAO.BookDAO;
import Librarian.BookManager.Entity.Book;
import Librarian.BookManager.Entity.BookExpanded;
import Librarian.BookManager.Entity.BookSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
    /*
    Inject BookDao here
     */
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BookExpanded bookExpanded;
    @Autowired
    private BookSummary bookSummary;

    ResourceBundle properties  = ResourceBundle.getBundle("BookProperties");
    String currencyFormat = properties.getString("currencyFormat");
    DecimalFormat ft = new DecimalFormat(currencyFormat);
    RupeeFormatter rf = new RupeeFormatter();
    @Override
    @Transactional
    public void deleteBookById(int theID) {
        bookDAO.deleteBookById(theID);
    }

    @Override
    @Transactional
    public List<BookExpanded> getBooks() {
        List<Book> bookList = bookDAO.getBooks();
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

    private double costInLocalCurrency(double cost, String currencyCode){
        Double rate =  Double.parseDouble(properties.getString(currencyCode));
        return cost * rate;
    }

    private String costInLocalCurrencyFmtd(double costInLocalCurrency){

        return rf.formattedRupee(ft.format(costInLocalCurrency));
    }
    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBookbyID(int theID) {
        return bookDAO.getBookbyID(theID);
    }

    @Override
    @Transactional
    public List<BookExpanded> getBooksByPartialName(String theSearchName) {
        List<Book> bookList = bookDAO.getBooksByPartialName(theSearchName);
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
}
