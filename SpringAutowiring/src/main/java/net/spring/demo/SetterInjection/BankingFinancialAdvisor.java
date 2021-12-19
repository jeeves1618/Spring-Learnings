package net.spring.demo.SetterInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingFinancialAdvisor implements FinancialAdvisor {

    private FeeCalculator feeCalculator;
    @Value("${source.of.advice}")
    private String sourceOfAdvice;

    @Value("${valid.till.date}")
    private String validTillDate;
    /*
    As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary
    if the target bean only defines one constructor to begin with. However, if several constructors
    are available, at least one must be annotated to teach the container which one to use.
     */
    @Autowired
    public void setFeeCalculator(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    @Override
    public String getDailyStockAdvice(){
        System.out.println("This advice is from " + sourceOfAdvice + " and it is valid till " + validTillDate);
        return "Buy 100 shares of J.P Morgan Chase from NYSE at $43.70.";
    }

    @Override
    public String getDailyFeeQuote() {
        return feeCalculator.getFee();
    }
}
