package net.spring.demo.FieldInjection;

import org.springframework.stereotype.Component;

@Component
public class PortfolioFeeCalculator implements FeeCalculator {
    @Override
    public String getFee() {
        return "The fee for maintaining your portfolio is 1% of your portfolio value";
    }
}
