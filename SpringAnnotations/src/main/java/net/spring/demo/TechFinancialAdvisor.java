package net.spring.demo;

import org.springframework.stereotype.Component;

@Component("yourFinancialAdvisor")
public class TechFinancialAdvisor implements FinancialAdvisor{

    @Override
    public String getDailyStockAdvice(){
        return "Buy 100 shares of Microsoft in Nasdaq at market price.";
    }
}
