package com.bootbasics.UnderstandingAutowiring.Portfolio;

public class FeeCalculator {

    public FeeCalculator() {
    }

    public Double calculateFee(Double portfolioValue){
        return portfolioValue * .15;
    }
}
