package udemy.spring.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    private final static Logger log = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        System.out.println("Hello World!");
        log.info("He he he! I am using Log4J");
        log.debug("He he he! I am using Log4J");
        log.warn("I am warning you");
    }
}
