package net.myphenotype.SpringAOP.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;



@ControllerAdvice
public class BookExceptionHandler {

    /*
    Add a specific exception handler for Book Not Found Situation
     */
    public ResponseEntity<BookErrors> handleException(BookNotFound exc){

        //Create the Customized Not Found Response

        BookErrors bookErrors = new BookErrors(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(bookErrors, HttpStatus.NOT_FOUND);
    }
    /*
    Add a generic exception handler for any exceptions which were not handled before
     */
}
