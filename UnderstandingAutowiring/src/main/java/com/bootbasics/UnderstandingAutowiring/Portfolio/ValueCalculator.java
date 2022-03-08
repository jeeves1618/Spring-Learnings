package com.bootbasics.UnderstandingAutowiring.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;

public class ValueCalculator {

    private Double portfolioValue;

    private FeeCalculator feeCalculator;

    public ValueCalculator() {

    }

    @Autowired
    public void setFeeCalculator(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    public Double getPortfolioValue() {
        this.portfolioValue = Math.random()*100000;
        this.setFeeCalculator(feeCalculator);
        return this.portfolioValue - feeCalculator.calculateFee(portfolioValue);
    }
}
