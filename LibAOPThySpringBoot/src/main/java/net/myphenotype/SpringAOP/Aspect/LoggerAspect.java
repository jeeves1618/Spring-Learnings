package net.myphenotype.SpringAOP.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

    @Before("execution(public void showBooks())")
    public void beforeListing(){
        log.info("Aspect: Before Advice is in action.");
    }
}
