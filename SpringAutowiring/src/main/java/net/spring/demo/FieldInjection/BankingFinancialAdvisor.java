package net.spring.demo.FieldInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankingFinancialAdvisor implements FinancialAdvisor {
    //Get the values from properties file
    @Value("${source.of.advice}")
    private String sourceOfAdvice;

    @Value("${valid.till.date}")
    private String validTillDate;

    @Autowired
    @Qualifier("advisorFeeCalculator")
    private FeeCalculator feeCalculator;

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
