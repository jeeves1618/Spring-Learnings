package net.myphenotype.Librarian.Service;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.DAO.BookDao;
import net.myphenotype.Librarian.Entity.*;
import net.myphenotype.Librarian.Repository.CurrencyRateRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    @Autowired
    BookExpanded bookExpanded;

    @Autowired
    BookDetail bookDetail;

    @Autowired
    BookSummary bookSummary;

    @Autowired
    Authors authors;

    @Autowired
    RupeeFormatter rf;

    @Autowired
    Book book;

    @Autowired
    CurrencyRate currencyRate;

    @Autowired
    CurrencyRateRepository currencyRateRepository;

    ResourceBundle properties  = ResourceBundle.getBundle("BookProperties");
    String currencyFormat = properties.getString("currencyFormat");
    DecimalFormat ft = new DecimalFormat(currencyFormat);
    final int MAX_AUTHOR_COUNT = 4;

    public Iterable<BookExpanded> listBooks(String uri){
        Iterable<Book> bookList = bookDao.listBooks();
        List<BookExpanded> bookExpandedList = new ArrayList<>();
        BookExpanded bookExpandedTemp = new BookExpanded();
        int i = 0;
        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);

        for (Book tempBook: bookList){
            try{
                if(tempBook.getBookTitle().contains(":")) {
                    System.out.println(uri);
                    if (uri.equals("/book/downloadList") || uri.equals("/book/downloadReadList"))
                        bookExpanded.setBookTitle(tempBook.getBookTitle());
                    else
                        bookExpanded.setBookTitle(tempBook.getBookTitle().substring(0, tempBook.getBookTitle().indexOf(":")));
                }
                else {
                    bookExpanded.setBookTitle(tempBook.getBookTitle());
                }
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
                bookExpanded.setShoppingUrl(tempBook.getBookDetail().getShoppingUrl());
                bookExpanded.setReadingNotesUrl(tempBook.getBookDetail().getReadingNotesUrl());
                bookExpanded.setTypeOfBinding(tempBook.getBookDetail().getTypeOfBinding());
                bookExpanded.setIsbNumber(tempBook.getBookDetail().getIsbNumber());
                bookExpanded.setImageFileName(tempBook.getImageFileName());
                bookExpanded.setReadStatus(tempBook.getReadStatus());
                bookExpanded.setDateOfReading(tempBook.getDateOfReading());
                bookExpanded.setRatingOfUsefulness(tempBook.getRatingOfUsefulness());
                bookExpanded.setAllTimeGreatIndicator(tempBook.getAllTimeGreatIndicator());                                                                                                                                                                                                     
            } catch (NullPointerException nullPointerException) {
                log.info("Object not found for book ID, " + tempBook.getId());
            }

            log.info("Debug = " + tempBook.getId());
            bookExpanded = appendAuthors(bookExpanded,tempBook.getId());
            bookSummary.setTotalCost(bookSummary.getTotalCost()+bookExpanded.getCostInLocalCurrency());
            bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);

            bookExpandedList.add(bookExpanded);
            try {
                BeanUtils.copyProperties(bookExpandedTemp, bookExpanded);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            bookExpanded = new BookExpanded();
            if (uri.equals("/book/downloadReadList")){
                List<Readings> readings = bookDao.listReadings(bookExpandedTemp.getId());
                for(Readings reading: readings){
                    bookExpandedTemp.setDateOfReading(reading.getDateOfReading());
                    bookExpandedTemp.setRatingOfUsefulness(reading.getRatingOfUsefulness());
                    bookExpandedTemp.setAllTimeGreatIndicator(reading.getAllTimeGreatIndicator());
                    bookExpandedTemp.setReadingNotesUrl(reading.getReadingNotesUrl());
                    bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);
                    bookExpandedList.add(bookExpandedTemp);
                    bookExpandedTemp = new BookExpanded();
                }
            }
            i++;
        }
        bookSummary.setTotalCostOfBooks(rf.formattedRupee(ft.format(bookSummary.getTotalCost())));
        log.info("Total Number = " + bookSummary.getNumberOfBooks());
        log.info("Total Cost = " + bookSummary.getTotalCostOfBooks());
        return bookExpandedList;
    }
    public List<BookExpanded> findBooksByGenre(String genre){
        System.out.println(genre);
        if(genre.contains(" (")) {
            System.out.println(genre);
            genre = genre.substring(0, genre.indexOf(" ("));
        }

        Iterable<Book> bookList = bookDao.listBookByTopics(genre);
        List<BookExpanded> bookExpandedList = new ArrayList<>();

        int i = 0;
        bookSummary.setTotalCost(0.00);
        bookSummary.setNumberOfBooks(0);

        for (Book tempBook: bookList){
            try{
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
                bookExpanded.setShoppingUrl(tempBook.getBookDetail().getShoppingUrl());
                bookExpanded.setTypeOfBinding(tempBook.getBookDetail().getTypeOfBinding());
                bookExpanded.setIsbNumber(tempBook.getBookDetail().getIsbNumber());
                bookExpanded.setImageFileName(tempBook.getImageFileName());
            } catch (NullPointerException nullPointerException) {
                log.info("Object not found for book ID, " + tempBook.getId());
            }

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
    public List<BookExpanded> getBooksByPartialName(String theSearchName, String allTimeGreatIndicator) {
        List<Book> bookList = null;
        if(allTimeGreatIndicator == null)
            bookList = bookDao.getBooksByPartialName(theSearchName);
        else
            bookList = bookDao.getAllTimeGreatsByPartialName(theSearchName,"Yes");
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
            bookExpanded.setRatingOfUsefulness(tempBook.getRatingOfUsefulness());
            bookExpanded.setAllTimeGreatIndicator(tempBook.getAllTimeGreatIndicator());
            bookExpanded.setId(tempBook.getId());
            bookExpanded.setCostInLocalCurrency(costInLocalCurrency(tempBook.getCostOfPurchase(), tempBook.getCurrencyCode()));
            bookExpanded.setCostInLocalCurrencyFmtd(costInLocalCurrencyFmtd(bookExpanded.getCostInLocalCurrency()));
            bookExpanded.setShoppingUrl(tempBook.getBookDetail().getShoppingUrl());
            bookExpanded.setTypeOfBinding(tempBook.getBookDetail().getTypeOfBinding());
            bookExpanded.setIsbNumber(tempBook.getBookDetail().getIsbNumber());
            bookExpanded.setImageFileName(tempBook.getImageFileName());
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
        if(book.getAuthorsFirstName3() == null) log.info("Null");
        if(book.getAuthorsFirstName3().isEmpty()) log.info("Empty");
        if(book.getAuthorsFirstName3().isBlank()) log.info("Blank");
        if(book.getAuthorsFirstName3().length() == 0) log.info("Spaces?");
        if(book.getAuthorsFirstName1().length() > 0 || book.getAuthorsLastName1().length() > 0)
        {
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
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName2());
            authors.setAuthorsLastName(book.getAuthorsLastName2());
            authorsList.add(authors);
            authors = new Authors();
        }

        if(book.getAuthorsFirstName3().length() > 0 || book.getAuthorsLastName3().length() > 0)
        {
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName3());
            authors.setAuthorsLastName(book.getAuthorsLastName3());
            authorsList.add(authors);
            authors = new Authors();
        }

        if(book.getAuthorsFirstName4().length() > 0 || book.getAuthorsLastName4().length() > 0)
        {
            authors.setBook(book);
            authors.setAuthorsFirstName(book.getAuthorsFirstName4());
            authors.setAuthorsLastName(book.getAuthorsLastName4());
            authorsList.add(authors);
        }
        if(book.getBookTitle().length() > 200)
            book.setBookTitle(book.getBookTitle().substring(0,200));
        log.info("Book Length " + book.getBookTitle().length());
        book.setAuthorsList(authorsList);
        bookDao.saveBook(book);
    }

    public void saveBook(BookExpanded bookExpanded) {
        List<Authors> authorsList = new ArrayList<>();
        log.info("The ID is " + bookExpanded.getId());
        if(bookExpanded.getId() != null)
            book.setId(bookExpanded.getId());
        book.setBookTitle(bookExpanded.getBookTitle());
        log.info("Book Length " + bookExpanded.getBookTitle().length());
        if (bookExpanded.getBookTitle().length() > 255)
            book.setBookTitleAdditionalChars(bookExpanded.getBookTitle().substring(255));
        book.setBookGenre(bookExpanded.getBookGenre());
        book.setAuthorFirstName(bookExpanded.getAuthorsFirstName1());
        book.setAuthorLastName(bookExpanded.getAuthorsLastName1());
        book.setAuthorsFirstName1(bookExpanded.getAuthorsFirstName1());
        book.setAuthorsLastName1(bookExpanded.getAuthorsLastName1());
        book.setAuthorsFirstName2(bookExpanded.getAuthorsFirstName2());
        book.setAuthorsLastName2(bookExpanded.getAuthorsLastName2());
        book.setAuthorsFirstName3(bookExpanded.getAuthorsFirstName3());
        book.setAuthorsLastName3(bookExpanded.getAuthorsLastName3());
        book.setAuthorsFirstName4(bookExpanded.getAuthorsFirstName4());
        book.setAuthorsLastName4(bookExpanded.getAuthorsLastName4());
        book.setPublisherName(bookExpanded.getPublisherName());
        book.setDateOfPurchase(bookExpanded.getDateOfPurchase());
        book.setCostOfPurchase(bookExpanded.getCostOfPurchase());
        book.setCurrencyCode(bookExpanded.getCurrencyCode());
        book.setReadStatus(bookExpanded.getReadStatus());
        book.setDateOfReading(bookExpanded.getDateOfReading());
        book.setRatingOfUsefulness(bookExpanded.getRatingOfUsefulness());
        book.setAllTimeGreatIndicator(bookExpanded.getAllTimeGreatIndicator());
        bookDetail.setShoppingUrl(bookExpanded.getShoppingUrl());
        book.setImageFileName(bookExpanded.getImageFileName());
        bookDetail.setReadingNotesUrl(bookExpanded.getReadingNotesUrl());
        bookDetail.setTypeOfBinding(bookExpanded.getTypeOfBinding());
        bookDetail.setShoppingChannel(bookExpanded.getShoppingChannel());
        bookDetail.setIsbNumber(bookExpanded.getIsbNumber());
        bookDetail.setBook(book);
        book.setBookDetail(bookDetail);

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

    public List<Readings> getReadings(int bookId){
        return bookDao.listReadings(bookId);
    }

    public void saveBook(BookExpanded bookExpanded, Readings readings) {
        List<Authors> authorsList = new ArrayList<>();
        List<Readings> readingsList = bookDao.listReadings(bookExpanded.getId());
        log.info("The ID is " + bookExpanded.getId());
        if(bookExpanded.getId() != null)
            book.setId(bookExpanded.getId());
        book.setBookTitle(bookExpanded.getBookTitle());
        log.info("Book Length " + bookExpanded.getBookTitle().length());
        if (bookExpanded.getBookTitle().length() > 255)
            book.setBookTitleAdditionalChars(bookExpanded.getBookTitle().substring(255));
        book.setBookGenre(bookExpanded.getBookGenre());
        book.setAuthorFirstName(bookExpanded.getAuthorsFirstName1());
        book.setAuthorLastName(bookExpanded.getAuthorsLastName1());
        book.setAuthorsFirstName1(bookExpanded.getAuthorsFirstName1());
        book.setAuthorsLastName1(bookExpanded.getAuthorsLastName1());
        book.setAuthorsFirstName2(bookExpanded.getAuthorsFirstName2());
        book.setAuthorsLastName2(bookExpanded.getAuthorsLastName2());
        book.setAuthorsFirstName3(bookExpanded.getAuthorsFirstName3());
        book.setAuthorsLastName3(bookExpanded.getAuthorsLastName3());
        book.setAuthorsFirstName4(bookExpanded.getAuthorsFirstName4());
        book.setAuthorsLastName4(bookExpanded.getAuthorsLastName4());
        book.setPublisherName(bookExpanded.getPublisherName());
        book.setDateOfPurchase(bookExpanded.getDateOfPurchase());
        book.setCostOfPurchase(bookExpanded.getCostOfPurchase());
        book.setCurrencyCode(bookExpanded.getCurrencyCode());
        book.setReadStatus(bookExpanded.getReadStatus());
        book.setDateOfReading(bookExpanded.getDateOfReading());
        book.setRatingOfUsefulness(bookExpanded.getRatingOfUsefulness());
        book.setAllTimeGreatIndicator(bookExpanded.getAllTimeGreatIndicator());
        bookDetail.setShoppingUrl(bookExpanded.getShoppingUrl());
        book.setImageFileName(bookExpanded.getImageFileName());
        bookDetail.setReadingNotesUrl(bookExpanded.getReadingNotesUrl());
        bookDetail.setTypeOfBinding(bookExpanded.getTypeOfBinding());
        bookDetail.setShoppingChannel(bookExpanded.getShoppingChannel());
        bookDetail.setIsbNumber(bookExpanded.getIsbNumber());
        bookDetail.setBook(book);
        book.setBookDetail(bookDetail);

        if (readings.getDateOfReading().length() > 0 ) {
            readings.setBook(book);
            readingsList.add(readings);
            book.setReadingsList(readingsList);
        }
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
        log.info("Multiple representations of the same entity are being merged 1.");
        bookDao.saveBook(book);
        log.info("Multiple representations of the same entity are being merged 2.");
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
        bookExpanded.setImageFileName(tempBook.getImageFileName());
        bookExpanded.setReadStatus(tempBook.getReadStatus());
        bookExpanded.setDateOfReading(tempBook.getDateOfReading());
        bookExpanded.setRatingOfUsefulness(tempBook.getRatingOfUsefulness());
        bookExpanded.setAllTimeGreatIndicator(tempBook.getAllTimeGreatIndicator());
        bookExpanded.setTypeOfBinding(tempBook.getBookDetail().getTypeOfBinding());
        bookExpanded.setShoppingChannel(tempBook.getBookDetail().getShoppingChannel());
        bookExpanded.setShoppingUrl(tempBook.getBookDetail().getShoppingUrl());
        bookExpanded.setIsbNumber(tempBook.getBookDetail().getIsbNumber());
        bookExpanded.setReadingNotesUrl(tempBook.getBookDetail().getReadingNotesUrl());
        bookExpanded = appendAuthors(bookExpanded,tempBook.getId());
        bookSummary.setTotalCost(bookSummary.getTotalCost()+bookExpanded.getCostInLocalCurrency());
        bookSummary.setNumberOfBooks(bookSummary.getNumberOfBooks() + 1);
        return bookExpanded;
    }

    public void deleteBookById(int theID) {
        bookDao.deleteBookById(theID);
    }

    private double costInLocalCurrency(double cost, String currencyCode){

        double rate;
        
            if(currencyCode.equals("INR"))
                rate = 1;
            else
                rate = currencyRateRepository.findById(currencyCode).get().getRateOfExchange();

        if (rate == 0) {
            try {
                rate = Double.parseDouble(properties.getString(currencyCode));
            } catch (MissingResourceException missingResourceException) {
                rate = 1.01;
            }
        }
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

    public List<Topic> findCountByTopics(){
        List<Topic> topicSummaryList =  bookDao.findCountByTopics();
        for(Topic summary:topicSummaryList){
            summary.setBookGenre(summary.getBookGenre() + " (" + summary.getBookCount() + ")");
        }
        return topicSummaryList;
    }

    public List<Topic> downloadTopics(){
        List<Topic> topicSummaryList =  bookDao.findCountByTopics();
        return topicSummaryList;
    }

    public void saveAll(List<Topic> topicList){
        bookDao.saveAll(topicList);
    }

    public void saveTopic(Book book){
        List<Topic> topics = bookDao.findTopicByGenre(book.getBookGenre());
        Topic topic;
        if (topics.size() == 0){
            topic = new Topic();
            topic.setBookGenre(book.getBookGenre());
            topic.setBookCount(1);
            topic.setImageFileName("/img/topic/topic-page-18.jpg");
        } else {
            topic = topics.get(0);
            topic.setBookCount(topic.getBookCount()+1);
        }
        bookDao.saveTopic(topic);
    }
}
