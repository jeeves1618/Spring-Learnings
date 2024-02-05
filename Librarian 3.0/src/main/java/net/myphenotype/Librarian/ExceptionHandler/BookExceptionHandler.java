package net.myphenotype.Librarian.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class BookExceptionHandler {

    /*
    Add a specific exception handler for Book Not Found Situation
     */
    @ExceptionHandler
    public ResponseEntity<BookErrors> handleException(BookNotFound exc){

        //Create the Customized Not Found Response
        log.info("Setting the Exception as Not Found");
        BookErrors bookErrors = new BookErrors(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(bookErrors, HttpStatus.NOT_FOUND);
    }
    /*
    Add a generic exception handler for any exceptions which were not handled before
     */
    @ExceptionHandler
    public ResponseEntity<BookErrors> handleException(Exception exc){

        //Create the Customized Not Found Response
        log.info("Setting the Exception as Bad Request");
        BookErrors bookErrors = new BookErrors(HttpStatus.BAD_REQUEST.value(),exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(bookErrors, HttpStatus.BAD_REQUEST);
    }
}
