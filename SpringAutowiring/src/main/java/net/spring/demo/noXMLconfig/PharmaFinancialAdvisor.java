package net.spring.demo.noXMLconfig;

import org.springframework.beans.factory.annotation.Value;

public class PharmaFinancialAdvisor implements FinancialAdvisor{

    private FeeCalculator feeCalculator;
    @Value("${source.of.advice}")
    private String sourceOfAdvice;

    @Value("${valid.till.date}")
    private String validTillDate;

    public PharmaFinancialAdvisor(FeeCalculator feeCalculator){
        this.feeCalculator = feeCalculator;
    }
    @Override
    public String getDailyStockAdvice(){
        System.out.println("This advice is from " + sourceOfAdvice + " and it is valid till " + validTillDate);
        return "Buy 100 March Call Options of Novartis from CBOE at a strike price of $43.70.";
    }

    @Override
    public String getDailyFeeQuote() {
        return feeCalculator.getFee();
    }

    public String getSourceOfAdvice() {
        return sourceOfAdvice;
    }

    public String getValidTillDate() {
        return validTillDate;
    }
}
