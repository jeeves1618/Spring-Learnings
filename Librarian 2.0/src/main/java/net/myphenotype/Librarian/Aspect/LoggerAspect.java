package net.myphenotype.Librarian.Aspect;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Entity.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

    @Before("execution(public void showBooks())")
    public void beforeListing(){
        log.info("Aspect: Before Advice is in action.");
    }

    @AfterReturning(
            pointcut = "execution(* net.myphenotype.Librarian.DAO.*.list*())", returning = "list")
    public void afterReturningBooks(JoinPoint joinPoint, List<Book> list){
        log.info("Executing @AfterReturning on method: " + joinPoint.getSignature().toShortString());
        log.info("The returned list is : " + list);
    }
}
