package com.bootbasics.UnderstandingAutowiring.Main;

import com.bootbasics.UnderstandingAutowiring.Portfolio.ValueCalculator;

public class MainApp {

    public static void main(String[] args) {

        ValueCalculator valueCalculator = new ValueCalculator();
        System.out.println(valueCalculator.getPortfolioValue());
    }


}
