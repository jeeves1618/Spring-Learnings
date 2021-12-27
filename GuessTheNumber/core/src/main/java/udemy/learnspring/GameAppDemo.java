package udemy.learnspring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameAppDemo {
    private final static Logger log = LoggerFactory
    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        NumberGenerator numberGenerator = context.getBean("numberGenerator",NumberGenerator.class);

        int number = numberGenerator.next();
        log.info("number = {}", number);

        Game game = context.getBean("game",GameImpl.class);

        game.reset();

        Game gameOne = context.getBean("gameTwo",GameImplAutoWired.class);

        gameOne.reset();

        context.close();

    }
}
