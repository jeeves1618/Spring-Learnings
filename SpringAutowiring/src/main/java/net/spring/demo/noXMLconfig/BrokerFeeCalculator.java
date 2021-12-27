package net.spring.demo.noXMLconfig;

public class BrokerFeeCalculator implements FeeCalculator {
    @Override
    public String getFee() {
        return "We charge a brokerage fee of 0.75% on your trade value. No additional taxes will be applied on this transaction fee.";
    }
}
