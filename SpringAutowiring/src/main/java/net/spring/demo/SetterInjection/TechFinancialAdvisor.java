package net.spring.demo.SetterInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("yourFinancialAdvisor")
public class TechFinancialAdvisor implements FinancialAdvisor {

    private FeeCalculator feeCalculator;
    @Value("${source.of.advice}")
    private String sourceOfAdvice;

    @Value("${valid.till.date}")
    private String validTillDate;
    /*
    Here is an example for Method Injection.
    You don't really need the method to be named as a setter
    As long as the method is annotated with @Autowired, spring will look for the bean of the type specified in the
    argument and pass that to a constructor, setter or any random method.
     */
    @Autowired
    public void methodNameCanbeAnything(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    @Override
    public String getDailyStockAdvice(){
        System.out.println("This advice is from " + sourceOfAdvice + " and it is valid till " + validTillDate);
        return "Buy 100 shares of Microsoft in Nasdaq at market price.";
    }

    @Override
    public String getDailyFeeQuote() {
        return feeCalculator.getFee();
    }
}
