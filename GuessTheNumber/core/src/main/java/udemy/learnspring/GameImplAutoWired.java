package udemy.learnspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImplAutoWired implements Game{
    private static final Logger log = LoggerFactory.getLogger(GameImplAutoWired.class);
    @Autowired
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        largest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.info("The number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("Inside preDestroy");
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return largest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange){
            if(guess > number)
                largest = guess - 1;
            if (guess < number)
                smallest = guess + 1;
        }
        remainingGuesses --;
    }

    @Override
    public boolean isValidNumber() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <=0;
    }

    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= largest);
    }
}
