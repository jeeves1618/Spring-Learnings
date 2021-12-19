package net.spring.demo;

import org.springframework.stereotype.Component;

@Component
public class BankingFinancialAdvisor implements FinancialAdvisor{

    @Override
    public String getDailyStockAdvice(){
        return "Buy 100 shares of J.P Morgan Chase from NYSE at $43.70.";
    }
}
